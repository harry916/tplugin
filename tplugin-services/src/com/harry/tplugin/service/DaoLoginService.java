package com.harry.tplugin.service;

import java.util.List;

import com.harry.tplugin.bean.Login;
import com.harry.tplugin.dao.basic.AbstractServiceDao;

public class DaoLoginService extends AbstractServiceDao implements LoginService{

	@Override
	public void createLogin(Login login) {
	    Login lg = findLoginByUserName(login.getUserName());
	    if (null != lg)
	    {
	    	updateLogin(login);
	    }
	    else
	    {
		    this.getDao().create(login);
	    }
	}

	@Override
	public Login updateLogin(Login login) {
		// TODO Auto-generated method stub
		return (Login)this.getDao().update(login);
	}

	@Override
	public void deleteLoginByUserName(String userName) {
		this.getDao().delete(Login.class, userName);
	}

	@Override
	public Login findLoginByUserName(String userName) {
		List<?> loginList = (List<?>)getDao().query("getLoginByUserName", new String[]{userName});
		return loginList == null || loginList.isEmpty() ? null : (Login)loginList.get(0);
	}

}
