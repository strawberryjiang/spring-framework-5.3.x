package com.redwit.beanfactorypostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @description:
 * @author: yangjiang
 * @create: 2022-05-27 15:51
 **/
public class ComponentScanBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanFactory) throws BeansException {
		try {
			ComponentScan componentScan = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);
			if (componentScan != null) {
				for (String basePage : componentScan.basePackages()) {
					String path = "classpath*:" + basePage.replace('.', '/') + "/**/*.class";
					CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
					AnnotationBeanNameGenerator generator = new AnnotationBeanNameGenerator();
					for (Resource resource : new PathMatchingResourcePatternResolver().getResources(path)) {
						// 查看对应的类上是否有@Component注解
						MetadataReader reader = factory.getMetadataReader(resource);
						String className = reader.getClassMetadata().getClassName();
						String name = Component.class.getName();
						AnnotationMetadata annotationMetadata = reader.getAnnotationMetadata();

						// 如果直接或者间接加了@Component注解
						if (annotationMetadata.hasAnnotation(Component.class.getName()) || annotationMetadata.hasMetaAnnotation(name)) {
							// 创建Bean的定义
							AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(className).getBeanDefinition();
							String beanName = generator.generateBeanName(beanDefinition, beanFactory);
							// 将Bean定义加入工厂
							beanFactory.registerBeanDefinition(beanName, beanDefinition);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	// context.refresh()中会回调该方法
	public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

	}

}
