package com.harry.tplugin.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Trade;
import com.taobao.api.internal.util.WebUtils;
import com.taobao.api.request.TradeFullinfoGetRequest;
import com.taobao.api.response.TradeFullinfoGetResponse;

public class TaoClientWrapper {
	private String mCode;
	private String mAppKey;
	private String mAppSecret;
	private String mUrl; 
	private String mApiUrl;
	private String mRedirectUrl;
	private String mAccessToken;
	
	private static TaoClientWrapper sInstance = null;
    private int mAccessTokenExpiredPeriod = 5000; // get

    private Runnable accessTokenRunnable = new Runnable() {
		@Override
		public void run() {
			while (true)
			{
				System.out.println(System.currentTimeMillis());
				getAccessTokenInternal();
				try {
					Thread.sleep(mAccessTokenExpiredPeriod);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};

	private void getAccessTokenInternal()
	{
		Map<String, String> props = new HashMap<String, String>();
		props.put("grant_type", "authorization_code");
		props.put("code", mCode);
		props.put("client_id", mAppKey);
		props.put("client_secret", mAppSecret);
		props.put("redirect_uri", mRedirectUrl);
		props.put("view", "web");
		String responseJson = "";
		try {
			responseJson = WebUtils.doPost(mUrl, props, 30000, 30000);
			Logger4j.getLogger(TaoClientWrapper.class).debug("@harry responseJson="+responseJson);
			Map<String, String> map =  JacksonUtils.objectMapper.readValue(responseJson, Map.class);
			mAccessToken = map.get("access_token");
			mAccessTokenExpiredPeriod = Integer.valueOf(map.get("expires_in")) * 1000; //ms
		} catch (IOException e) {
            Logger4j.getLogger(TaoClientWrapper.class).debug("@harry getAccessTokenInternal", e);
		}
	}
	
	private void taoClientConfigInit()
	{
		  try{
	           String path = TaoClientWrapper.class.getResource("/").getPath();
	           String websiteUrl = path.replace("/classes","").replace("%20", " ") + "taoclient.properties";
	           Logger4j.getLogger(TaoClientWrapper.class).debug("@harry websiteUrl="+websiteUrl);
	           setParameter(websiteUrl);
	       }catch(Exception e){
	            Logger4j.getLogger(TaoClientWrapper.class).debug("@harry taoClientConfigInit", e);
	       }       
	}
	
	private void setParameter(String path) throws Exception {
		Properties properties = new Properties();
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(path);
			properties.load(fileInputStream);
			mRedirectUrl = properties.getProperty("taoclient.redirecturl");
			mUrl = properties.getProperty("taoclient.url");
			mApiUrl = properties.getProperty("taoclient.apiurl");
			mCode = properties.getProperty("taoclient.code");
			mAppKey = properties.getProperty("taoclient.appkey", "23165697");
			mAppSecret = properties.getProperty("taoclient.appsecret", "67f2880a56f37d0ed90f111160498d4a");
		} catch (Exception e) {
            Logger4j.getLogger(TaoClientWrapper.class).debug("@harry setParameter", e);
		} finally {
			if (null != fileInputStream) {
				fileInputStream.close();
				fileInputStream = null;
			}
		}
	}
	
	private TaoClientWrapper() {
		taoClientConfigInit();
		new Thread(accessTokenRunnable).start();
		System.out.println("hello000");
	}

	public static TaoClientWrapper getInstance()
	{
		if (null == sInstance)
			sInstance = new TaoClientWrapper();
		return sInstance;
	}

	/**
	 * 
	 * @param tid
	 * @param fields  e.g. "tid,type,status,payment,orders"
	 * @return
	 */
	public Trade getTradeFullInfo(long tid, String fields)
	{
	    return getTradeFullInfo(tid, fields, 0);
	}
	
	private Trade getTradeFullInfo(long tid, String fields, int callCnt)
    {
	    Trade  fullInfo = null;
        TaobaoClient client=new DefaultTaobaoClient(mApiUrl, mAppKey, mAppSecret);
        TradeFullinfoGetRequest req=new TradeFullinfoGetRequest();
        req.setFields(fields);
        req.setTid(tid);
        try {
            TradeFullinfoGetResponse response = client.execute(req , mAccessToken);
            if (response.isSuccess())
            {
                fullInfo = response.getTrade();
            }
            else
            {
                getAccessTokenInternal();
                // API服务不可用或超时，则重试
                if ("520".equals(response.getErrorCode()) && callCnt++ < 3) {
                    return getTradeFullInfo(tid, mAccessToken, callCnt);
                } else {
                    Logger4j.getLogger(TaoClientWrapper.class).debug("@harry getTradeFullInfo 查询订单详情失败 error code: " + response.getErrorCode());
                    return fullInfo;
                }
            }
        } catch (ApiException e) {
            Logger4j.getLogger(TaoClientWrapper.class).debug("@harry getTradeFullInfo", e);
            getAccessTokenInternal();
        }
        return fullInfo;
    }
}
