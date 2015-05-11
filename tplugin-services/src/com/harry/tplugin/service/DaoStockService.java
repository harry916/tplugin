package com.harry.tplugin.service;

import java.util.List;

import com.harry.tplugin.bean.Stock;
import com.harry.tplugin.dao.basic.AbstractServiceDao;

public class DaoStockService extends AbstractServiceDao implements StockService{

	@Override
	public String createStock(Stock stock) {
		return (String) this.getDao().create(stock);
	}

	@Override
	public Stock updateStock(Stock stock) {
		return (Stock) this.getDao().update(stock);
	}

	@Override
	public Stock findStroeByStockId(String stockId) {
		List<?> stockList = (List<?>)this.getDao().query("getStockByStockId", new String[]{stockId});
		return stockList == null ? null : (Stock)stockList.get(0);
	}

	@Override
	public void deleteByStockId(String stockId) {
		this.getDao().delete(Stock.class, stockId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Stock> findAllStock() {
		return (List<Stock>)this.getDao().query("getAllStock");
	}


}
