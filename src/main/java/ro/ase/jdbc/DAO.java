package ro.ase.jdbc;

import java.util.List;

public abstract class DAO<T> {
	
	public abstract Long insert(T entity);
	
	public abstract void delete(Long id);
	
	public abstract void update(T entity);
	
	public abstract T find(Long id);
	
	public abstract List<T> findAll();
}
