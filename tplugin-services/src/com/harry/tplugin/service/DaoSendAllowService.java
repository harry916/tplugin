package com.harry.tplugin.service;

import java.util.List;

import com.harry.tplugin.bean.SendAllow;
import com.harry.tplugin.dao.basic.AbstractServiceDao;

public class DaoSendAllowService extends AbstractServiceDao implements SendAllowService{
	@Override
	public void createSendAllow(SendAllow sendAllow) {
		SendAllow sa = findSendAllowByStateProType(sendAllow.getState(), sendAllow.getProType());
		if (null != sa)
		{
			updateSendAllow(sendAllow);
		}
		else
		{
			this.getDao().create(sendAllow);
		}
	}

	@Override
	public SendAllow updateSendAllow(SendAllow sendAllow) {
		return (SendAllow) this.getDao().update(sendAllow);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SendAllow> findAllSendAllow() {
		List<SendAllow> list = (List<SendAllow>) this.getDao().query("getAllSendAllow");
		return null == list || list.isEmpty() ? null : list;
	}

	@Override
	public SendAllow findSendAllowByStateProType(String state, String proType) {
		List<?> sendAllowList = null;
		if ("活体".equals(proType))
		{
			sendAllowList = (List<?>)this.getDao().query("getAllowTypeLiveByState", new String[]{state});
		}
		else if ("普通器材".equals(proType))
		{
			sendAllowList = (List<?>)this.getDao().query("getAllowTypeNormalByState", new String[]{state});
		}
		else if ("超标器材".equals(proType))
		{
			sendAllowList = (List<?>)this.getDao().query("getAllowTypeUnNormalByState", new String[]{state});
		}
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

	@SuppressWarnings("unchecked")
	@Override
	public List<SendAllow> findAllSendAllowByProType(String proType) {
		List<SendAllow> list = null;
		if ("活体".equals(proType))
		{
			list = (List<SendAllow>) this.getDao().query("getAllAllowTypeLive");
		}
		else if ("普通器材".equals(proType))
		{
			list = (List<SendAllow>) this.getDao().query("getAllAllowTypeNormal");
		}
		else if ("超标器材".equals(proType))
		{
			list = (List<SendAllow>) this.getDao().query("getAllAllowTypeUnNormal");
		}
		return null == list || list.isEmpty() ? null : list;
	}


}
