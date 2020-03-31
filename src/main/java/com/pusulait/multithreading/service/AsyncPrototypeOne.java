package com.pusulait.multithreading.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
@Data
@Slf4j
public class AsyncPrototypeOne {
    /*@Autowired
    private AsyncPrototypeTwo asyncPrototypeTwo;*/
    @Autowired
    private ApplicationContext applicationContext;

    @Async
    public void run() {
        log.info("******* AsyncPrototypeOne#run ->  RUN (AND Call  AsyncPrototypeTwo#run ): " + Thread.currentThread().getId());
        applicationContext.getBean(AsyncPrototypeTwo.class).run();
    }
}
