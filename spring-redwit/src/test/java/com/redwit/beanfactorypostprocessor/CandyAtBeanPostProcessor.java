package com.redwit.beanfactorypostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.util.Set;

/**
 * @description:
 * @author: yangjiang
 * @create: 2022-05-27 17:28
 **/
public class CandyAtBeanPostProcessor implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanFactory) throws BeansException {
		try {
			CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
			MetadataReader reader = factory.getMetadataReader(new ClassPathResource("com/redwit/beanfactorypostprocessor/Config.class"));
			Set<MethodMetadata> annotatedMethods = reader.getAnnotationMetadata().getAnnotatedMethods(Bean.class.getName());
			for (MethodMetadata annotatedMethod : annotatedMethods) {
				//之所所能这样写，而不必担心get("initMethod").toString()NPE的问题，是因为，默认有init-method等五个方法
				String initMethod = annotatedMethod.getAnnotationAttributes(Bean.class.getName()).get("initMethod").toString();

				// 这里不需要指定类名了，因为最终的BeanDefinition是Config类中加了@Bean属性的方法的返回值的类型的定义。
				BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
				builder.setFactoryMethodOnBean(annotatedMethod.getMethodName(), "config");
				builder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
				if (initMethod.length() > 0) {
					builder.setInitMethodName(initMethod);
				}
				AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
				beanFactory.registerBeanDefinition(annotatedMethod.getMethodName(), beanDefinition);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
	}

}
