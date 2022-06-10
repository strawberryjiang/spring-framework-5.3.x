package com.redwit.inject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @description: test usage of import
 * @author: yangjiang
 * @create: 2022-06-09 16:38
 **/
public class TestImport {

	@Test
	public void testBasicImport() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BasicImportClass.class);
		assertThat(Arrays.asList(context.getBeanDefinitionNames())).contains("com.redwit.inject.TestImport$Color");
	}

	@Test
	public void testImportSelector() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BasicImportClass.class);
		assertThat(Arrays.asList(context.getBeanDefinitionNames())).contains("com.redwit.inject.TestImport$Blue", "com.redwit.inject.TestImport$Red");
	}

	@Test
	public void testImportBeanDefinitionRegistrar() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BasicImportClass.class);
		assertThat(Arrays.asList(context.getBeanDefinitionNames())).contains("rainbow");
	}


	@Configuration
	@Import({Color.class, BasicImportSelector.class,BasicImportBeanDefinitionRegistrar.class})
	static class BasicImportClass {

	}

	static class Color {

	}

	static class BasicImportSelector implements ImportSelector {

		@Override
		public String[] selectImports(AnnotationMetadata importingClassMetadata) {
			return new String[]{"com.redwit.inject.TestImport$Blue", "com.redwit.inject.TestImport$Red"};
		}
	}

	static class Blue {

	}

	static class Red {

	}


	static class BasicImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

		@Override
		public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
			boolean definition = registry.containsBeanDefinition("com.redwit.inject.TestImport$Blue");
			boolean definition2 = registry.containsBeanDefinition("com.redwit.inject.TestImport$Red");
			if (definition && definition2) {
				RootBeanDefinition beanDefinition = new RootBeanDefinition(RainBow.class);
				// 注册一个bean，并且指定bean的名称
				registry.registerBeanDefinition("rainbow", beanDefinition);
			}
		}

	}

	static class RainBow {

	}
}
