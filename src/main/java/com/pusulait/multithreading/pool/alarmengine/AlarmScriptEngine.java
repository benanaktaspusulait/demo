package com.pusulait.multithreading.pool.alarmengine;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.UUID;

@Data
@Slf4j
@EqualsAndHashCode
@ToString
@Component
@Scope("prototype")
public class AlarmScriptEngine implements DisposableBean {

    private String key;
    private Date runDate = new Date();

    @PostConstruct
    public void initialize() {
        /* ScriptEngine context create edilir. PUT edilir */
        key = UUID.randomUUID().toString();
        log.info("AlarmScriptEngine INIT " + key);
    }

    @Override
    public void destroy() throws Exception {
        /* Burada context temizlenir yeniden put edilir */
        log.warn("BEAN DESTROY : " + key);
    }
}
