package com.pusulait.multithreading.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
@Data
@Slf4j
public class AsyncPrototypeTwo {

    @Async("alarmEngineThreadPool")
    public void run() {
        try {
            Thread.sleep(25L);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.info("******* AsyncPrototypeTwo#run ->  RUN : " + Thread.currentThread().getId());
    }
}
