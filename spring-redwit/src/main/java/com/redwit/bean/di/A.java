package com.redwit.bean.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yangjiang
 * @create: 2022-03-09 17:19
 **/
@Component
public class A{

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private B b;


	public A() {
		System.out.println("a 被实例化了");
	}


}

