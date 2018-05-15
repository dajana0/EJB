package edu.pjwstk.sri.lab2.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.*;

import edu.pjwstk.sri.lab2.model.Category;
import edu.pjwstk.sri.lab2.model.Product;
/**
 * DAO for Category
 */
//@Stateless
@Singleton
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CategoryDao {
	@PersistenceContext(unitName = "sri2-persistence-unit")
	private EntityManager em;
	
	@Resource
    TimerService timerService;
	
//	@Resource
//	private  UserTransaction tx;
	 @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void create(Category entity)  {

			em.persist(entity);

		
	}
	 @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteById(Long id)  {
		Category entity = em.find(Category.class, id);
		if (entity != null) {
			em.remove(entity);

		}
	}
	 
	 @TransactionAttribute(TransactionAttributeType.NEVER)
	 @Lock(LockType.READ)
	public Category findById(Long id) {
		return em.find(Category.class, id);
	}
	 @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Category update(Category entity)  {

			return em.merge(entity);


	}
	 
	 public List<Category> utworzBudzik(long duration) {
         long iloscsekund = duration* 1000;
         timerService.createTimer(iloscsekund, null);
         return listAll(null,null);

    }
	 
	 
	 @Timeout
     private List<Category> metodaCzasowa(Timer timer) {
		 System.out.println(listAll(null,null));
		 return listAll(null,null);
     }
	 
	 @Lock(LockType.READ)
	 @TransactionAttribute(TransactionAttributeType.NEVER)
	private List<Category> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Category> findAllQuery = em
				.createQuery(
						"SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.parentCategory LEFT JOIN FETCH c.childCategories ORDER BY c.id",
						Category.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
