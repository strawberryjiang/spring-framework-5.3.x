package com.redwit.beanfactorypostprocessor;


import com.redwit.beanfactorypostprocessor.component.Bean2;
import com.redwit.beanpostprocessor.Bean1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * @Author CandyWall
 * @Date 2022/3/29--12:27
 * @Description
 */
@Configuration
@ComponentScan("com.redwit.beanfactorypostprocessor.component")
public class Config {



	@Bean
	public Bean1 bean1() {
		return new Bean1();
	}


	public Bean2 bean2() {
		return new Bean2();
	}

	@Bean
	public Bean5 bean5() {
		return new Bean5();
	}

}
