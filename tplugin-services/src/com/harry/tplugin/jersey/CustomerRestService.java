package com.harry.tplugin.jersey;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
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
        
        
        return "";
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
