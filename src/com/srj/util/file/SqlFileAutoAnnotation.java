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

public class SqlFileAutoAnnotation {
	List<String> lines;
	File sqlFile;
	public SqlFileAutoAnnotation(String fileName){
		this.sqlFile=new File(fileName);
	}
	
	public  List<String> readFile(File file,String encodingFrom){
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
	
	public void fileAnnotation(String encodingFrom,String encodingTo){
		lines=readFile(sqlFile,encodingFrom);
		
		boolean isNewLineSql=false;
		
		for(int i=0;i<lines.size();i++){
			String line=lines.get(i);
			if(!(line.startsWith("--"))){
				line="--"+line;
				lines.remove(i);
				if(!isNewLineSql){
					lines.add(i,"--//////////////////////////////////////////////////////////");
					lines.add(i+1,line);
					isNewLineSql=true;
				}else{
					lines.add(i,line);
				}
				
			}
		}
		
		saveFile(sqlFile, encodingTo, lines);
		
	}
	
	public static void main(String[] args) {
		
		SqlFileAutoAnnotation sfaa= new SqlFileAutoAnnotation("D:\\excesql.sql");
		sfaa.fileAnnotation("UTF-8", "UTF-8");
		System.out.println("sql文件注释成功");
	}
}
