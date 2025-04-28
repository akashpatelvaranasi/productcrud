package productcrud.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import productcrud.model.Product;

@Component
public class ProductDao {
	
	  @Autowired
	  private HibernateTemplate hibernateTemplate;
	  
	  
	  //create.....................................
	  @Transactional
	  public void createProduct(Product product)
	  {
		  this.hibernateTemplate.saveOrUpdate(product);
	  }
	  
	  
	  //get all product...........................
	  public List<Product> getProducts()
	  {
		  List<Product> products=this.hibernateTemplate.loadAll(Product.class);
		  return products;
	  }

	  
	  //delete a single product...........................
	  @Transactional
	  public void deleteProduct(int productId)
	  {
		 Product p=this.hibernateTemplate.load(Product.class, productId);
		 this.hibernateTemplate.delete(p);
	  }
	  
	  
	  //get a single product.........................
	  public Product getProduct(int productId)
	  {
		  return this.hibernateTemplate.get(Product.class, productId);
	  }
	  
	 
}









