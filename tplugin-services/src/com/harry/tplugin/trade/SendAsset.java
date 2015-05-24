package com.harry.tplugin.trade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.harry.tplugin.bean.SendAllow;
import com.harry.tplugin.service.SendAllowService;
import com.harry.tplugin.trade.SplitAsset.SplitItem;
import com.harry.tplugin.util.JacksonUtils;
import com.harry.tplugin.util.ServiceFactoryBean;

public class SendAsset {
    private SendAllowService sendAllowService = ServiceFactoryBean.getSendAllowService();
    
    public class DeliveryFormal
    {
        private int deliveryId;
        private String proId;
        private String proName;
        private int orderNum;
        private String storeId;
        private int deliveryNum;
        private int index;
        private String comment;
        
        public DeliveryFormal() {
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

        public int getDeliveryNum() {
            return deliveryNum;
        }

        public void setDeliveryNum(int deliveryNum) {
            this.deliveryNum = deliveryNum;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        @Override
        public String toString() {
            return "DeliveryFormal [deliveryId=" + deliveryId + ", proId="
                    + proId + ", proName=" + proName + ", orderNum=" + orderNum
                    + ", storeId=" + storeId + ", deliveryNum=" + deliveryNum
                    + ", index=" + index + ", comment=" + comment + "]";
        }

    }
    
    public List<DeliveryFormal> getSendList(String state, List<SplitItem> splitItems)
    {
        List<DeliveryFormal> deliveryFormalList = new ArrayList<SendAsset.DeliveryFormal>();
        int sz = splitItems.size();
        String preSendProId = "";
        
        for (int i = 0; i < sz; i++)
        {
            SplitItem splitItem = splitItems.get(i);
            if (preSendProId.equals(splitItem.getProId()) || splitItem.getStoreNum() < splitItem.getOrderNum())
            {
                if (i + 1 < sz)
                {
                    SplitItem nextSplitItem = splitItems.get(i+1); 
                    if (!splitItem.getProId().equals(nextSplitItem.getProId()))
                    {
                        DeliveryFormal formal = new DeliveryFormal();
                        formal.setDeliveryId(splitItem.getDeliveryId());
                        formal.setDeliveryNum(0);
                        formal.setProId(splitItem.getProId());
                        formal.setOrderNum(splitItem.getOrderNum());
                        formal.setProName(splitItem.getProName());
                        formal.setComment("缺货不发");
                        deliveryFormalList.add(formal);
                    }
                }
                continue;
            }
            String proType = splitItem.getProType();
            SendAllow sendAllow = sendAllowService.findSendAllowByStateProType(state, proType);
            String allowJson = sendAllow.getStoreJson();
            String storeId = splitItem.getStoreId();
            String storeArea = "";
            if ("021".equals(storeId))
            {
                storeArea = "上海";
            }
            else if ("010".equals(storeId))
            {
                storeArea = "北京";
            }
            else if ("022".equals(storeId))
            {
                storeArea = "广州";
            }
            try {
                boolean find = false;
                Map<String, String> map = JacksonUtils.objectMapper.readValue(allowJson, Map.class);
                Iterator<Entry<String, String>>  iter = map.entrySet().iterator();
                while (iter.hasNext())
                {
                    Entry<String, String> entry = iter.next();
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (key.contains(storeArea))
                    {
                        if ("true".equalsIgnoreCase(value))
                        {
                            DeliveryFormal formal = new DeliveryFormal();
                            formal.setDeliveryId(splitItem.getDeliveryId());
                            formal.setDeliveryNum(splitItem.getOrderNum());
                            formal.setIndex(splitItem.getIndex());
                            formal.setProId(splitItem.getProId());
                            formal.setOrderNum(splitItem.getOrderNum());
                            formal.setProName(splitItem.getProName());
                            formal.setStoreId(storeId);
                            formal.setComment(storeArea+"发货");
                            deliveryFormalList.add(formal);
                            preSendProId = storeId;
                            find = true;
                            break;
                        }
                    }
                }
                if (i + 1 < sz)
                {
                    SplitItem nextSplitItem = splitItems.get(i+1); 
                    if (!splitItem.getProId().equals(nextSplitItem.getProId()) && !find)
                    {
                        DeliveryFormal formal = new DeliveryFormal();
                        formal.setDeliveryId(splitItem.getDeliveryId());
                        formal.setProId(splitItem.getProId());
                        formal.setOrderNum(splitItem.getOrderNum());
                        formal.setProName(splitItem.getProName());
                        formal.setComment("超范围不发");
                    }
                    
                 }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        
        return deliveryFormalList;
    }
}
