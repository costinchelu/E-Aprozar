package ro.ase.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.ase.entity.InfoUser;
import ro.ase.entity.LineItem;
import ro.ase.entity.Order;
import ro.ase.entity.OrderStatus;
import ro.ase.service.LineItemServiceInterface;
import ro.ase.service.OrderServiceInterface;

@Service
public class ChoiceCart {
	
	@Autowired
	LineItemServiceInterface lineItemService;
	
	@Autowired
	OrderServiceInterface orderService;
	
	/**
	 * Doua comenzi
	 */
	public Order mixOrder(Order order, Order orderUser) {
		
		if(order.getLineItem().isEmpty()) {
			return orderUser;
		}
		
		if(orderUser.getLineItem().isEmpty()) {
			return order;
		}
		
		List<LineItem> lineItemMatchedUser = new LinkedList<LineItem>();
		List<LineItem> lineItemMatchedSession = new LinkedList<LineItem>();
		for(LineItem itemUser: orderUser.getLineItem()) {
			for(LineItem itemSession: order.getLineItem()) {
				if(itemUser.getIdProduct().equals(itemSession.getIdProduct())) {					
					lineItemMatchedUser.add(itemUser);
					lineItemMatchedSession.add(itemSession);
					break;
				}
			}			
		}
		orderUser.getLineItem().removeAll(lineItemMatchedUser);
		order.getLineItem().removeAll(lineItemMatchedSession);

		for(LineItem itemUser: lineItemMatchedUser) {
			for(LineItem itemSession: lineItemMatchedSession) {
				if(itemUser.getIdProduct().equals(itemSession.getIdProduct())) {					
					itemUser.setQuantity(itemUser.getQuantity()+itemSession.getQuantity());
					break;
				}
			}			
		}

		orderUser.getLineItem().addAll(lineItemMatchedUser);

		if(!order.getLineItem().isEmpty()) {
			for(LineItem item: order.getLineItem()) {
				item.setIdOrder(orderUser.getIdOrder());
				item.setIdLineItem(lineItemService.insert(item));
				orderUser.getLineItem().add(item);
				}
		}
		// calcul total
		new ServiceOrderEntity().setTotal(orderUser);
		// actualizare lineitems
		for(LineItem itemUser: lineItemMatchedUser){
			lineItemService.update(itemUser);
		}
		return orderUser;
	}
	
	/**
	 * Comanda noua
	 */
	public void createNewOrder(Order order, InfoUser infoUser) {
		order.setIdUser(infoUser.getIdUser());		
		Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
		order.setOrderedDate(timestamp);
		order.setPaidDate(timestamp);
		order.setStatus(OrderStatus.NOUA.name());
		order.setCustomerAddress("");		
		Long idOrder = orderService.insert(order);
		order.setIdOrder(idOrder);
		if(!order.getLineItem().isEmpty()) for(LineItem item: order.getLineItem()) {
			item.setIdOrder(idOrder);
			item.setIdLineItem(lineItemService.insert(item));
		}
		infoUser.getOrders().add(order);
	}
}
