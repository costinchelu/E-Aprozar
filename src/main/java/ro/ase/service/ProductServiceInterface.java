package ro.ase.service;

import java.util.List;

import ro.ase.entity.Product;

public interface ProductServiceInterface {
	
	Long insert(Product product);
	
	void delete(Long id);
	
	void update(Product product);
	
	Product find(Long id);
	
	List<Product> findAll();
	
	List<Product> findAllByDepartment(String department);

}
