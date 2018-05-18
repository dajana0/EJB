package edu.pjwstk.sri.lab2.test;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import edu.pjwstk.sri.lab2.dao.CategoryDao;
import edu.pjwstk.sri.lab2.dao.ProductDao;
import edu.pjwstk.sri.lab2.dto.Cart;
import edu.pjwstk.sri.lab2.dto.OrderItem;
import edu.pjwstk.sri.lab2.model.Category;
import edu.pjwstk.sri.lab2.model.Product;

@Named("testBean")
@RequestScoped
public class TestBean implements Serializable {

	@Inject
	private CategoryDao catService;

	@Inject
	private ProductDao prodService;

	@Inject
	private Cart cart;

	public TestBean() {
	}

	public void test() {
		cart.addItem(prodService.findById(2000L), 1);
		cart.addItem(prodService.findById(2003L), 8);
		makeOrder();
	}

	public void produkty() {
		List<Product> listAllProduct = prodService.listAll(null, null);
		for (Product p : listAllProduct) {
			System.out.println(p.getId() + " " + p.getName() + " " + p.getStock());
		}
	}

	public void prod1() {
		cart.addItem(prodService.findById(2001L), 1);
		cart.addItem(prodService.findById(2002L), 2);
		makeOrder();
	}

	public void makeOrder() {
		try {
			cart.makeOrder();
			System.out.println("------------------------Zamówienie udane!----------------------");
		} catch (Throwable er) {
			System.out.println("------------------------Wycofano----------------------");
		}
	}

}
