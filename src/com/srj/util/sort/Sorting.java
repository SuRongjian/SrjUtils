/**
 * 常用的排序方法集合
 * create time 2014年3月14日 下午5:10:34
 * author surongjian
 *		
 */


package com.srj.util.sort;

import java.util.Arrays;
import java.util.Random;

public class Sorting {
	
	/**
	 * 插入排序
	 * @param arrays
	 */
	public void insertionSort(int [] arrays){
		for(int i=0;i<arrays.length-1;i++){
			int j=i+1;
			int nowNumber=arrays[j];    
			for(--j;j>=0;j--){ 
				if(j>0&&nowNumber<arrays[j]){
					arrays[j+1]=arrays[j];
					arrays[j]=nowNumber;
				}
			}
			
		}
	}
	
	
	
	/**
	 * 获取任意长度的随机数组
	 * @param length
	 * @return
	 */
	public int [] getRandNumberArray(int length){
		
		int [] randNumberArray=new int[length];
		Random random=new Random();
		for(int i=0;i<length;i++){
			randNumberArray[i]=random.nextInt(10*length);
		}
		
		return randNumberArray;
	}
	
	
	
	
	public static void main(String[] args) {
		Sorting sorting=new Sorting();
		int [] arrays=sorting.getRandNumberArray(25);
		System.out.println(Arrays.toString(arrays));
		sorting.insertionSort(arrays);
		System.out.println(Arrays.toString(arrays));
		
		boolean b=true;
		if(b=1>0&&1<0){
			
		}
		b=true||false;
		System.out.println(b);
		
	}
	
	
}
