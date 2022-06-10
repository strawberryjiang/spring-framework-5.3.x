package com.redwit.proxy.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

/**
 * @description: jdk动态代理:代理对象与目标对象都需要实现同一个接口
 * @author: yangjiang
 * @create: 2022-05-30 14:32
 **/
public class JdkProxyDemo {

	@Test
	public void testJdkProxy() {
		// jdk的动态代理，只能针对接口代理
		// 目标对象
		Target target = new Target();
		// 用来加载在运行期间动态生成的字节码
		ClassLoader loader = JdkProxyDemo.class.getClassLoader();
		Foo fooProxy = (Foo) Proxy.newProxyInstance(loader, new Class[]{Foo.class}, (proxy, method, args) -> {
			System.out.println("before...");
			Object result = method.invoke(target, args);
			System.out.println("after...");
			return result;  // 让代理也返回目标方法执行的结果
		});

		fooProxy.foo();
	}
}

interface Foo {
	void foo();
}

@Slf4j
class Target implements Foo {
	public void foo() {
		log.debug("target foo");
	}
}

