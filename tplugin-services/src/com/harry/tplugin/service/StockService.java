package com.harry.tplugin.service;

import java.util.List;

import com.harry.tplugin.bean.Stock;

public interface StockService {

	/**
	 * 
	 * @param stock
	 * @return
	 */
	public String createStock(Stock stock);
	
	/**
	 * 
	 * @param stock
	 * @return
	 */
	public Stock updateStock(Stock stock);
	
	/**
	 * 
	 * @param stockId
	 * @return
	 */
	public Stock findStroeByStockId(String stockId);
	
	public List<Stock> findAllStock(); 
	
	public void deleteByStockId(String stockId);
}
