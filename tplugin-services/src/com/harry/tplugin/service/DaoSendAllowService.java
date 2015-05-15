package com.harry.tplugin.service;

import java.util.List;
import java.util.Map;

import com.harry.tplugin.bean.SendAllow;
import com.harry.tplugin.dao.basic.AbstractServiceDao;

public class DaoSendAllowService extends AbstractServiceDao implements SendAllowService{
	
	@Override
	public void createSendAllow(SendAllow sendAllow) {
		this.getDao().create(sendAllow);
	}

	@Override
	public SendAllow updateSendAllow(SendAllow sendAllow) {
		return (SendAllow) this.getDao().update(sendAllow);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SendAllow> findAllSendAllow() {
		return (List<SendAllow>)this.getDao().query("getAllSendAllow");
	}

	@Override
	public SendAllow findSendAllowByStateProType(String state, String proType) {
		List<?> sendAllowList = (List<?>)this.getDao().query("getSendAllowByStateProType", new String[]{state, proType});
		return sendAllowList == null || sendAllowList.isEmpty() ? null : (SendAllow)sendAllowList.get(0);
	}

	@Override
	public void deleteByStateProType(String state, String proType) {
		SendAllow sendAllow = findSendAllowByStateProType(state, proType);
		if (null != sendAllow)
		{
			this.getDao().delete(sendAllow);
		}
	}


}
