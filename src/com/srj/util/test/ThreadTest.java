/**
 * create time 2014年3月13日 下午4:55:04
 * author surongjian
 *		
 */


package com.srj.util.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest {
	
	static int count=0;
	
	float ff=3.2f;
	double dd=3.3;
	
	SimpleDateFormat sdf=new SimpleDateFormat("");
	Date date=new Date();
	
	
	public static void main(String[] args) {
		
		
		ThreadTest tt=new ThreadTest();
		ThreadTest.AddOne addOne=tt.new AddOne("addOne1");
		ThreadTest.decOne decOne=tt.new decOne("decOne1");
		//ExecutorService exec=Executors.newCachedThreadPool();
		//exec.execute(addOne);
		Thread thread1= new Thread(addOne);
		thread1.setName("thread1");
		thread1.start();
		while(true){
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("isAlive:"+thread1.isAlive());
			System.out.println("isInterrupted:"+thread1.isInterrupted());
		}
		//new Thread(addOne).start();
		//new Thread(decOne).start();
		
	}
	
	public void function(){
		sdf.format(date);
		
	}
	
	
	class AddOne implements Runnable{
		
		
		String name;
		public AddOne(){
			
		}
		public AddOne(String name){
			this.name=name;		
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				++count;
				System.out.println(name+":"+count);
			}
			
			
		}
		
		
		
	}
	
	
	class decOne implements Runnable{
		String name;
		public decOne(){
			
		}
		public decOne(String name){
			this.name=name;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(1==1){
				--count;
				System.out.println(name+":"+count);
			}
			
		}
		
	}

}






