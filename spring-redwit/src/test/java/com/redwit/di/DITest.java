package com.redwit.di;

import com.redwit.config.di.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description: 依赖注入的测试
 * @author: yangjiang
 * @create: 2022-02-25 16:56
 **/
public class DITest {

	/**
	 * @author yangjiang
	 * @creed: 使用@Autowired注解注入ApplicationContext
	 * @date 2022/3/9 17:22
	 */
	@Test
	public void testAutowiredApplicationContext() {
		AnnotationConfigApplicationContext ac
				= new AnnotationConfigApplicationContext(AppConfig.class);

		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		//打印spring容器当中所有bean的bd
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName);
		}
	}


}
