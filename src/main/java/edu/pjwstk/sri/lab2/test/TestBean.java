package edu.pjwstk.sri.lab2.test;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import edu.pjwstk.sri.lab2.dao.CategoryDao;
import edu.pjwstk.sri.lab2.dao.ProductDao;
import edu.pjwstk.sri.lab2.dto.Cart;
import edu.pjwstk.sri.lab2.model.Category;
import edu.pjwstk.sri.lab2.model.Product;

@Named("testBean")
@RequestScoped
public class TestBean implements Serializable {

	@Inject
	private CategoryDao catService ;
	
	@Inject
	private ProductDao prodService ;
	
	@Inject
	private Cart cart;
	public TestBean() {
	}
	
	public void test() {

		List<Product> listAllProduct = prodService.listAll(null, null);
		System.out.println("dajana " +listAllProduct);
		Product e = prodService.findById(2003L);
		e.setStock(7);
		prodService.update(e);
		
		 listAllProduct = prodService.listAll(null, null);
		System.out.println(listAllProduct);
		for(Product p : listAllProduct) {
			System.out.println(p.getId() + " "+ p.getName() + " " + p.getStock());
		}

		cart.addItem(prodService.findById(2000L), 1);
		cart.addItem(prodService.findById(2003L), 8);
		try {
		cart.makeOrder();
		}catch(Throwable er) {
			System.out.println("------------------------Wycofano----------------------");
		}
		 listAllProduct = prodService.listAll(null, null);
			System.out.println(listAllProduct);
			for(Product p : listAllProduct) {
				System.out.println(p.getId() + " "+ p.getName() + " " + p.getStock());
			}
	}
	

}
