package ro.ase.service.impl;

import ro.ase.entity.LineItem;
import ro.ase.entity.Order;

public class ServiceOrderEntity {

    /**
     * Calculeaza total si seteaza in comanda
     */
    public void setTotal(Order order) {
        double totalPrice = 0.0;
        for (LineItem item : order.getLineItem()) {
            item.setAmount(round(item.getQuantity() * item.getPrice()));
            totalPrice += item.getAmount();
        }
        order.setTotalPrice(round(totalPrice));
        if (order.getIdOrder() != null) {
            new OrderServiceImp().update(order);
        }
    }

    private double round(double number) {
        return Math.round(number * 100) / 100.0;
    }
}
