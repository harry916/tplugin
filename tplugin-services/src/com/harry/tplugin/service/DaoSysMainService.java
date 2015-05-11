package com.harry.tplugin.service;

import java.util.List;

import com.harry.tplugin.bean.SysMain;
import com.harry.tplugin.dao.basic.AbstractServiceDao;

public class DaoSysMainService extends AbstractServiceDao implements SysMainService
{
    /**
     * Create sysMain
     * @param sysMain is the SysMain
     * @return SysMain
     */
    public String createSysMain(SysMain sysMain)
    {
        return (String) this.getDao().create(sysMain);
    }

    /**
     * Update sysMain
     * @param sysMain is the SysMain
     * @return SysMain
     */
    public SysMain updateSysMain(SysMain sysMain)
    {
        return (SysMain) this.getDao().update(sysMain);
    }

    /**
     * Delete sysMain
     * @param sysMain is the SysMain
     */
    public void deleteSysMain(SysMain sysMain)
    {
        this.getDao().delete(sysMain);
    }
    
    
    /**
     * find all sysMain
     * @return List
     */
    public List<?> findAllSysMain()
    {
        return getDao().query("getAllSysMain");
    }
    

	@Override
	public void deleteBySysId(String sysId) {
		this.getDao().delete(SysMain.class, sysId);
	}

	@Override
	public SysMain findSysMainBySysId(String sysId) {
		if (null == sysId ) return null;
		List<?> datalist= (List<?>)getDao().query("getSysMainBySysId", new String[]{sysId});
		return datalist == null ? null : (SysMain)datalist.get(0);
	}

}
