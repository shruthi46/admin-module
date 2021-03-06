package com.niit.shoppingcart.DAO;


import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.shoppingcart.Model.Product;
import com.niit.shoppingcart.DAO.*;



@Repository("productDAO")
public  class ProductDAOImpl implements ProductDAO{
	
	@Autowired
	private SessionFactory sessionFactory;


	public ProductDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean saveOrUpdate(Product product){
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	
	} 

	@Transactional
	public boolean delete(Product product)
	    {     
		try {
			sessionFactory.getCurrentSession().delete(product);
			return true;
		}
		 catch (Exception e){
			e.printStackTrace();
		
		return false;
		}
		}

	@Transactional
	public Product get(int id) {
		String hql = "from"+" Product"+" where id=" +id;
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Product> listProduct = (List<Product>) query.list();
		
		if (listProduct != null && !listProduct.isEmpty()) {
			return listProduct.get(0);
		}
		
		return null;
	}
	
	@SuppressWarnings("deprecation")

	@Transactional
	  public List<Product> list(){
			@SuppressWarnings("unchecked")
			List<Product> listProduct = (List<Product>)
			sessionFactory.getCurrentSession()
			.createCriteria(Product.class)
			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			return listProduct;
		}


	
	

}
