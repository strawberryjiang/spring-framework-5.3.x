package com.redwit.listen;

import com.redwit.listen.bean.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description: 事件监听
 * @author: yangjiang
 * @create: 2022-06-02 14:26
 **/
public class TestListener {

	@Test
	public void test01(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Conf.class);
		context.getBean(MyService.class).doBusiness();
	}


}
