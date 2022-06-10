package com.redwit.listen.bean;

import org.springframework.context.ApplicationEvent;

/**
 * @description:
 * @author: yangjiang
 * @create: 2022-06-02 14:40
 **/
public class MyEvent extends ApplicationEvent {

	public MyEvent(Object source) {
		super(source);
	}
}
