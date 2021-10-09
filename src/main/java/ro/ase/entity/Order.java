package ro.ase.entity;

import lombok.AllArgsConstructor;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
public class Order {
	
	private Long idOrder;
	private Long idUser;
	private Timestamp orderedDate;
	private Timestamp paidDate;
	private String status;
	
	@NotNull(message="{order.customerAddress.null}")
	@NotEmpty(message="{order.customerAddress.empty}")
	private String customerAddress;
	
	private List<LineItem> lineItem;
	private double totalPrice;
	private boolean paid;
	
	public Order() {
		lineItem = new LinkedList<>();
	}
	
	public Order(Long idUser, Timestamp orderedDate, Timestamp paidDate, String status, String customerAddress, List<LineItem> lineItems, double totalPrice, boolean paid) {
		this.idUser = idUser;
		this.orderedDate = orderedDate;
		this.paidDate = paidDate;
		this.status = status;
		this.customerAddress = customerAddress;		
		this.lineItem = lineItems;
		this.totalPrice = totalPrice;
		this.paid = paid;
	}

	public Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Long idOrder) {
		 this.idOrder = idOrder;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		 this.idUser = idUser;
	}

	public void setOrderedDate(Timestamp orderedDate) {
		this.orderedDate = orderedDate;
	}

	public Timestamp getOrderedDate() {
		return orderedDate;
	}

	public void setPaidDate(Timestamp paidDate) {
		this.paidDate = paidDate;
	}

	public Timestamp getPaidDate() {
		return paidDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setLineItem(List<LineItem> lineitems) {
		this.lineItem = lineitems;
	}

	public List<LineItem> getLineItem() {
		return lineItem;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public boolean isPaid() {
		return paid;
	}
}
