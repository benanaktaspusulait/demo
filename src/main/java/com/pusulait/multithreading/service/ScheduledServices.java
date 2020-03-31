package com.pusulait.multithreading.service;

import com.pusulait.multithreading.pool.alarmengine.AlarmScriptEngine;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@Data
@Slf4j
public class ScheduledServices {

    @Autowired
    private AsyncServices asyncServices;

    @Autowired
    final private ApplicationContext context;

    //@Scheduled(initialDelay = 100L, fixedRate = 1 * 1)
    void schedule01() throws InterruptedException {
        asyncServices.async01();
        log.info("schedule01 -> Runned!");
    }

    //@Scheduled(initialDelay = 250L, fixedRate = 1 * 1000)
    void schedule02() throws InterruptedException {
        asyncServices.async02();
        log.info("schedule02 -> Runned!");
    }

    //@Timed
    //@Scheduled(initialDelay = 0L, fixedRate = 1 * 1000L)
    public void schedule03() throws Exception {
        while (true) {
            final Runnable runnable = () -> {
                // Note: the name is required as both "foo" and "fooTarget" will match class Foo.
                AlarmScriptEnginePoolService alarmScriptEnginePoolService = null;
                AlarmScriptEngine alarmScriptEngine = null;
                try {
                    alarmScriptEnginePoolService = context.getBean(AlarmScriptEnginePoolService.class);
                    alarmScriptEngine = alarmScriptEnginePoolService.getScriptEngine();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                log.info(Thread.currentThread() + " - o: " + alarmScriptEngine.getKey());
                try {
                    alarmScriptEnginePoolService.releaseScriptEngine();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };

            for (int i = 0; i < 25; i++) {
                new Thread(runnable).start();
            }
            runnable.run();

            log.info("alarm engine: ");
            Thread.sleep(1000L);
        }
    }


    //@Scheduled(fixedRate = 1000L)
    public void asyncCallTest() {
        final List<String> strings = new LinkedList<>();
        strings.add("sfsf");
        //for(int i = 0 ; i<=5; i++){
        new Thread(new Runnable() {
            @Override
            public void run() {
                strings.stream().forEach(x -> {
                    context.getBean(AsyncPrototypeOne.class).run();
                });
            }
        }).run();
//        }
        log.info("Scheduler Async Test Run -> " + Thread.currentThread().getId());
    }

    @Autowired
    private MetricsService metricsService;

    @Scheduled(fixedRate = 1000L)
    public void metricsTest() {
        try {
            log.info("******* 1");
            metricsService.runMetricsFunc();
            log.info("******* 2");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
