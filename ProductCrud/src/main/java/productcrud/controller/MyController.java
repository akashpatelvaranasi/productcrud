package productcrud.controller;

import java.lang.classfile.Attribute;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import productcrud.dao.ProductDao;
import productcrud.model.Product;

@Controller
public class MyController {
	
	@Autowired
	private ProductDao productDao;
	
	
	@GetMapping("/")
	public String home(Model m)
	{
		List<Product> products=productDao.getProducts();
		m.addAttribute("products", products);
		return "index";
	}
	
	//show add product form...................................
	@RequestMapping("/add-product")
	public String addProduct(Model m)
	{
		m.addAttribute("title" ,"Add Product");
		return "add_product_form";
	}
	
	//add product handler............................
	@RequestMapping(value ="/handle-product", method =RequestMethod.POST)
	public RedirectView handleProduct(@ModelAttribute Product product,HttpServletRequest request)
	{
		System.out.println(product);
		productDao.createProduct(product);
		RedirectView redirectView=new RedirectView();
		redirectView.setUrl(request.getContextPath()+ "/");
		return redirectView;
	}
	
	//delete product .....................
	@RequestMapping("/delete/{productId}")
	public RedirectView deleteproduct(@PathVariable("productId") int productId ,HttpServletRequest request)
	{
		this.productDao.deleteProduct(productId);
		RedirectView redirectView=new RedirectView();
		redirectView.setUrl(request.getContextPath()+ "/");
		return redirectView;
	}
	
	
	//update product.................................................
	@RequestMapping("/update/{productId}")
	public String updateForm(@PathVariable("productId") int productId, Model m)
	{
		Product product=this.productDao.getProduct(productId);
		m.addAttribute("product" , product);
		return "update-form";
	}

}










