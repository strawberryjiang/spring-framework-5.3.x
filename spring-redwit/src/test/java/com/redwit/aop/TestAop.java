package com.redwit.aop;

import com.redwit.aop.service.MyService;
import org.junit.jupiter.api.Test;

/**
 * @description:
 * @author: yangjiang
 * @create: 2022-05-30 11:36
 **/
public class TestAop {

	@Test
	public void testAopAjc() {
		new MyService().foo();
	}
}
