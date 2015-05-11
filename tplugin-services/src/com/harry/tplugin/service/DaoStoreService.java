package com.harry.tplugin.service;

import java.util.List;

import com.harry.tplugin.bean.Product;
import com.harry.tplugin.bean.Store;
import com.harry.tplugin.dao.basic.AbstractServiceDao;

public class DaoStoreService extends AbstractServiceDao implements StoreService{

	@Override
	public String createStore(Store store) {
		return (String) this.getDao().create(store);
	}

	@Override
	public Store updateStore(Store store) {
		return (Store) this.getDao().update(store);
	}

	@Override
	public Store findStroeByStoreId(String storeId) {
		List<?> storeList = (List<?>)this.getDao().query("getStoreByStoreId", new String[]{storeId});
		return storeList == null ? null : (Store)storeList.get(0);
	}

	@Override
	public void deleteByStoreId(String storeId) {
		this.getDao().delete(Store.class, storeId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Store> findAllStore() {
		return (List<Store>)this.getDao().query("getAllStore");
	}


}
