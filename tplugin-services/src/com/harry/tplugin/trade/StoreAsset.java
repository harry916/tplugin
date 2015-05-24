package com.harry.tplugin.trade;

import java.util.ArrayList;
import java.util.List;

import com.harry.tplugin.bean.Product;
import com.harry.tplugin.bean.Stock;
import com.harry.tplugin.bean.StockSumView;
import com.harry.tplugin.service.ProductService;
import com.harry.tplugin.service.StockService;
import com.harry.tplugin.trade.OrderAsset.OrderItem;
import com.harry.tplugin.util.ServiceFactoryBean;

public class StoreAsset {
    
    public StoreAsset() {
        super();
    }

    public class StoreDetail
    {
        private String proId;
        private String storeId;
        private String proName;
        private String proType;
        private String unstandard;
        private int num;
        public StoreDetail() {
        }
        public String getProId() {
            return proId;
        }
        public void setProId(String proId) {
            this.proId = proId;
        }
        public String getStoreId() {
            return storeId;
        }
        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }
        public String getProName() {
            return proName;
        }
        public void setProName(String proName) {
            this.proName = proName;
        }
        public String getProType() {
            return proType;
        }
        public void setProType(String proType) {
            this.proType = proType;
        }
        public String getUnstandard() {
            return unstandard;
        }
        public void setUnstandard(String unstandard) {
            this.unstandard = unstandard;
        }
        public int getNum() {
            return num;
        }
        public void setNum(int num) {
            this.num = num;
        }
        @Override
        public String toString() {
            return "StoreDetail [proId=" + proId + ", storeId=" + storeId
                    + ", proName=" + proName + ", proType=" + proType
                    + ", unstandard=" + unstandard + ", num=" + num + "]";
        }
        
    }
    public List<StoreDetail> getStoreDetailList(List<OrderItem> orderList)
    {
        List<StoreDetail> storeDetails = new ArrayList<StoreDetail>();
        for (int i = 0; i < orderList.size(); i++)
        {
            String proId = orderList.get(i).getProId();
            int num = (int) orderList.get(i).getNum();
            StockService daoStockService = ServiceFactoryBean.getStockService();
            StockSumView stockSum = daoStockService.findStockSumViewByProId(proId);
            if (num > stockSum.getId().getNum())
            {
                continue;
            }
            else
            {
                List<Stock> stockList = daoStockService.findAllStockByProId(proId); // not use View in case of  add other storeId in the future
                if (null == stockList)
                    continue;
                ProductService pService = ServiceFactoryBean.getProductService();
                StoreDetail stDetail = new StoreDetail();
                int sz = stockList.size();
                for (int j = 0; j < sz; j++)
                {
                    Stock tStock = stockList.get(j);
                    stDetail.setProId(proId);
                    stDetail.setStoreId(tStock.getStoreId());
                    stDetail.setNum(tStock.getNum());
                    Product tProduct = pService.getProductByProId(proId);
                    stDetail.setProName(tProduct.getProName());
                    stDetail.setProType(tProduct.getProType());
                    stDetail.setUnstandard(tProduct.getUnstandard());
                    storeDetails.add(stDetail);
                }
            }
        }
        
        return storeDetails;
    }
}
