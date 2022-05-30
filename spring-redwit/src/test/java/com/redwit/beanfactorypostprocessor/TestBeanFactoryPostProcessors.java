package com.redwit.beanfactorypostprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

/**
 * @description:TestBeanFactoryPostProcessors
 * @author: yangjiang
 * @create: 2022-05-27 11:13
 **/
@Slf4j
public class TestBeanFactoryPostProcessors {

	@Test
	public void testBeanPostProcessors() throws IOException {
		// ⬇️GenericApplicationContext 是一个【干净】的容器，默认不会添加任何后处理器，方便做测试
		GenericApplicationContext context = new GenericApplicationContext();
		context.registerBean("config",Config.class);
		// 解析@ComponentScan、@Bean、@Import、@ImportResource注解
//		context.registerBean(ConfigurationClassPostProcessor.class);
		// 添加Bean工厂后处理器MapperScannerConfigurer，解析@MapperScan注解
//		context.registerBean(MapperScannerConfigurer.class, beanDefinition -> {
//			// 指定扫描的包名
//			beanDefinition.getPropertyValues().add("basePackage", "com.redwit.mapper");
//		});
		// 把自定义组件扫描Bean工厂后处理器加进来
		context.registerBean(ComponentScanBeanFactoryPostProcessor.class);

		// ⬇️初始化容器
		context.refresh();

		for (String name : context.getBeanDefinitionNames()) {
			System.out.println(name);
		}

		// ⬇️销毁容器
		context.close();
	}

	@Test
	// 模拟实现@Bean注解的解析
	public void testMockAtBeanAnnotation() throws Exception {
		// ⬇️GenericApplicationContext 是一个【干净】的容器，默认不会添加任何后处理器，方便做测试
		GenericApplicationContext context = new GenericApplicationContext();

		context.registerBean("config", Config.class);
		context.registerBean(CandyAtBeanPostProcessor.class);

		// ⬇️初始化容器
		context.refresh();

		for (String name : context.getBeanDefinitionNames()) {
			System.out.println(name);
		}

		// ⬇️销毁容器
		context.close();
	}

}
