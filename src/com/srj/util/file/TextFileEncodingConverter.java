package com.srj.util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * 支持将某个文件夹下所有的文本文件和子文件夹下的所有文本文件转换编码（从一种编码到另一种编码）
 * @author SuRongjian
 * @createDate 2013年8月14日 下午3:03:16
 * @version 1.0
 * @Description TODO
 */
public class TextFileEncodingConverter {
	public static String UTF8="UTF-8";
	public static String GBK="GBK";
	
	
	/**
	 * @param fileDir 
	 * 			要转换的文件夹或文本文件
	 * @param encodingFrom
	 * 			文本文件的原始编码
	 * @param encodingTo
	 * 			新文本文件的编码
	 */
	public static void encodingConverter(String fileDir,String encodingFrom,String encodingTo){
		File file=new File(fileDir);
		if(!file.isDirectory()){
			converterFile(file, file.getParentFile(), encodingFrom, encodingTo);
			
		}
		else{
			File[] files=file.listFiles();
			for(File f:files){
				converterFile(f,file,encodingFrom,encodingTo);
			}
		}
		
	}
	
	
	/**
	 * 通过递归调用实现文件夹下所有文件的转换
	 * @param f
	 * 			当前的文件或文件夹
	 * @param file
	 * 			最开始要用到的文件夹
	 * @param encodingFrom
	 * 			文本文件的原始编码
	 * @param encodingTo
	 * 			新文本文件的编码
	 */
	private static void converterFile(File f, File file, String encodingFrom, String encodingTo) {
		// TODO Auto-generated method stub
		if(f.isDirectory()){
			for(File f2:f.listFiles()){
				converterFile(f2,file,encodingFrom,encodingTo);
			}
		}else{
			List<String> lines=readFile(f, encodingFrom);
			String dirA=f.getParent();
			String dirB=null;
			if(!file.isDirectory()){
				dirB=file.getParent();
			}else{
				dirB=file.getAbsolutePath();
			}
			String converterDir=dirB+"/converter"+dirA.substring(dirB.length());
			createDirectory(converterDir);
			File save=new File(converterDir+"/"+f.getName());
			saveFile(save, encodingTo, lines);
		}
		
	}
	
	
	
	/**
	 * 用指定编码按行读取文件
	 * @param file
	 * 			要读取的文件
	 * @param encodingFrom
	 * 			指定读取的编码
	 * @return
	 * 			返回读取到的所有行
	 */
	private static List<String> readFile(File file,String encodingFrom){
		List<String> lines=new ArrayList<String>();
		try {
			FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, encodingFrom);
            BufferedReader br = new BufferedReader(isr);
            
            for(String line = br.readLine(); line != null; line = br.readLine()){
            	lines.add(line);
            }
            
            br.close();
            isr.close();
            fis.close();
            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return lines;
	}
	
	
	/**
	 * 按指定编码一行一行的写入文件
	 * @param file
	 * 			要写入的文件
	 * @param encodingTo
	 * 			指定写入的编码
	 * @param lines
	 * 			字符串集合
	 */
	private static void saveFile(File file,String encodingTo,List<String> lines){
		try {
			 FileOutputStream fos=new FileOutputStream(file);
	         OutputStreamWriter osw=new OutputStreamWriter(fos);
	         BufferedWriter bw=new BufferedWriter(osw);
	         for(String line:lines){
	        	 bw.write(line);
	        	 bw.newLine();
	         }
	         bw.close();
	         osw.close();
	         fos.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	
		
	}
	
	
	/**
	 * 创建文件夹
	 * @param dir
	 * @return
	 */
	private static File createDirectory(String dir){
		File fileDir=new File(dir);
		if(!fileDir.exists()){
			fileDir.mkdirs();
		}
		return fileDir;
	}
	
	
	public static void main(String[] args) {
		String fileDir="/media/000B0DC100092E7F/KuaiPan/各种资料/各种源码/lucene的20万网页负载完整案例/lecenedemo/src/joyoou/util/CollectionFactory.java";
		String fileDir2="/home/srj/下载/疯狂Java讲义/疯狂Java讲义_源码/14";
		String fileDir3="F:\\光盘内容\\JavaWeb典型模块与项目实战大全\\本书源程序\\18\\webpage\\src";
//		File file=new File(fileDir2);
//		System.out.println(file.isDirectory());
//		System.out.println(file.getName());
//		File [] files=new File(fileDir).listFiles();
//		System.out.println(files.length);
		TextFileEncodingConverter.encodingConverter(fileDir3, GBK, UTF8);
		
	}
	
}
