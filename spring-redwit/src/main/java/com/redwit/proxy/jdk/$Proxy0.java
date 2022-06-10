package com.redwit.proxy.jdk;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @description:
 * @author: yangjiang
 * @create: 2022-05-30 16:19
 **/
public class $Proxy0 implements Foo {

	static Method foo;
	static Method bar;

	static {
		try {
			foo = Foo.class.getDeclaredMethod("foo");
			bar = Foo.class.getDeclaredMethod("bar");
		} catch (NoSuchMethodException e) {
			throw new NoSuchMethodError(e.getMessage());
		}
	}


	public InvocationHandler h;

	public $Proxy0(InvocationHandler h) {
		this.h = h;
	}

	@Override
	public void foo() {
		try {
			Method foo = Foo.class.getDeclaredMethod("foo");
			h.invoke(this, foo, new Object[0]);
		} catch (RuntimeException | Error e) {
			throw e;
		} catch (Throwable e) {
			throw new UndeclaredThrowableException(e);
		}


	}

	@Override
	public int bar() {
		try {
			Method bar = Foo.class.getDeclaredMethod("bar");
			return (int)h.invoke(this, bar, new Object[0]);
		} catch (RuntimeException | Error e) {
			throw e;
		} catch (Throwable e) {
			throw new UndeclaredThrowableException(e);
		}

	}


	public static void main(String[] args) {
		Foo proxy = new $Proxy0(new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
				// 1. 功能增强
				System.out.println("before...");
				// 2. 调用目标
				return method.invoke(new Target(), args);
			}
		});
		proxy.foo();
		proxy.bar();
	}
}
