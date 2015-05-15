package com.harry.tplugin.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.harry.tplugin.service.LoginService;
import com.harry.tplugin.service.ProductService;
import com.harry.tplugin.service.SendAllowService;
import com.harry.tplugin.service.SendOrderService;
import com.harry.tplugin.service.StockService;
import com.harry.tplugin.service.SysMainService;

public class ServiceFactoryBean
{
    private static ApplicationContext ctx = new ClassPathXmlApplicationContext("basic-config.xml");
    
  
    public static Object getService(String beanName)
    {
        return ctx.getBean(beanName);
    }
    
    public static ProductService getProductService()
    {
        return (ProductService) getService("productService");
    }
    
    public static LoginService getLoginService()
    {
        return (LoginService) getService("loginService");
    }
    
    public static SysMainService getSysMainService()
    {
        return (SysMainService) getService("sysMainService");
    }
    
    public static StockService getStockService()
    {
        return (StockService) getService("stockService");
    }
    
    public static SendAllowService getSendAllowService()
    {
        return (SendAllowService) getService("sendAllowService");
    }
    
    public static SendOrderService getSendOrderService()
    {
        return (SendOrderService) getService("sendOrderService");
    }
    
}
