package com.srj.util.jdbc;


import java.util.List;

public interface MyEasyHibernate {
	public <T> boolean  save(T t);
	public <T> boolean delete(T id);
	public <T> boolean update(T t);
	public <T> T get(Class<T> clazz,Object id);
	public <T> List<T> getAll(Class<T> clazz,Object id);
}
