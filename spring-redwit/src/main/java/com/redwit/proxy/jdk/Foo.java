package com.redwit.proxy.jdk;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface Foo {
	void foo();
	int bar();
}

class Target implements Foo {
	@Override
	public void foo() {
		System.out.println("target foo");
	}

	@Override
	public int bar() {
		System.out.println("target bar");
		return 100;
	}
}

 interface InvocationHandler {
	Object invoke(Object proxy,Method method, Object[] args) throws InvocationTargetException, IllegalAccessException;
}