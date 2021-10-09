package ro.ase.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineItem {
	
	private Long idLineItem;
	private Long idOrder;
	private Long idProduct;
	private Product product;
	private int quantity;
	private double price;
	private double amount;

	public LineItem(Long idOrder, Long idProduct, Product product, int quantity, double price, double amount) {
		this.idOrder = idOrder;
		this.idProduct = idProduct;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.amount = amount;
	}
}
