package com.harry.tplugin.service;

import java.util.List;
import java.util.Map;

import com.harry.tplugin.bean.SendAllow;

public interface SendAllowService {

	/**
	 * 
	 * @param sendAllow
	 * @return
	 */
	public void createSendAllow(SendAllow sendAllow);
	
	/**
	 * 
	 * @param sendAllow
	 * @return
	 */
	public SendAllow updateSendAllow(SendAllow sendAllow);
	
	/**
	 * 
	 * @param sendAllowId
	 * @return
	 */
	public SendAllow findSendAllowByStateProType(String state, String proType);
	
	public List<SendAllow> findAllSendAllow();
	
	public List<SendAllow> findAllSendAllowByProType(String proType);
	
	public void deleteByStateProType(String state, String proType);
}
