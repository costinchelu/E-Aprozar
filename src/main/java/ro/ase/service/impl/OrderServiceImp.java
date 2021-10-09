package ro.ase.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ro.ase.entity.Order;
import ro.ase.jdbc.OrderDAO;
import ro.ase.service.OrderServiceInterface;

@Service
public class OrderServiceImp implements OrderServiceInterface {

	@Override
	public Long insert(Order order) {
		OrderDAO dao = new OrderDAO();
		return dao.insert(order);
	}

	@Override
	public void delete(Long id) {
		OrderDAO dao = new OrderDAO();
		dao.delete(id);
	}

	@Override
	public void update(Order order) {
		OrderDAO dao = new OrderDAO();
		dao.update(order);
	}

	@Override
	public Order find(Long id) {
		return null;
	}

	@Override
	public List<Order> findAll() {
		return null;
	}

	@Override
	public List<Order> findAllByIdUser(Long idUser) {
		return null;
	}

	@Override
	public List<Order> findAllByIdUserStatus(Long idUser, String status) {
		OrderDAO dao = new OrderDAO();
		return dao.findAllByIdUserStatus(idUser, status);
	}
}
