package com.harry.tplugin.service;

import java.util.List;

import com.harry.tplugin.bean.Stock;
import com.harry.tplugin.bean.StockSumView;
import com.harry.tplugin.dao.basic.AbstractServiceDao;

public class DaoStockService extends AbstractServiceDao implements StockService{

	@Override
	public void createStock(Stock stock) {
		Stock sk = findStockByProIdStoreId(stock.getProId(), stock.getStoreId());
		if (null != sk)
		{
			updateStock(stock);
		}
		else
		{
			this.getDao().create(stock);
		}
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

	@SuppressWarnings("unchecked")
	@Override
	public Stock findStockByProIdStoreId(String proId, String storeId) {
		List<Stock> list = null;
		if ("021".equals(storeId))
		{
			list = (List<Stock>) this.getDao().query("getStockSHViewByProId", new String[]{proId});
		}
		else if ("010".equals(storeId))
		{
			list = (List<Stock>) this.getDao().query("getStockBJViewByProId", new String[]{proId});
		}
		else if ("020".equals(storeId))
		{
			list = (List<Stock>) this.getDao().query("getStockGZViewByProId", new String[]{proId});
		}
		return null == list || list.isEmpty() ? null : list.get(0);
	}

	@Override
	public void deleteStockByProIdStoreId(String proId, String storeId) {
		Stock stock = findStockByProIdStoreId(proId, storeId);
		if (null != stock)
		{
			this.getDao().delete(stock);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Stock> findAllStockByStoreId(String storeId) {
		List<?> list = null;
		if ("021".equals(storeId))
		{
			list = this.getDao().query("getAllStockSHView");
		}
		else if ("010".equals(storeId))
		{
			list = this.getDao().query("getAllStockBJView");
		}
		else if ("020".equals(storeId))
		{
			list = this.getDao().query("getAllStockGZView");
		}
		return null == list || list.isEmpty() ? null : (List<Stock>)list;
	}

	@Override
	public List<StockSumView> findAllStockSumView() {
		@SuppressWarnings("unchecked")
		List<StockSumView> list = (List<StockSumView>)this.getDao().query("getAllStockSumView"); 
		return null == list || list.isEmpty() ? null : list;
	}

	@Override
	public StockSumView findStockSumViewByProId(String proId) {
		List<?> list = (List<?>)this.getDao().query("getStockSumViewByProId", new String[]{proId});
		return list == null || list.isEmpty() ? null : (StockSumView)list.get(0);
	}

}
