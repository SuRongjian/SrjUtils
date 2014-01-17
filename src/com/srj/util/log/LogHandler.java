package com.srj.util.log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.logging.Logger;


public class LogHandler implements InvocationHandler {
	
	private Logger logger=Logger.getLogger(this.getClass().getName()); 
	private Object delegate; 
	public LogHandler(Object delegate){ 
		this.delegate = delegate; 
	}
	
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object o = null;
		try {
			logger.info("SRJ  ########   method stats..." + method);
			o = method.invoke(delegate, args);
			logger.info("SRJ  ########   method ends..." + method);
		} catch (Exception e) {
			//logger.warn("方法异常啦！！！",e);
			logger.info(e.toString());

		}
		return o;
	}

}
