package com.redwit.beanfactory;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @description: beanFactory 不会做的事
 * <p>
 * 不会主动调用BeanFactory的后处理器
 * <p>
 * 不会主动添加Bean的后处理器
 * <p>
 * 不会主动初始化单例
 * <p>
 * 不会解析BeanFactory，还不会解析 ${}, #{}
 * @author: yangjiang
 * @create: 2022-05-23 11:23
 **/
public class TestBeanFactory {

	@Test
	public  void testBeanFactoryImpl() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Config.class).setScope("singleton").getBeanDefinition();
		beanFactory.registerBeanDefinition("config", beanDefinition);
		//给beanFactory添加后置处理器
		AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
		// 从bean工厂中取出BeanFactory的后处理器，并且执行这些后处理器
		// BeanFactory 后处理器主要功能，补充了一些 bean 的定义
		beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values().forEach(beanFactoryPostProcessor -> {
			beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
		});
//		for (String name : beanFactory.getBeanDefinitionNames()) {
//			System.out.println(name);
//		}

		// 从BeanFactory中取出Bean1，然后再从Bean1中取出它依赖的Bean2
		// 可以看到结果为null，所以@Autowired注解并没有被解析
		//Bean1 bean1 = beanFactory.getBean(Bean1.class);
		//System.out.println(bean1.getBean2());

		// 要想@Autowired、@Resource等注解被解析，还要添加Bean的后处理器，可以针对Bean的生命周期的各个阶段提供扩展
		// 从bean工厂中取出Bean的后处理器，并且执行这些后处理器
		// BeanFactory 后处理器主要功能，补充了一些 bean 的定义
		beanFactory.getBeansOfType(BeanPostProcessor.class).values().forEach(beanPostProcessor -> {
					System.out.println(beanPostProcessor);
					beanFactory.addBeanPostProcessor(beanPostProcessor);
				});

		// 准备好所有单例，get()前就把对象初始化好
		//这边注意了，因为这个版本是spring5.0x，此方法调用了smartSingleton.afterSingletonsInstantiated();
		//afterSingletonsInstantiated.getEventListenerFactories.getApplicationContext()为null，因为这边用了beanFactory而没有用applicationContext
		//并且没有调用applicationContextAware导致EventListenerMethodProcessor implements SmartInitializingSingleton, ApplicationContextAware 中的
		//context为null，但是在高版本中,ConfigurableListableBeanFactory beanFactory = this.beanFactory;
		beanFactory.preInstantiateSingletons();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		Bean1 bean1 = beanFactory.getBean(Bean1.class);
		//优先级高的先执行
		System.out.println(bean1.getInter());

	}



	@Configuration
	static class Config {
		@Bean
		public Bean1 bean1() {
			return new Bean1();
		}

		@Bean
		public Bean2 bean2() {
			return new Bean2();
		}

		@Bean
		public Bean3 bean3() {
			return new Bean3();
		}

		@Bean
		public Bean4 bean4() {
			return new Bean4();
		}
	}

	@Slf4j
	static class Bean1 {
		@Autowired
		private Bean2 bean2;

		public Bean2 getBean2() {
			return bean2;
		}

		@Autowired
		@Resource(name = "bean4")
		private Inter bean3;

		public Inter getInter() {
			return bean3;
		}

		public Bean1() {
			log.debug("构造 Bean1()");
		}
	}

	@Slf4j
	static class Bean2 {
		public Bean2() {
			log.debug("构造 Bean2()");
		}
	}

	interface Inter {

	}

	@Slf4j
	static class Bean3 implements Inter {
		public Bean3() {
			log.debug("构造 Bean3()");
		}
	}

	@Slf4j
	static class Bean4 implements Inter {
		public Bean4() {
			log.debug("构造 Bean4()");
		}
	}

}
