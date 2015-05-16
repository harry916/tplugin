package com.harry.tplugin.service;

import java.util.List;

import com.harry.tplugin.bean.Stock;
import com.harry.tplugin.bean.StockSumView;

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
	
	public List<StockSumView> findAllStockSumView();
	
	public StockSumView findStockSumViewByProId(String proId);
	
	public void deleteStockByProIdStoreId(String proId, String storeId);
}
