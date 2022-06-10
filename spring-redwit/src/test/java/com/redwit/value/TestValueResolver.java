package com.redwit.value;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.core.env.ConfigurableEnvironment;

import java.lang.reflect.Field;

/**
 * @description:
 * @author: yangjiang
 * @create: 2022-06-02 10:32
 **/
@Configuration
public class TestValueResolver {

	@Test
	public void testValue() throws NoSuchFieldException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();
		ContextAnnotationAutowireCandidateResolver resolver = new ContextAnnotationAutowireCandidateResolver();
		resolver.setBeanFactory(beanFactory);

		test01(context, resolver,"javaHome");
		test02(context, resolver,"age");

	}

	private void test01(AnnotationConfigApplicationContext context, ContextAnnotationAutowireCandidateResolver resolver,String fieldName) throws NoSuchFieldException {
		Field field = Bean1.class.getDeclaredField(fieldName);
		DependencyDescriptor dd = new DependencyDescriptor(field, false);
		String value = resolver.getSuggestedValue(dd).toString();
		System.out.println(value);
		ConfigurableEnvironment environment = context.getEnvironment();
		value= environment.resolvePlaceholders(value);
		System.out.println(value);
	}

	private void test02(AnnotationConfigApplicationContext context, ContextAnnotationAutowireCandidateResolver resolver,String fieldName) throws NoSuchFieldException {
		Field field = Bean1.class.getDeclaredField(fieldName);
		DependencyDescriptor dd = new DependencyDescriptor(field,false);
		String value = resolver.getSuggestedValue(dd).toString();
		System.out.println(value);
		ConfigurableEnvironment environment = context.getEnvironment();
		value= environment.resolvePlaceholders(value);
		System.out.println(value);
		System.out.println(value.getClass());
		Object age = context.getBeanFactory().getTypeConverter().convertIfNecessary(value, dd.getDependencyType());
		System.out.println(age.getClass());
	}

	public class Bean1 {

		@Value("${JAVA_HOME}")
		private String javaHome;
		@Value("18")
		private int age;
	}
}
