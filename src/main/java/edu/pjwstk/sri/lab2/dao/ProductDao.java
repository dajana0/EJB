package edu.pjwstk.sri.lab2.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.rmi.CORBA.Util;

import edu.pjwstk.sri.lab2.dto.Cart;
import edu.pjwstk.sri.lab2.dto.OrderItem;
import edu.pjwstk.sri.lab2.model.Product;

/**
 * DAO for Product
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProductDao {

	
	@PersistenceContext(unitName = "sri2-persistence-unit")
	private EntityManager em;
	 @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void create(Product entity) {
		em.persist(entity);
	}
	 @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteById(Long id) {
		Product entity = em.find(Product.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	public Product findById(Long id) {
		return em.find(Product.class, id);
	}
	 @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Product update(Product entity) {
		  
		return em.merge(entity);
		
	}
	 
	 @Lock(LockType.READ)
	 @TransactionAttribute(TransactionAttributeType.NEVER)
	public List<Product> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Product> findAllQuery = em
				.createQuery(
						"SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.category ORDER BY p.id",
						Product.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
