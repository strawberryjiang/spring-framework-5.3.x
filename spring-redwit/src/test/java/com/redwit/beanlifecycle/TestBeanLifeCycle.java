package com.redwit.beanlifecycle;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @description:
 * @author: yangjiang
 * @create: 2022-05-24 11:25
 **/
@Slf4j
public class TestBeanLifeCycle {

	@Test
	public void testBeanLifeCycle() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
		Config config = (Config)applicationContext.getBean("config");
		applicationContext.close();

	}
}

// 单元测试的过程中如果要解析一些Spring注解，比如@Configuration的时候不要把相关类定义到写单元测试类的内部类，会读取不到
//@Configuration
class Config {
	public Config(){
		System.out.println("construct ... config");
	}

	@Bean
	public LifeCycleBean lifeCycleBean() {
		return new LifeCycleBean();
	}
	@Bean
	public MyBeanPostProcessor myBeanPostProcessor() {
		return new MyBeanPostProcessor();
	}
}


@Slf4j
class LifeCycleBean {
	public LifeCycleBean() {
		System.out.println("构造");
	}

	@Autowired
	public void autowire(@Value("${JAVA_HOME}") String name) {
		System.out.println("依赖注入：{}" + name);
	}

	@PostConstruct
	public void init() {
		System.out.println("初始化");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("销毁");
	}
}

@Slf4j
class MyBeanPostProcessor implements InstantiationAwareBeanPostProcessor, DestructionAwareBeanPostProcessor {
	@Override
	// 实例化前（即调用构造方法前）执行的方法
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		if (beanName.equals("lifeCycleBean"))
			System.out.println("<<<<<<<<<<< 实例化前执行，如@PreDestroy");
		// 返回null保持原有对象不变，返回不为null，会替换掉原有对象
		return null;
	}

	@Override
	// 实例化后执行的方法
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		if (beanName.equals("lifeCycleBean")) {
			System.out.println("<<<<<<<<<<< 实例化后执行，这里如果返回 false 会跳过依赖注入阶段");
			// return false;
		}

		return true;
	}

	// 依赖注入阶段执行的方法



	@Override
	// 销毁前执行的方法
	public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
		if (beanName.equals("lifeCycleBean"))
			System.out.println("<<<<<<<<<<<销毁之前执行");
	}

	@Override
	// 初始化之前执行的方法
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.equals("lifeCycleBean"))
			System.out.println("<<<<<<<<<<< 初始化之前执行，这里返回的对象会替换掉原本的bean，如 @PostConstruct、@ConfigurationProperties");
		return bean;
	}

	@Override
	// 初始化之后执行的方法
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.equals("lifeCycleBean"))
			System.out.println("<<<<<<<<<<< 初始化之后执行，这里返回的对象会替换掉原本的bean，如 代理增强");
		return bean;
	}


}
