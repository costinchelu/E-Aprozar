package ro.ase.service;

import java.util.List;

import ro.ase.entity.Order;

public interface OrderServiceInterface {
	
	Long insert(Order order);
	
	void delete(Long id);
	
	void update(Order order);
	
	Order find(Long id);
	
	List<Order> findAll();
	
	List<Order> findAllByIdUser(Long idUser);
	
	List<Order> findAllByIdUserStatus(Long idUser, String status);
}
