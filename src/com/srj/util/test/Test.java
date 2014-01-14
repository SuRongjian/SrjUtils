package com.srj.util.test;

import java.util.ArrayList;
import java.util.List;

import com.srj.util.instance.InstancePool;

public class Test {
	private Test t;
	
	public int i;
	private long l;
	private double d;
	private byte b;
	private float f;
	private boolean bo;
	private char c;
	private short s;
	private String string;
	
	private Integer integer;
	private Long long1;
	private Double double1;
	private Byte byte1;
	private Float float1;
	private Boolean boolean1;
	private Character character;
	private Short short1;
	
	
	
//	private int [] arrays;
//	private long [] ls;
//	private Test [] ts;
	
	public void T(){
		System.out.println("hh");
	}
	
	
	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		for(int i=0;i<400000000;i++){
			list.add(null);
		}
		System.out.println(list.size());
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[int] "+i+"\n"
				+"[long]"+l+"\n"
				+"[double]"+d+"\n"
				+"[byte]"+b+"\n"
				+"[float]"+f+"\n"
				+"[boolean]"+bo+"\n"
				+"[char]"+c+"\n"
				+"[short]"+s+"\n"
				+"[Integer]"+integer+"\n"
				+"[Double]"+double1+"\n"
				+"[Byte]"+byte1+"\n"
				+"[Float]"+float1+"\n"
				+"[Boolean]"+boolean1+"\n"
				+"[Character]"+character+"\n"
				+"[Short]"+short1+"\n"
				+"[String]"+string+"\n"
				+"[Object]"+t+"\n";
				
	}

	
}
