package com.srj.util.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

public class NewProxy {
	public  <T> T getProxyObject(T t,Class<?> invocationHandler){
		T proxy=null;
		try {
			proxy = (T) Proxy.newProxyInstance(t.getClass().getClassLoader(), t.getClass().getInterfaces(),(InvocationHandler)(invocationHandler.getConstructor(Object.class).newInstance(t)));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proxy;
	}
}
