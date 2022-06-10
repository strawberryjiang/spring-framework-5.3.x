package com.redwit.aop.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @description:
 * @author: yangjiang
 * @create: 2022-05-30 11:44
 **/
@Aspect // ⬅️注意此切面并未被 Spring 管理，本项目pom文件中根本没有引入spring的相关类
@Slf4j
public class MyAspect {

	@Before("execution(com.redwit.aop.service.MyService.*)")
	public void before() {
		log.debug("before()");
	}
}

