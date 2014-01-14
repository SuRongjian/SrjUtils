package com.srj.util.jdbc;



import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Id;


/**
 * @author surongjian
 * Date	2013年11月2日	上午10:17:35
 * 这是类可以持久化一个对象进数据库中，需要提供一个Connection给该类的构造函数。
 * 持久化对象和数据库表之间的映射关系采取默认的命名方式，如果一个对象对应的类名是AaaaBbbb,则对应的表名是aaaa_bbbb
 * 需要为持久化的类指定一个@Id注解，用来确认那个字段是主键
 * 用完之后记得要手动关闭connection连接，调用closeConnection方法
 */
public class MyEasyHibernateImpl implements MyEasyHibernate{
	
	String tableName;
	List<String> columnNames;
	Connection conn;
	
	public MyEasyHibernateImpl(Connection connection){
		this.conn=connection;
	}
	
	
	public <T> boolean  save(T t){
		
		boolean isSave=false;
		
		tableName=getTableName(t);
		columnNames=getColumndName(t);
		StringBuffer sqlBuffer=new StringBuffer();
		sqlBuffer.append("insert into ").append(tableName);
		sqlBuffer.append(" (");
		for(String s:columnNames){
			sqlBuffer.append(s);
			sqlBuffer.append(",");
		}
		sqlBuffer.deleteCharAt(sqlBuffer.length()-1);
		sqlBuffer.append(") values(");
		
		Field [] fields=t.getClass().getDeclaredFields();
		for (Field f:fields) {
			try {
				f.setAccessible(true);
				Object o=f.get(t);
				if(fieldIsNum(f)){
					sqlBuffer.append(o);
					sqlBuffer.append(",");
				}else{
					sqlBuffer.append("'");
					sqlBuffer.append(o);
					sqlBuffer.append("',");
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		sqlBuffer.deleteCharAt(sqlBuffer.length()-1);
		sqlBuffer.append(")");
		
		
		isSave = executeSQL(isSave, sqlBuffer);
		
		System.out.println(sqlBuffer.toString());
		return isSave;
	}


	private boolean executeSQL(boolean isSave, StringBuffer sqlBuffer) {
		PreparedStatement ps=null;
		try {
			ps= conn.prepareStatement(sqlBuffer.toString());
			int i=ps.executeUpdate();
//			System.out.println("getAutoCommit()  "+conn.getAutoCommit());
			if(!conn.getAutoCommit()){
				conn.commit();
			}
			if(i>0){
				isSave=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return isSave;
	}


	private boolean fieldIsNum(Field f) {
		return f.getType().getGenericSuperclass()==null||f.getType().getGenericSuperclass().toString().equals(Number.class.toString());
	}
	
	
	public <T> boolean delete(T t){
		boolean isDelete=false;
		tableName=getTableName(t);
		String id="";
		Object idValue=null;
		boolean isNum=false;
		Field [] fields=t.getClass().getDeclaredFields();
		
		for(Field f:fields){
			f.setAccessible(true);
			Id idA =f.getAnnotation(Id.class);
			if(idA!=null){
				id=f.getName();
				try {
					idValue=f.get(t);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				isNum=fieldIsNum(f);
				break;
			}
		}
		StringBuffer sqlBuffer=new StringBuffer();
		sqlBuffer.append("delete from ");
		sqlBuffer.append(tableName);
		sqlBuffer.append(" where ");
		sqlBuffer.append(id);
		sqlBuffer.append("=");
		if(isNum){
			sqlBuffer.append(idValue.toString());
		}else{
			sqlBuffer.append("'");
			sqlBuffer.append(idValue);
			sqlBuffer.append("'");
		}
		
		isDelete=executeSQL(isDelete, sqlBuffer);
		
		System.out.println("INFO: "+sqlBuffer.toString());
		
		return isDelete;
	}
	
	
	public <T> boolean update(T t){
		StringBuffer sqlBuffer=new StringBuffer();
		Boolean isUpdate=false;
		tableName=getTableName(t);
		columnNames=getColumndName(t);
		String keyColumn=null;
		Object keyValue=null;
		Boolean keyColumnIsNum=false;
		
		
		
		sqlBuffer.append("update ");
		sqlBuffer.append(tableName);
		sqlBuffer.append(" set ");
		for(Field f:t.getClass().getDeclaredFields()){
			f.setAccessible(true);
			StringBuffer sb=new StringBuffer();
			nameConversion(f.getName(), sb);
			sqlBuffer.append(sb);
			sqlBuffer.append("=");
			try {
				if(fieldIsNum(f)){
					sqlBuffer.append(f.get(t).toString());
					sqlBuffer.append(",");
				}else{
					sqlBuffer.append("'");
					sqlBuffer.append(f.get(t).toString());
					sqlBuffer.append("',");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			if(f.getAnnotation(Id.class)!=null){
				StringBuffer keyColumnBuffer=new StringBuffer(); 
				nameConversion(f.getName(), keyColumnBuffer);
				keyColumn=keyColumnBuffer.toString();
				try {
					keyValue=f.get(t);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				keyColumnIsNum=fieldIsNum(f);
			}
		}
		sqlBuffer.deleteCharAt(sqlBuffer.length()-1);
		sqlBuffer.append(" where ");
		sqlBuffer.append(keyColumn);
		sqlBuffer.append("=");
		if(keyColumnIsNum){
			sqlBuffer.append(keyValue);
		}else{
			sqlBuffer.append("'");
			sqlBuffer.append(keyValue);
			sqlBuffer.append("'");
		}
		System.out.println("INFO:"+sqlBuffer.toString());
		isUpdate=executeSQL(isUpdate, sqlBuffer);
		
		
		return isUpdate;
	}
	
	public <T> T get(Class<T> clazz,Object id){
		tableName=getTableName(clazz);
		
		
		
		return null;
	}
	
	public <T> List<T> getAll(Class<T> clazz,Object id){
		return null;
	}
	

	
	
	private <T> String getTableName(T t){
		String className=t.getClass().getSimpleName();
		StringBuffer tableName=new StringBuffer();
		nameConversion(className, tableName);
		
		return tableName.toString();
	}


	private void nameConversion(String nameFrom, StringBuffer nameTo) {
		for(int i=0;i<nameFrom.length();i++){
			char c=nameFrom.charAt(i);
			if((c>='A')&&c<='Z'){
				if(i!=0){
					nameTo.append("_");
				}
				nameTo.append(new Character(c).toString().toLowerCase());
			}else{
				nameTo.append(c);
			}
		}
	}
	
	
	private <T> List<String> getColumndName(T t){
		List<String> columnNameList=new ArrayList<String>();
		Class clazz=t.getClass();
		
		StringBuffer columnNameBuffer=new StringBuffer();
		
		Field [] fields=clazz.getDeclaredFields();
		for(Field f:fields){
			nameConversion(f.getName(), columnNameBuffer);
			columnNameList.add(columnNameBuffer.toString());
			columnNameBuffer.setLength(0);
		}
		
		
		return columnNameList;
	}
	
	public void closeConnection(){
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		testSave();
//		testDelete();
//		BdMaterials bm= InitializationGenerator.newInitInstance(BdMaterials.class);
//		bm.setMaxstock(new java.math.BigDecimal(3323));
//		bm.setMinstock(new java.math.BigDecimal(2222));
//		bm.setSafestock(new java.math.BigDecimal(1223));
//		bm.setCurrencyCode("rmb");
//		bm.setModelCode("m123");
//		bm.setSpecCode("s123");
//		bm.setUnitCode("u123");
//		bm.setCode("xjdso");
//		MyEasyHibernate meh=new MyEasyHibernateImpl();
//		Boolean b= meh.update(bm);
//		System.out.println(b);
//		
//		try {
//			TimeUnit.SECONDS.sleep(10);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//	}


//	private static void testDelete() {
//		MyEasyHibernate meh=new MyEasyHibernateImpl();
//		BdMaterials bm=new BdMaterials();
//		bm.setCode("nukol");
//		boolean b=meh.delete(bm);
//		System.out.println("delete "+b);
//	}


//	private static void testSave() {
//		MyEasyHibernate meh=new MyEasyHibernateImpl();
//		BdMaterials bm=InitializationGenerator.newInitInstance(BdMaterials.class);
//		bm.setMaxstock(new java.math.BigDecimal(333));
//		bm.setMinstock(new java.math.BigDecimal(222));
//		bm.setSafestock(new java.math.BigDecimal(123));
//		bm.setCurrencyCode("rmb");
//		bm.setModelCode("m123");
//		bm.setSpecCode("s123");
//		bm.setUnitCode("u123");
//		meh.save(bm);
////		System.out.println(meh.getColumndName(new BdMaterials()));
//		BigDecimal b=new BigDecimal(1);
//		b.toString();
//	}
	
}
