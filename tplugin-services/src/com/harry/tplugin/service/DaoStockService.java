package com.harry.tplugin.service;

import java.util.List;

import com.harry.tplugin.bean.Stock;
import com.harry.tplugin.dao.basic.AbstractServiceDao;

public class DaoStockService extends AbstractServiceDao implements StockService{

	@Override
	public void createStock(Stock stock) {
		this.getDao().create(stock);
	}

	@Override
	public Stock updateStock(Stock stock) {
		return (Stock) this.getDao().update(stock);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Stock> findAllStock() {
		return (List<Stock>)this.getDao().query("getAllStock");
	}

	@Override
	public Stock findStockByProIdStoreId(String proId, String storeId) {
		List<?> stockList = (List<?>)this.getDao().query("getStockByProIdStoreId", new String[]{proId, storeId});
		return stockList == null || stockList.isEmpty() ? null : (Stock)stockList.get(0);
	}

	@Override
	public void deleteStockByProIdStoreId(String proId, String storeId) {
		Stock stock = findStockByProIdStoreId(proId, storeId);
		if (null != stock)
		{
			this.getDao().delete(stock);
		}
	}


}
