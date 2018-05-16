package edu.pjwstk.sri.lab2.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import edu.pjwstk.sri.lab2.dao.ProductDao;
import edu.pjwstk.sri.lab2.model.Product;

@Stateful
public class Cart  {
	
	@Inject
    private ProductDao prodService;
	
	 @Resource
	 private EJBContext context;

	private List<OrderItem> cartItems = new ArrayList<OrderItem>();

	public List<OrderItem> getCartItems() {
		return cartItems;
	}

	public void addItem(Product p, Integer amount) {
		cartItems.add(new OrderItem(p, amount));
	}
	 @Remove
    public void remove() {
	 cartItems.clear();
	 }
    
	public void removeItem(Product p) {
		for(OrderItem ou : cartItems) {
			if(ou.getProduct() == p) {
				cartItems.remove(ou);
			}
		}
	}
	
	public void makeOrder() {
		try {
			for(OrderItem oi : getCartItems()) {
				   Product prod_database = prodService.findById(oi.getProduct().getId());
				   prod_database.setStock(prod_database.getStock() - oi.getAmount());
				   prodService.update(prod_database);
			   }
			}catch(Throwable th){
				   context.setRollbackOnly();
			   }
	}
	
}
