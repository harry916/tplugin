package com.harry.tplugin.trade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.harry.tplugin.util.JacksonUtils;
import com.taobao.api.domain.Order;
import com.taobao.api.domain.Trade;

public class OrderAsset {
    
    public OrderAsset() {
        super();
    }

    public Map<String, String> getOrderCustomerInfo(Trade trade)
    {
        Map<String, String> mp = new HashMap<String, String>();
        mp.put("num_iid", trade.getNumIid()+"");
        mp.put("receiver_address", trade.getReceiverAddress());
        mp.put("receiver_state", trade.getReceiverState());
        return mp;
    }
    
    public class OrderItem
    {
        private String proId;
        private String proName;
        private long num;
        
        
        public OrderItem() {
            super();
        }

        public OrderItem(String proId, String proName, long num) {
            super();
            this.proId = proId;
            this.proName = proName;
            this.num = num;
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

        public long getNum() {
            return num;
        }

        public void setNum(long num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return "OrderItem [proId=" + proId + ", proName=" + proName
                    + ", num=" + num + "]";
        }
        
    }
    
    public List<OrderItem> getOrderList(Trade trade)
    {
        List<OrderItem> orderList = new ArrayList<OrderItem>();
        long num = trade.getNum();
        List<Order> orders = trade.getOrders();
        OrderItem item = new OrderItem();
        for (int i = 0; i < orders.size(); i++)
        {
            Order order = orders.get(i);
            item.setProId(order.getOuterIid());
            item.setProName(order.getTitle());
            item.setNum(order.getNum());
            orderList.add(item);
        }
        return orderList;
    }
}
