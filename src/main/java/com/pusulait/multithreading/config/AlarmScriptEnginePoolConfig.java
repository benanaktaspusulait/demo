package com.pusulait.multithreading.config;

import com.pusulait.multithreading.pool.alarmengine.AlarmScriptEngine;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.target.CommonsPool2TargetSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Slf4j
@Configuration
public class AlarmScriptEnginePoolConfig {

    public static final String POOL_OBJECT_NAME = "alarmScriptEnginePoolTargetSource";

    /* Script Engine Havuzu yonetilir */
    @Bean
    public CommonsPool2TargetSource alarmScriptEnginePoolTargetSource() throws Exception {
        CommonsPool2TargetSource commonsPoolTargetSource = new CommonsPool2TargetSource();
        commonsPoolTargetSource.setMinIdle(5);
        commonsPoolTargetSource.setMaxSize(16);
        commonsPoolTargetSource.setTimeBetweenEvictionRunsMillis(60 * 60 * 1000L);
        commonsPoolTargetSource.setTargetBeanName("alarmScriptEngine");
        commonsPoolTargetSource.setTargetClass(AlarmScriptEngine.class);
        return commonsPoolTargetSource;
    }

}
