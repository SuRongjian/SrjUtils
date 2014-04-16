package com.srj.util.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.If;

import com.srj.util.file.ExcelManageByJXL;
import com.srj.util.file.TextFileEncodingConverter;
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
	
	
	public static void main(String[] args) throws InterruptedException {
		/*List<String> list=new ArrayList<String>();
		for(int i=0;i<400000000;i++){
			list.add(null);
		}
		System.out.println(list.size());*/
		/*Thread.sleep(30*1000);
		
		boolean bo=false;
		int booli=0;
		*/
		
		//Test.writeSql();
		Test.testFunction();
		
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
	
	
	
	public static  void writeSql(){
		
		List<String> lists=readFile(new File("d:\\user.sql"), "utf-8");
		for(String line:lists){
			String words[]=line.split(",");
			String insertSql="INSERT INTO users_system_architecture (department, room) VALUES ('"+words[0]+"', '"+words[1]+"');";
			System.out.println(insertSql);
		}
		
		
		
	}
	
	
	
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
	
	
	public static void testFunction(){
		List<String> lines=new ArrayList<String>();
		ExcelManageByJXL embj=new ExcelManageByJXL("D:\\document\\sendi\\20140402\\系统用户用户架构.xls");
		
		for(int row=0;row<313;row++){
			String insert="INSERT INTO users_system_architecture(department,room) VALUES (";
			insert+="'"+embj.getCellValueToString(0, 0, row)+"','"+embj.getCellValueToString(0, 1, row)+"');";
			lines.add(insert);
		}
		
		TextFileEncodingConverter.saveFile(new File("d:/sql.sql"), "utf-8", lines);
		
	}

	
}
