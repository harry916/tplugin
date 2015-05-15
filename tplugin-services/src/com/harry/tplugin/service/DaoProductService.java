package com.harry.tplugin.service;

import java.util.List;

import com.harry.tplugin.bean.Product;
import com.harry.tplugin.dao.basic.AbstractServiceDao;

public class DaoProductService extends AbstractServiceDao implements ProductService{

	@Override
	public String create(Product product) {
		return  (String) this.getDao().create(product);
	}

	@Override
	public void deleteByProId(String proId) {
		this.getDao().delete(Product.class, proId);
	}

	@Override
	public Product update(Product product) {
		return (Product)this.getDao().update(product);
	}

	@Override
	public Product getProductByProId(String proId) {
		List<?> products = this.getDao().query("getProductViewByProId", new String[]{proId});
		return products == null ? null : (Product)products.get(0);
	}

}
