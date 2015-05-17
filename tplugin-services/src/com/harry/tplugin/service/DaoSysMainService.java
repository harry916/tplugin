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
    public void createSysMain(SysMain sysMain)
    {
        SysMain sm = findSysMainBySysId(sysMain.getSysId());
        if (null != sm)
        {
        	updateSysMain(sysMain);
        }
        else
        {
        	this.getDao().create(sysMain);
        }
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
        @SuppressWarnings("unchecked")
		List<SysMain> list =  (List<SysMain>) getDao().query("getAllSysMain");
        return null == list || list.isEmpty() ? null : list;
    }
    

	@Override
	public void deleteBySysId(String sysId) {
		this.getDao().delete(SysMain.class, sysId);
	}

	@Override
	public SysMain findSysMainBySysId(String sysId) {
		if (null == sysId ) return null;
		List<?> datalist= (List<?>)getDao().query("getSysMainBySysId", new String[]{sysId});
		return datalist == null || datalist.isEmpty() ? null : (SysMain)datalist.get(0);
	}

}
