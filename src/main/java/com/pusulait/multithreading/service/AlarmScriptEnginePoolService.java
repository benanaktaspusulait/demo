package com.pusulait.multithreading.service;

import com.pusulait.multithreading.config.AlarmScriptEnginePoolConfig;
import com.pusulait.multithreading.pool.alarmengine.AlarmScriptEngine;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.springframework.aop.target.CommonsPool2TargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Data
@Slf4j
@Scope("prototype")
@Service
public class AlarmScriptEnginePoolService {

    @Autowired
    private ApplicationContext context;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private CommonsPool2TargetSource pool2TargetSource;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private AlarmScriptEngine alarmScriptEngine;

    @PostConstruct
    void init() throws Exception {
        pool2TargetSource = ((CommonsPool2TargetSource) context.getBean(AlarmScriptEnginePoolConfig.POOL_OBJECT_NAME));
        alarmScriptEngine = (AlarmScriptEngine) pool2TargetSource.getTarget();
    }

    public AlarmScriptEngine getScriptEngine() throws Exception {
        return alarmScriptEngine;
    }

    public void releaseScriptEngine() throws Exception {

        long diff = (new Date().getTime() - alarmScriptEngine.getRunDate().getTime()) / 1000L ;
        if (diff > 30L) {
            destroyScriptEngine();
            log.warn("detroy object: " + alarmScriptEngine.getKey());
        }
        pool2TargetSource.releaseTarget(alarmScriptEngine);
    }

    public void destroyScriptEngine() throws Exception {
        /* AlarmEngine.destroy cagrilir */
//        alarmScriptEngine.setRunDate(new Date());
        DefaultPooledObject<Object> pooledObject = new DefaultPooledObject<>(alarmScriptEngine);
        pool2TargetSource.destroyObject(pooledObject);

    }
}
