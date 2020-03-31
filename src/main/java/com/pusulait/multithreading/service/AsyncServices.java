package com.pusulait.multithreading.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Data
@Slf4j
@Service
public class AsyncServices {

    @Async
    public void async01() throws InterruptedException {
        Thread.sleep(10L);
        log.info("async01 (DEFAULT) -> Runned!");
    }

    @Async("alarmEngineThreadPool")
    public void async02() throws InterruptedException {
        Thread.sleep(20L);
        log.info("async02 (alarmEngineThreadPool) -> Runned!");
    }

    //@Scheduled(initialDelay = 1L, fixedRate = 24 * 60 * 60 * 1000)
    public void schedule() throws InterruptedException {
        while (true) {
            async01();
            async02();
        }
    }

}
