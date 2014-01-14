package com.srj.util.instance;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;


public class InstancePool {
	private Map _classPool;

	private static InstancePool _instance;

	private InstancePool() {
		_classPool = CollectionFactory.getHashMap();
	}

	private static InstancePool _getInstance() {
		if (_instance == null) {
			synchronized (InstancePool.class) {
				if (_instance == null) {
					_instance = new InstancePool();
				}
			}
		}

		return _instance;
	}

	public static Object get(String className) {
		return _getInstance()._get(className);
	}

	@SuppressWarnings("unchecked")
	private Object _get(String className) {
		className = className.trim();
		Object obj = _classPool.get(className);
		if (obj == null) {
			try {
				Class clazz = Class.forName(className);
				obj = instantiateClass(clazz);
				_put(className, obj);
			} catch (ClassNotFoundException cnofe) {
			}
		}

		return obj;
	}

	public static void put(String className, Object obj) {
		_getInstance()._put(className, obj);
	}

	private void _put(String className, Object obj) {
		_classPool.put(className, obj);
	}

	/**
	 * 实例化带默认构造方法的bean,此方法是从org.springframework.beans.BeanUtils里提取出来的
	 * 
	 * @param clazz
	 * @return
	 */
	private static Object instantiateClass(Class clazz) {
		Object object = null;
		try {
			object = instantiateClass(clazz.getDeclaredConstructor((Class[]) null), null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return object;
	}

	/**
	 * 根据构造方法实例化bean ,此方法是从org.springframework.beans.BeanUtils里提取出来的
	 * 
	 * @param ctor
	 * @param args
	 * @return
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static Object instantiateClass(Constructor ctor, Object[] args) throws IllegalArgumentException,
			InstantiationException, IllegalAccessException, InvocationTargetException {
		if (!Modifier.isPublic(ctor.getModifiers()) || !Modifier.isPublic(ctor.getDeclaringClass().getModifiers())) {
			// 如果是私有构造方法则修改访问权限
			ctor.setAccessible(true);
		}
		// 反射实例
		return ctor.newInstance(args);
	}
}

class CollectionFactory{
	public static Map getHashMap() {
		return new HashMap();
	}
}
