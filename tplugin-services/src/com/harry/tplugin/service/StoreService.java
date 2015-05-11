package com.harry.tplugin.service;

import java.util.List;

import com.harry.tplugin.bean.Store;

public interface StoreService {

	/**
	 * 
	 * @param store
	 * @return
	 */
	public String createStore(Store store);
	
	/**
	 * 
	 * @param store
	 * @return
	 */
	public Store updateStore(Store store);
	
	/**
	 * 
	 * @param storeId
	 * @return
	 */
	public Store findStroeByStoreId(String storeId);
	
	public List<Store> findAllStore(); 
	
	public void deleteByStoreId(String storeId);
}
