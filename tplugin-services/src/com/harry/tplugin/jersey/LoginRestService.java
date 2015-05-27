package com.harry.tplugin.jersey;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;

import com.harry.tplugin.bean.Login;
import com.harry.tplugin.service.LoginService;
import com.harry.tplugin.util.JacksonUtils;
import com.harry.tplugin.util.Logger4j;
import com.harry.tplugin.util.ServiceFactoryBean;

@Path("/")
public class LoginRestService{

	private static final Logger log = Logger4j.getLogger(LoginRestService.class);
	
	private static LoginService loginService = ServiceFactoryBean.getLoginService();
	
	private final static String ADMIN_POWERCODE = "11111111";
	
	@POST
	@Path("/login")
	public String login( @Context HttpServletRequest request,@FormParam("userName") String userName,@FormParam("password") String password){
		System.out.println("@mawz:  userName:"+userName+"  password:"+password);
	    Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            if (null == userName || null == password){
            	resultMap.put("ack", "failed");
            	resultMap.put("cause", "用户名或密码不能为空！");
            	return JacksonUtils.getJsonString(resultMap);
            }
            Login logInfo = loginService.findLoginByUserName(userName);
            if (null == logInfo){
            	resultMap.put("ack", "failed");
            	resultMap.put("cause", "没有此用户！");
            	return JacksonUtils.getJsonString(resultMap);
            }
            if (!logInfo.getPassword().equals(password))
            {
            	resultMap.put("ack", "failed");
            	resultMap.put("cause", "密码错误！");
            	return JacksonUtils.getJsonString(resultMap);
            }
            if (!isIpAddrPermit(getIpAddr(request), logInfo))
            {
            	resultMap.put("ack", "failed");
            	resultMap.put("cause", "您的IP地址没有登陆权限！");
            	return JacksonUtils.getJsonString(resultMap);
            }
            if (ADMIN_POWERCODE.equals(logInfo.getPowerCode()))
            {
            	resultMap.put("ack", "success");
            	return JacksonUtils.getJsonString(resultMap);
            }
            else
            {
            	resultMap.put("ack", "customer");
            	return JacksonUtils.getJsonString(resultMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("ack", "failed");
        }
        
        return JacksonUtils.getJsonString(resultMap);
	}
	

	private boolean isIpAddrPermit(String ip, Login logInfo)
	{
	    String ipList = logInfo.getIpAdress();
	    if (null != ipList && ipList.contains(ip))
	        return true;
	    return false;
	}
    private String getIpAddr(HttpServletRequest request) {
       String ip = request.getHeader("x-forwarded-for");
       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getHeader("Proxy-Client-IP");
       }
       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getHeader("WL-Proxy-Client-IP");
       }
       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getRemoteAddr();
       }
       return ip;
   }
	
}
