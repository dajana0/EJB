package edu.pjwstk.sri.lab2.test;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import edu.pjwstk.sri.lab2.dao.CategoryDao;
import edu.pjwstk.sri.lab2.dao.ProductDao;
import edu.pjwstk.sri.lab2.dto.Cart;
import edu.pjwstk.sri.lab2.dto.Order;
import edu.pjwstk.sri.lab2.dto.OrderItem;
import edu.pjwstk.sri.lab2.model.Category;
import edu.pjwstk.sri.lab2.model.Product;

@Named("testBean")
@RequestScoped
public class TestBean implements Serializable {

	@Inject
	private CategoryDao catService ;
	
	@Inject
	private ProductDao prodService ;
	
	public TestBean() {
	}
	
	public void test() {
		List<Category> listAll = catService.utworzBudzik(20);
		System.out.println(listAll);
		Product e = prodService.findById(2003L);
		e.setStock(7);
		prodService.update(e);
		
		List<Product> listAllProduct = prodService.listAll(null, null);
		System.out.println(listAllProduct);
		for(Product p : listAllProduct) {
			System.out.println(p.getId() + " "+ p.getName() + " " + p.getStock());
		}
		Cart cart = new Cart() ;
		cart.addItem(prodService.findById(2003L), 5);
		cart.makeOrder();
		
	}
	

}
