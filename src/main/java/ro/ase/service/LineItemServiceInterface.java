package ro.ase.service;

import java.util.List;

import ro.ase.entity.LineItem;

public interface LineItemServiceInterface {
	
	Long insert(LineItem lineitem);
	
	void delete(Long id);
	
	void update(LineItem lineitem);
	
	LineItem find(Long id);
	
	List<LineItem> findAll();
	
	List<LineItem> findAllByIdOrder(Long idOrder);
}
