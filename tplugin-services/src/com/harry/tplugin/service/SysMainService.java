package com.harry.tplugin.service;

import java.util.List;

import com.harry.tplugin.bean.SysMain;


public interface SysMainService
{
    /**
     * Create sysMain
     * @param sysMain is the SysMain
     * @return 
     */
    public void createSysMain(SysMain sysMain);
    
    /**
     * Update sysMain
     * @param sysMain is the SysMain
     * @return SysMain
     */
    public SysMain updateSysMain(SysMain sysMain);
    
    /**
     * Delete sysMain
     * @param sysMain is the SysMain
     */
    public void deleteSysMain(SysMain sysMain);
    
    
    /**
     * find all sysMain
     * @return List
     */
    public List<?> findAllSysMain();
    
    
    /**
     * 
     * @param clazz
     * @param id
     */
    public void deleteBySysId(String sysId);
    
    /**
     * 
     * @param proId
     * @return
     */
    public SysMain findSysMainBySysId(String sysId);
    
}
