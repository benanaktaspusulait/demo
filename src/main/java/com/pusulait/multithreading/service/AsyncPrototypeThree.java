package com.pusulait.multithreading.service;

import com.pusulait.multithreading.pool.alarmengine.AlarmScriptEngine;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@Data
@Slf4j
public class AsyncPrototypeThree {

    private AtomicInteger counter = new AtomicInteger();
    private AtomicInteger counter2 = new AtomicInteger();

    @Autowired
    private MeterRegistry meterRegistry;
    @Autowired
    private ApplicationContext context;

    @Async("alarmEngineThreadPool")
    @Timed("AsyncPrototypeThreeRun001")
    public void run() {
        try {
            Thread.sleep(25L);
            counter.addAndGet(1);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.info("******* AsyncPrototypeThree#run ->  RUN : " + Thread.currentThread().getId() + " - c: " + counter.get());
    }

    //@Async("alarmEngineThreadPool")
    public void run2() {
        Timer timer = Timer.builder("AsyncPrototypeThreeRun002").register(meterRegistry);

        try {
            timer.record(() -> {
                try {
                    AlarmScriptEnginePoolService alarmScriptEnginePoolService = context.getBean(AlarmScriptEnginePoolService.class);
                    AlarmScriptEngine alarmScriptEngine = alarmScriptEnginePoolService.getScriptEngine();
                    alarmScriptEnginePoolService.releaseScriptEngine();
                    alarmScriptEnginePoolService.destroyScriptEngine();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
                counter2.addAndGet(1);
            });

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.info("******* AsyncPrototypeThree#run2 ->  RUN : " + Thread.currentThread().getId() + " - c2: " + counter2.get());
    }

}
