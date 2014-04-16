/**
 * create time 2014年4月16日 下午3:52:18
 * author surongjian
 * 本工具类用到的jar包是   jxl-2.6.12.jar
 * 本工具封装了一下操作excle文档的基本方法
 */


package com.srj.util.file;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelManageByJXL {
	
	private Workbook workbook;

	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}


	public ExcelManageByJXL(String filePath){
		super();
		try {
			workbook=Workbook.getWorkbook(new File(filePath));
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 * 根据初始化对象时的文件路径返回一个工作簿对象（工作簿就是一个excel文件）
	 */
	public Workbook getWorkbook(){
		return workbook;
	}
	
	/**
	 * @param index
	 * 		工作簿里面的工作表是从0开始的，第一个工作表的索引是0
	 * @return
	 * @throws IndexOutOfBoundsException
	 * @throws BiffException
	 * @throws IOException
	 * 返回一个工作表
	 */
	public Sheet getSheet(int index) throws IndexOutOfBoundsException, BiffException, IOException{
		return getWorkbook().getSheet(index);
	}
	
	/**
	 * @param sheetIndex
	 * 		当前工作簿中工作表的位置
	 * @param col
	 * 		工作表的列号，从0开始
	 * @param row
	 * 		工作表的行号，从0开始
	 * @return 
	 * 		返回一个cell对象
	 * @throws IndexOutOfBoundsException
	 * @throws BiffException
	 * @throws IOException
	 * 
	 * 
	 */
	public Cell getCell(int sheetIndex,int col,int row) throws IndexOutOfBoundsException, BiffException, IOException{
		return getSheet(sheetIndex).getCell(col, row);
	}
	
	
	/**
	 * @param sheetIndex
	 * 		当前工作簿中工作表的位置(索引从0开始)
	 * @param col
	 * 		工作表的列号，从0开始
	 * @param row
	 * 		工作表的行号，从0开始
	 * @return
	 * 		返回cell的字符串值
	 */
	public String getCellValueToString(int sheetIndex,int col,int row) {
		String cellValue=null;
		
		try {
			cellValue=getCell(sheetIndex, col, row).getContents();
		} catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cellValue;
	}
	
	/**
	 * 释放资源，必须要做的。
	 */
	public void excelManageByJXLClose(){
		workbook.close();
	}
	
	public static void main(String[] args) throws IndexOutOfBoundsException, BiffException, IOException {
		ExcelManageByJXL embj=new ExcelManageByJXL("D:\\document\\sendi\\20140402\\系统用户用户架构.xls");
		//System.out.println(embj.getSheet(0).getName());
		//System.out.println(embj.getSheet(1).getName());
		//System.out.println(embj.getSheet(2).getName());
		
		System.out.println(embj.getCellValueToString(0,0,0));
		
	}
	
	
	
}
