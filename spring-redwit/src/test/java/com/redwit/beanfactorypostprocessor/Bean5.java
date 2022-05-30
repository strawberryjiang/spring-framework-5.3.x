package com.redwit.beanfactorypostprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author CandyWall
 * @Date 2022/3/29--12:42
 * @Description
 */
@Slf4j
@Component
public class Bean5 {
    public Bean5() {
        log.debug("我被 Spring 管理啦");
    }
}
