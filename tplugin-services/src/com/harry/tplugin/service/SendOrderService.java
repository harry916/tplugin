package com.harry.tplugin.service;

import java.util.List;
import java.util.Map;

import com.harry.tplugin.bean.SendOrder;

public interface SendOrderService {

	/**
	 * 
	 * @param sendOrder
	 * @return
	 */
	public void createSendOrder(SendOrder sendOrder);
	
	/**
	 * 
	 * @param sendOrder
	 * @return
	 */
	public SendOrder updateSendOrder(SendOrder sendOrder);
	
	/**
	 * 
	 * @param sendOrderId
	 * @return
	 */
	public SendOrder findSendOrderByStateProType(String state, String proType);
	
	public List<SendOrder> findSendOrderByState(String state);
	
	public List<SendOrder> findAllSendOrder(); 
	
	public void deleteByStateProType(String state, String proType);
}
