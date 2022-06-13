package com.redwit.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @description: 简单的测试下bean的生命周期
 * @author: yangjiang
 * @create: 2022-06-10 11:39
 **/
public class TestSimpleBeanLifeCycle {

	@Test
	public void testSimpleBeanLifeCycle() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanLifeCycleConf.class);
		applicationContext.close();

	}


	@Configuration
	@Import(BasicBeanPostProcessor.class)
	static class BeanLifeCycleConf {


		@Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
		public Car car() {
			return new Car();
		}

	}


	@Slf4j
	static class Car implements InitializingBean, DisposableBean {

		public Car() {
			log.debug("car constructor...");
		}

		/**
		 * 会在容器关闭的时候进行调用
		 */
		@Override
		public void destroy() {
			log.debug("car DisposableBean destroy...");
		}

		/**
		 * 会在bean创建完成，并且属性都赋好值以后进行调用
		 */
		@Override
		public void afterPropertiesSet() {
			log.debug("car InitializingBean  afterPropertiesSet...");
		}

		@PostConstruct
		public void initMethodPostConstruct() {
			log.debug("car...@PostConstruct...");
		}

		@PreDestroy
		public void destroyMethodPreDestroy() {
			log.debug("car...@PreDestroy...");
		}

		public void initMethod() {
			log.debug("@bean car initMethod");
		}

		public void destroyMethod() {
			log.debug("@bean car  destroyMethod ");
		}
	}


	static class BasicBeanPostProcessor implements BeanPostProcessor, Ordered {

		@Override
		public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
			System.out.println("postProcessBeforeInitialization..." + beanName + "=>" + bean);
			return bean;
		}

		@Override
		public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
			System.out.println("postProcessAfterInitialization..." + beanName + "=>" + bean);
			return bean;
		}

		@Override
		public int getOrder() {
			return 3;
		}

	}

}
