package ro.ase.service.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.ase.entity.LineItem;
import ro.ase.entity.Order;
import ro.ase.entity.Product;
import ro.ase.service.LineItemServiceInterface;

@Service
public class ServiceCart {

    @Autowired
    LineItemServiceInterface lineItemService;

    Logger logger = Logger.getLogger(ServiceCart.class.getName());

    /**
     * String -> Long
     */
    public long getId(String id) {
        long idProduct;
        try {
            idProduct = Long.parseLong(id);
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, null, e);
            return -1L;
        }
        return idProduct;
    }

    /**
     * Adauga produs la comanda
     */
    public void setItemsToCart(Product product, HttpServletRequest request) {
        boolean flag = true;
        Order order = (Order) request.getSession().getAttribute("order");
        for (LineItem item : order.getLineItem()) {
            if (item.getIdProduct().equals(product.getIdProduct())) {
                setQuantityOfItems(order, item, 1);
                flag = false;
                break;
            }
        }
        if (flag) {
            setNewItem(order, product);
        }
    }

    /**
     * Modifica cantitatea
     */
    public void changeQuantityOfItems(Product product, HttpServletRequest request, int quantity) {
        Order order = (Order) request.getSession().getAttribute("order");
        for (LineItem item : order.getLineItem()) {
            if (item.getIdProduct().equals(product.getIdProduct())) {
                //if quantity = 0 delete a product from order
                if (item.getQuantity() + quantity == 0) {
                    if (order.getIdOrder() != null) lineItemService.delete(item.getIdLineItem());
                    order.getLineItem().remove(item);
                    new ServiceOrderEntity().setTotal(order);
                    break;
                }
                setQuantityOfItems(order, item, quantity);
            }
        }
    }

    /**
     * Seteaza cantitatea in comanda
     */
    private void setQuantityOfItems(Order order, LineItem item, int quantity) {
        item.setQuantity(item.getQuantity() + quantity);
        new ServiceOrderEntity().setTotal(order);
        if (order.getIdOrder() != null) lineItemService.update(item);
    }

    /**
     * Produs nou
     */
    private void setNewItem(Order order, Product product) {
        LineItem newItem = new LineItem();
        newItem.setIdProduct(product.getIdProduct());
        newItem.setProduct(product);
        newItem.setQuantity(1);
        newItem.setPrice(product.getPrice());
        order.getLineItem().add(newItem);
        new ServiceOrderEntity().setTotal(order);
        if (order.getIdOrder() != null) {
            newItem.setIdOrder(order.getIdOrder());
            newItem.setIdLineItem(lineItemService.insert(newItem));
        }
    }
}
