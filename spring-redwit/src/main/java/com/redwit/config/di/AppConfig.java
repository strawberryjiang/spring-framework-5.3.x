package com.redwit.config.di;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: yangjiang
 * @create: 2022-02-25 16:55
 **/
@Configuration
@ComponentScan({"com.redwit.bean.di"})
public class AppConfig {
//扫描com.shadow包下面的所有bean
}

