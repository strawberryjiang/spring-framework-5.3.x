package com.redwit.beanDefinition;//package com.redwit.beanDefinition;
//
//import com.redwit.bean.beanDefinition.X;
//import com.redwit.bean.beanDefinition.Y;
//import com.redwit.bean.Z;
//import com.redwit.config.beanDefinition.AppConfig;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
///**
// * @description:
// * @author: yangjiang
// * @create: 2022-03-09 15:04
// **/
//public class BeanDefinitionTest {
//
//
//	@Before
//	public void test(){
//		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
//		ac.register(AppConfig.class);
//		ac.refresh();
//	}
//
//	/**
//	 * @author yangjiang
//	 * @creed: 测试beanFactory中的beanDefinitionMap中合适放入我们自定义的bean
//	 * @date 2022/3/9 15:33
//	 */
//	@Test
//	public void testBeanFactoryBeanDefinition(){
//		System.out.println("start");
//		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
//		ac.register(AppConfig.class);
//		ac.refresh();
//	}
//
//
//	/**
//	 * @author yangjiang
//	 * @creed: 改变beanDefinitionMap中bean的定义
//	 * @date 2022/3/9 15:36
//	 */
//	@Test
//	public void testChangeBeanByBeanPostProcessor(){
//		AnnotationConfigApplicationContext ac =
//				new AnnotationConfigApplicationContext();
//		ac.register(AppConfig.class);
//		ac.refresh();
//		//正常打印
//		System.out.println(ac.getBean(Y.class));
//		//正常打印
//		System.out.println(ac.getBean(Z.class));
//		//异常打印
//		//虽然X加了注解，但是被偷梁换柱了，故而异常
//		System.out.println(ac.getBean(X.class));
//
//	}
//
//}
