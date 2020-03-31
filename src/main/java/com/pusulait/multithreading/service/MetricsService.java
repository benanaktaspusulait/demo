package com.pusulait.multithreading.service;

import io.micrometer.core.annotation.Timed;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Slf4j
@Service
public class MetricsService {

    @Autowired
    private AsyncPrototypeThree asyncPrototypeThree;

    //@Async
    @Timed("AsyncPrototypeThreeRun000")
    public void runMetricsFunc() throws Exception {
        log.info("*********** 1 ***** 1");
        asyncPrototypeThree.run();
        asyncPrototypeThree.run2();
        log.info("*********** 2 ***** 2");
    }

}
