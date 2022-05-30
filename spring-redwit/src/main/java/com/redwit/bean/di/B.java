package com.redwit.bean.di;

import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yangjiang
 * @create: 2022-03-09 17:19
 **/
@Component
public class B {

	public B() {
		System.out.println("b 被实例化了");
	}
}
