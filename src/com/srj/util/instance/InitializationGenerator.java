package com.srj.util.instance;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Random;

import com.srj.util.test.Test;

/**
 * 产生一个对象实例，并给对象的属性随机赋值
 * 说明：暂时只能对实例里面的基本数据类型随机赋值，如果是对象类型的则使用
 * 该对象类型的默认构造函数创建出一个对象赋值，暂时无法对数组随机填充值
 * 
 * @author SuRongjian
 * @createDate 2013年8月23日 上午9:20:00
 * @version 1.0
 * @Description TODO
 */
public class InitializationGenerator {
	public static <T>  T newInitInstance(Class<T> clazz){
		T t=null;
		try {
			t=clazz.newInstance();
			Field fields []= clazz.getDeclaredFields();
			for(Field f:fields){
				Class c=f.getType();
				f.setAccessible(true);
				System.out.println(c.getName()+"     is array  "+c.isArray());
				
				if(c.isArray()){
//					如果是数组就暂时不处理
					
//					Object array=Array.newInstance(c.getComponentType(), 20);
//					System.out.println(array);
//					f.set(t,(Test[])array);
				}else{
					Random random=new Random();
					int value=((int)(System.currentTimeMillis()%10000d))+random.nextInt(10000);
					
					if(c.getName()=="int"){
						f.set(t, value);
					}else if(c.getName()=="double"){
						f.set(t, value);
					}else if(c.getName()=="float"){
						f.set(t, value);
					}else if(c.getName()=="boolean"){
						f.set(t, value%2==0?true:false);
					}else if(c.getName()=="long"){
						f.set(t, value);
					}else if(c.getName()=="byte"){
						f.set(t, (byte)value);
					}else if(c.getName()=="short"){
						f.set(t, (short)value);
					}else if(c.getName()=="char"){
						f.set(t, (char)(value%26+'a'));
					}else if(c.getName()==Integer.class.getName()){
						f.set(t, new Integer(value));
					}else if(c.getName()==Double.class.getName()){
						f.set(t, new Double(value));
					}else if(c.getName()==Float.class.getName()){
						f.set(t, new Float(value));
					}else if(c.getName()==Boolean.class.getName()){
						f.set(t, new Boolean(value%2==0?true:false));
					}else if(c.getName()==Long.class.getName()){
						f.set(t, new Long(value));
					}else if(c.getName()==Byte.class.getName()){
						f.set(t, new Byte((byte)value));
					}else if(c.getName()==Short.class.getName()){
						f.set(t, new Short((short) value));
					}else if(c.getName()==Character.class.getName()){
						f.set(t, new Character((char)(value%26+'a')));
					}else if(c.getName()==String.class.getName()){
						StringBuffer sb=new StringBuffer();
						for(int i=0;i<5;i++)
							sb.append((char)(random.nextInt(26)+'a'));
						f.set(t,sb.toString());
					}else {
						f.set(t, c.newInstance());
					}
				}
				
				
				
			}
		} catch (InstantiationException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			// TODO: handle exception
			e.printStackTrace();
		};
		return t;
	}
	
	public static void main(String[] args) {
		Test t=newInitInstance(Test.class);
		System.out.println(t);
		//String [] s={"a","b"};
		//System.out.println(s.getClass());
//		System.out.println(new int[3][2]);
//		System.out.println(Array.newInstance(int.class, 3));
//		
//		int dims[]={5,10,15};
//		Object arr=Array.newInstance(Integer.TYPE,dims);
//		Object arrobj=Array.get(arr,3);
//		
//		
//		System.out.println(arr);
//		System.out.println(arrobj);
//		System.out.println(arrobj.getClass().getComponentType().getSimpleName());
//		
	}
}
