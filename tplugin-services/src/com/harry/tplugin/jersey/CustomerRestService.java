package com.harry.tplugin.jersey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.harry.tplugin.trade.OrderAsset;
import com.harry.tplugin.trade.OrderAsset.OrderItem;
import com.harry.tplugin.trade.SendAsset.DeliveryFormal;
import com.harry.tplugin.trade.SplitAsset.SplitItem;
import com.harry.tplugin.trade.StoreAsset.StoreDetail;
import com.harry.tplugin.trade.SendAsset;
import com.harry.tplugin.trade.SplitAsset;
import com.harry.tplugin.trade.StoreAsset;
import com.harry.tplugin.util.Cause;
import com.harry.tplugin.util.JacksonUtils;
import com.harry.tplugin.util.Logger4j;
import com.harry.tplugin.util.TaoClientWrapper;
import com.harry.tplugin.util.TpluginType.TPLUGIN_RETURN_CODE;
import com.taobao.api.domain.Trade;

@Path("/customer")
public class CustomerRestService {
    private static final Logger log = Logger4j.getLogger(CustomerRestService.class);
    private static TaoClientWrapper sTaoClient = TaoClientWrapper.getInstance();
    private OrderAsset mOrderAsset = new OrderAsset();
    private StoreAsset mStoreAsset = new StoreAsset();
    private SplitAsset mSplitAsset = new SplitAsset();
    private SendAsset mSendAsset = new SendAsset();

    public static class ItemInfo{
        private String proName;
        private int orderNum;
        private int shNum;
        private int bjNum;
        private int gzNum;
        public ItemInfo() {
            super();
        }
        public String getProName() {
            return proName;
        }
        public void setProName(String proName) {
            this.proName = proName;
        }
        public int getOrderNum() {
            return orderNum;
        }
        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }
        public int getShNum() {
            return shNum;
        }
        public void setShNum(int shNum) {
            this.shNum = shNum;
        }
        public int getBjNum() {
            return bjNum;
        }
        public void setBjNum(int bjNum) {
            this.bjNum = bjNum;
        }
        public int getGzNum() {
            return gzNum;
        }
        public void setGzNum(int gzNum) {
            this.gzNum = gzNum;
        }
        @Override
        public String toString() {
            return "ItemInfo [proName=" + proName + ", orderNum=" + orderNum
                    + ", shNum=" + shNum + ", bjNum=" + bjNum + ", gzNum="
                    + gzNum + "]";
        }

    }
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{tid}")
    public String getUIDisplayData(@PathParam("tid") String tid)
    {
        if (!isTidValid(tid))
        {
            return Cause.getFailcode(TPLUGIN_RETURN_CODE.TPLUGIN_ERR_INVALID_TID.getValue(), "getUIDisplayData", "tid invalid");
        }
        long n_tid = Long.valueOf(tid);
        String fields = "receiver_state,receiver_address,num,num_iid,orders";
        Trade trade = sTaoClient.getTradeFullInfo(n_tid, fields);
        if (null == trade)
        {
            return Cause.getFailcode(TPLUGIN_RETURN_CODE.TPLUGIN_ERR_GET_TRADE_EXCEPTION.getValue(), "getUIDisplayData", "get trade failed");
        }
        Map<String, String> orderCustomerInfo = mOrderAsset.getOrderCustomerInfo(trade);
        List<OrderItem> orderList = mOrderAsset.getOrderList(trade);
        List<StoreDetail> stDetais = mStoreAsset.getStoreDetailList(orderList);
        List<SplitItem> splitItems = mSplitAsset.getSplitList(orderCustomerInfo.get("receiver_state"), orderList, stDetais);
        List<DeliveryFormal> deliveryFormal = mSendAsset.getSendList(orderCustomerInfo.get("receiver_state"), splitItems);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("tid", tid);
        resultMap.put("state", orderCustomerInfo.get("receiver_state"));
        List<ItemInfo> itemList = new ArrayList<CustomerRestService.ItemInfo>();
        List<Integer> shDeliver = new ArrayList<Integer>();
        List<Integer> bjDeliver = new ArrayList<Integer>();
        List<Integer> gzDeliver = new ArrayList<Integer>();
        List<Integer> beyond = new ArrayList<Integer>();
        List<Integer> lack = new ArrayList<Integer>();
        for (int i = 0; i < orderList.size(); i++)
        {
            OrderItem oder = orderList.get(i);
            ItemInfo tItem = new ItemInfo();
            tItem.setProName(oder.getProName());
            tItem.setOrderNum((int) oder.getNum());
            String proId = oder.getProId();
            for (int j = 0; j < splitItems.size(); j++)
            {
                SplitItem splitItem = splitItems.get(j);
                if (proId.equals(splitItem.getProId()))
                {
                    if ("021".equals(splitItem.getStoreId()))
                        tItem.setShNum(splitItem.getStoreNum());
                    if ("010".equals(splitItem.getStoreId()))
                        tItem.setBjNum(splitItem.getStoreNum());
                    if ("022".equals(splitItem.getStoreId()))
                        tItem.setGzNum(splitItem.getStoreNum());
                }
            }
            itemList.add(tItem);
            
            for (int j = 0; j < deliveryFormal.size(); j++)
            {
                DeliveryFormal dFormal = deliveryFormal.get(j);
                if (!dFormal.getProId().equals(proId))
                    continue;
                if ("缺货不发".equals(dFormal.getComment()))
                {
                    lack.add(i);
                }
                else if ("上海发货".equals(dFormal.getComment()))
                {
                    shDeliver.add(i);
                }
                else if ("北京发货".equals(dFormal.getComment()))
                {
                    bjDeliver.add(i);
                }
                else if ("广州发货".equals(dFormal.getComment()))
                {
                    gzDeliver.add(i);
                }
                else if ("超范围不发".equals(dFormal.getComment()))
                {
                    beyond.add(i);
                }
            }
        }
        resultMap.put("itemInfoList", itemList);
        resultMap.put("shDelivery", shDeliver);
        resultMap.put("bjDelivery", bjDeliver);
        resultMap.put("gzDelivery", gzDeliver);
        resultMap.put("lack", lack);
        resultMap.put("beyond", beyond);
        
        return JacksonUtils.getJsonString(resultMap);
    }
    
    private boolean isTidValid(String tid)
    {
        try {
            Long.parseLong(tid);
            return true;
        } 
        catch (Exception e)
        {
            return false;
        }
    }
    
   

}
