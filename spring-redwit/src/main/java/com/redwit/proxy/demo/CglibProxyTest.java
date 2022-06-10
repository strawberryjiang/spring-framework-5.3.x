package com.redwit.proxy.demo;

import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * @description:
 * @author: yangjiang
 * @create: 2022-05-30 15:38
 **/
public class CglibProxyTest {

	@Test
	public void testCglibProxy1() {
		// 目标对象
		Target target = new Target();
		Foo fooProxy = (Foo) Enhancer.create(Target.class, (MethodInterceptor) (obj, method, args, proxy) -> {
			System.out.println("before...");
			Object result = method.invoke(target, args); // 用方法反射调用目标
			System.out.println("after...");
			return result;
		});
		System.out.println(fooProxy.getClass());

		fooProxy.foo();
	}

	@Test
	public void testCglibProxy2() {
		// 目标对象
		Target target = new Target();
		Foo fooProxy = (Foo) Enhancer.create(Target.class, (MethodInterceptor) (obj, method, args, methodProxy) -> {
			System.out.println("before...");
			// proxy 它可以避免反射调用
			Object result = methodProxy.invoke(target, args); // 需要传目标类
			System.out.println("after...");
			return result;
		});
		System.out.println(fooProxy.getClass());

		fooProxy.foo();
	}

	@Test
	public void testCglibProxy3() {
		// 目标对象
		Foo fooProxy = (Foo) Enhancer.create(Target.class, (MethodInterceptor) (obj, method, args, methodProxy) -> {
			System.out.println("before...");
			// proxy 它可以避免反射调用
			Object result = methodProxy.invokeSuper(obj, args); // 不需要目标类，需要代理自己
			System.out.println("after...");
			return result;
		});
		System.out.println(fooProxy.getClass());

		fooProxy.foo();
	}

}