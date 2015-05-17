package com.harry.tplugin.service;

import java.util.List;

import com.harry.tplugin.bean.Product;
import com.harry.tplugin.dao.basic.AbstractServiceDao;

public class DaoProductService extends AbstractServiceDao implements ProductService{

	@Override
	public void create(Product product) {
		Product pd = getProductByProId(product.getProId());
		if (null != pd)
		{
			update(product);
		}
		else
		{
			this.getDao().create(product);
		}
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
		return products == null || products.isEmpty() ? null : (Product)products.get(0);
	}

	@Override
	public List<Product> getAllProduct() {
		@SuppressWarnings("unchecked")
		List<Product> list = (List<Product>) this.getDao().query("getAllProductView");
		return list == null || list.isEmpty() ? null : list;
	}

}
