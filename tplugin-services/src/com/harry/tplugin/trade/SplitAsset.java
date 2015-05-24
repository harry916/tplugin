package com.harry.tplugin.trade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.harry.tplugin.bean.SendOrder;
import com.harry.tplugin.service.SendAllowService;
import com.harry.tplugin.service.SendOrderService;
import com.harry.tplugin.trade.OrderAsset.OrderItem;
import com.harry.tplugin.trade.StoreAsset.StoreDetail;
import com.harry.tplugin.util.JacksonUtils;
import com.harry.tplugin.util.ServiceFactoryBean;

public class SplitAsset {
    
    private SendAllowService sendAllowService = ServiceFactoryBean.getSendAllowService();
    private SendOrderService sendOrderService = ServiceFactoryBean.getSendOrderService();
    
    private enum ProType
    {
        LIVEBODY(01), 
        WATERWEEDS(02), 
        EQUIPMENT(03),
        UNSTANDARD_EQUIPMENT(04),
        UNKNOWN(077);
        
        private final int value;
        
        ProType(int value)
        {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
        public static ProType valueOf(int value)
        {
            for (int i = 0; i < ProType.values().length; i++)
            {
                if (value == ProType.values()[i].getValue())
                {
                    return ProType.values()[i];
                }
            }
            return UNKNOWN;
        }
    }
    public class SplitItem
    {
        private int deliveryId;
        private String proId;
        private String proName;
        private int orderNum;
        private String storeId;
        private int storeNum;
        private int index;
        public SplitItem() {
            super();
        }
        public int getDeliveryId() {
            return deliveryId;
        }
        public void setDeliveryId(int deliveryId) {
            this.deliveryId = deliveryId;
        }
        public String getProId() {
            return proId;
        }
        public void setProId(String proId) {
            this.proId = proId;
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
        public String getStoreId() {
            return storeId;
        }
        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }
        public int getStoreNum() {
            return storeNum;
        }
        public void setStoreNum(int storeNum) {
            this.storeNum = storeNum;
        }
        public int getIndex() {
            return index;
        }
        public void setIndex(int index) {
            this.index = index;
        }
        @Override
        public String toString() {
            return "SplitItem [deliveryId=" + deliveryId + ", proId=" + proId
                    + ", proName=" + proName + ", orderNum=" + orderNum
                    + ", storeId=" + storeId + ", storeNum=" + storeNum
                    + ", index=" + index + "]";
        }
        
    }
    
    private void splitProcess(List<SplitItem> splitItems, List<String> proTypeList, List<OrderItem> orderList, List<StoreDetail> storeDetails, String state, int deliveryId)
    {
        int liveSize = proTypeList.size();
        int orderSize = orderList.size();
        int storeSize = storeDetails.size();

        for (int i = 0; i < liveSize; i++)
        {
            OrderItem orItem = null;
            String proId = proTypeList.get(i);
            for (int j = 0; j < orderSize; i++)
            {
                if (orderList.get(j).getProId().equals(proId))
                {
                    orItem = orderList.get(j);
                    break;
                }
            }
            if (null == orItem)
                continue;
            List<StoreDetail> tmpStoreDetails = new ArrayList<StoreAsset.StoreDetail>();
           StoreDetail storeDetail = null;
            for (int j = 0; j < storeSize; i++)
            {
                storeDetail = storeDetails.get(j);
                if (storeDetail.getProId().equals(proId))
                {
                    tmpStoreDetails.add(storeDetail);
                }
            }

            List<SendOrder> lt = sendOrderService.findSendOrderByState(state);
            if (null != lt)
            {
                try {
                    String indexJson = lt.get(0).getSendIndex();
                    Map<String, String>map = JacksonUtils.objectMapper.readValue(indexJson, Map.class);
                    
                    Object[] key_arr = map.keySet().toArray();   
                    Arrays.sort(key_arr);   
                    for  (Object key : key_arr) {   
                        String value = map.get(key);   
                        SplitItem splitItem = new SplitItem();
                        splitItem.setDeliveryId(deliveryId);
                        splitItem.setProId(proId);
                        splitItem.setProName(orItem.getProName());
                        splitItem.setOrderNum((int) orItem.getNum());
                        String storeId = "";
                        if (value.contains("上海"))
                        {
                            storeId = "021";
                        }
                        else if (value.contains("广州"))
                        {
                            storeId = "022";
                        }
                        else if (value.contains("北京"))
                        {
                            storeId = "010";
                        }
                        else
                        {
                            
                        }
                        splitItem.setIndex(Integer.valueOf((String)key));
                        splitItem.setStoreId(storeId);
                        for (int k = 0; k < tmpStoreDetails.size(); k++)
                        {
                            storeDetail = tmpStoreDetails.get(i);
                            if (proId.equals(storeDetail.getProId()) && storeId.equals(storeDetail.getStoreId()))
                            {
                                splitItem.setStoreNum(storeDetail.getNum());
                                break;
                            }
                        }
                        splitItems.add(splitItem);
                    } 
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        }
    }
    
    
    public List<SplitItem> getSplitList(String state, List<OrderItem> orderList, List<StoreDetail> storeDetails)
    {
        Map<String, String> storeIdState = new HashMap<String, String>();
        storeIdState.put("上海", "021");
        storeIdState.put("广州", "022");
        storeIdState.put("北京", "010");
        
        List<SplitItem> splitItems = new ArrayList<SplitAsset.SplitItem>();
        List<String> waterweeds = new ArrayList<String>();
        List<String> liveBody = new ArrayList<String>();
        List<String> equipment = new ArrayList<String>();
        List<String> unstandardEquipment = new ArrayList<String>();
        
        SendAllowService sendAllowService = ServiceFactoryBean.getSendAllowService();
        SendOrderService sendOrderService = ServiceFactoryBean.getSendOrderService();

        int storeSize = storeDetails.size();
        for (int i = 0; i < storeSize; i++)
        {
            StoreDetail storeDetail = storeDetails.get(i);
            String storeId = storeDetail.getStoreId();
            String unstandardStr = storeDetail.getUnstandard();
            int proType = Integer.valueOf(storeDetail.getProType());
            if (ProType.LIVEBODY.getValue() == proType)
            {
                liveBody.add(storeDetail.getProId());
            }
            else if (ProType.WATERWEEDS.getValue() == proType)
            {
                waterweeds.add(storeDetail.getProId());
            }
            else if (ProType.EQUIPMENT.getValue() == proType)
            {
                if (null != unstandardStr)
                {
                    unstandardEquipment.add(storeDetail.getProId());
                }
                else
                {
                    equipment.add(storeDetail.getProId());
                }
            }
            else if (ProType.UNSTANDARD_EQUIPMENT.getValue() == proType)
            {
                unstandardEquipment.add(storeDetail.getProId());
            }
        }
        int deliveryId = 0;
        if (null != liveBody)
        {
            splitProcess(splitItems, liveBody, orderList, storeDetails, state, ++deliveryId);
        }
        if (null != waterweeds)
        {
            splitProcess(splitItems, waterweeds, orderList, storeDetails, state, ++deliveryId);
        }
        if (null != equipment)
        {
            splitProcess(splitItems, equipment, orderList, storeDetails, state, ++deliveryId);
        }
        if (null != unstandardEquipment)
        {
            splitProcess(splitItems, unstandardEquipment, orderList, storeDetails, state, ++deliveryId);
        }
        
        
        return splitItems;
    }
    
}
