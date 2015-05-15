package com.harry.tplugin.service;

import com.harry.tplugin.bean.Login;
import com.harry.tplugin.bean.Product;

public interface ProductService {
	
	/**
	 * 
	 * @param product
	 * @return 
	 */
	public String create(Product product);
	
	/**
	 * 
	 * @param proId
	 */
	public void deleteByProId(String proId);
	
	/**
	 * 
	 * @param product
	 * @return
	 */
	public Product update(Product product);
	
	public List<Product>
	/**
	 * 
	 * @param proId
	 * @return
	 */
	public Product getProductByProId(String proId);

}
