package ro.ase.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ro.ase.entity.Product;
import ro.ase.jdbc.ProductDAO;
import ro.ase.service.ProductServiceInterface;

@Service
public class ProductServiceImp implements ProductServiceInterface {

	@Override
	public Long insert(Product product) {
		ProductDAO dao = new ProductDAO();
		return dao.insert(product);
	}	

	@Override
	public void delete(Long id) {
		ProductDAO dao = new ProductDAO();
		dao.delete(id);		
	}

	@Override
	public void update(Product product) {
		ProductDAO dao = new ProductDAO();
		dao.update(product);
	}

	@Override
	public Product find(Long id) {
		ProductDAO dao = new ProductDAO();
		return dao.find(id);
	}

	@Override
	public List<Product> findAll() {
		ProductDAO dao = new ProductDAO();
		return dao.findAll();
	}

	@Override
	public List<Product> findAllByDepartment(String department) {
		ProductDAO dao = new ProductDAO();
		return dao.findAllByDepartment(department);
	}
}
