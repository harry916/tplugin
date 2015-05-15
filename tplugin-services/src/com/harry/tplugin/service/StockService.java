package com.harry.tplugin.service;

import java.util.List;

import com.harry.tplugin.bean.Stock;

public interface StockService {

	/**
	 * 
	 * @param stock
	 * @return
	 */
	public void createStock(Stock stock);
	
	/**
	 * 
	 * @param stock
	 * @return
	 */
	public Stock updateStock(Stock stock);
	
	public Stock findStockByProIdStoreId(String proId, String storeId);
	
	public List<Stock> findAllStock();
	
	public List<Stock> findAllStockByStoreId(String storeId); 
	
	public void deleteStockByProIdStoreId(String proId, String storeId);
}
