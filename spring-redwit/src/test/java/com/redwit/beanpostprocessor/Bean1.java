package com.redwit.beanpostprocessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @description:
 * @author: yangjiang
 * @create: 2022-05-24 16:45
 **/
public class Bean1 {

	private Bean2 bean2;

	@Autowired
	public void setBean2(Bean2 bean2) {
		System.out.println("@Autowired 生效：{}" + bean2);
		this.bean2 = bean2;
	}

	@Autowired
	public void setJava_home(@Value("${JAVA_HOME}") String java_home) {
		System.out.println("@Value 生效：{}" + java_home);
		this.java_home = java_home;
	}

	@Autowired
	private Bean3 bean3;

	@Resource
	public void setBean3(Bean3 bean3) {
		System.out.println("@Resource 生效：{}" + bean3);
		this.bean3 = bean3;
	}

	private String java_home;

	@PostConstruct
	public void init() {
		System.out.println("@PostConstruct 生效：{}");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("@PreDestroy 生效：{}");
	}

	@Override
	public String toString() {
		return "Bean1{" +
				"bean2=" + bean2 +
				", bean3=" + bean3 +
				", java_home='" + java_home + '\'' +
				'}';
	}




}
