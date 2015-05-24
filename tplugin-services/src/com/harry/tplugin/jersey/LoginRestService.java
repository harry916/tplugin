package com.harry.tplugin.jersey;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.harry.tplugin.bean.Login;
import com.harry.tplugin.service.LoginService;
import com.harry.tplugin.util.Cause;
import com.harry.tplugin.util.JacksonUtils;
import com.harry.tplugin.util.Logger4j;
import com.harry.tplugin.util.ServiceFactoryBean;
import com.harry.tplugin.util.TaoClientWrapper;
import com.harry.tplugin.util.TpluginType.TPLUGIN_RETURN_CODE;

@Path("/login")
public class LoginRestService {

	private static final Logger log = Logger4j.getLogger(LoginRestService.class);
	
	private static LoginService loginService = ServiceFactoryBean.getLoginService();
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON) 
	public String login(String jsonString, @Context HttpServletRequest request){
	    Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        try {
            @SuppressWarnings("unchecked")
            Map<String, String> map =  JacksonUtils.objectMapper.readValue(jsonString, Map.class);
            if(null == map.get("userName") || null == map.get("password")){
                return Cause.getFailcode(TPLUGIN_RETURN_CODE.TPLUGIN_ERR_ARGUMENT.getValue(), "login", "userName or password can not be null");
            }
            Login logInfo = loginService.findLoginByUserName(map.get("userName"));
            if (null == logInfo){
                return Cause.getFailcode(TPLUGIN_RETURN_CODE.TPLUGIN_ERR_LOGIN_NAME.getValue(), "login", "name not exist");
            }
            if (!logInfo.getPassword().equals(map.get("password")))
            {
                return Cause.getFailcode(TPLUGIN_RETURN_CODE.TPLUGIN_ERR_LOGIN_PASSWORD.getValue(), "login", "password failed");
            }
            if (!isIpAddrPermit(getIpAddr(request), logInfo))
            {
                return Cause.getFailcode(TPLUGIN_RETURN_CODE.TPLUGIN_ERR_LOGIN_IP.getValue(), "login", "IP not permitted");
            }
           
            resultMap.put("ack", "success");
            dataMap.put("userName", logInfo.getUserName());
            dataMap.put("token", null);
            resultMap.put("data", dataMap);
            TaoClientWrapper.getInstance();
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
