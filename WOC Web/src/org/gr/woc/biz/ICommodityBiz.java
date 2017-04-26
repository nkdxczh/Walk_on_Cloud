package org.gr.woc.biz;

import java.util.List;

import org.gr.woc.po.CComment;
import org.gr.woc.po.Commodity;
import org.gr.woc.vo.CComments;
import org.gr.woc.vo.Commodities;

public interface ICommodityBiz {
public abstract Commodities searchByComId(final int comId);
public abstract List<Commodities> searchByUserId(final int userId,final int key,final int index,final int pageSize);
public abstract List<Commodities> searchByUserName(final String ownerName,final int key,final int index,final int pageSize,final String order);
public abstract List<Commodities> searchByComType(final String comType,final int key,final int index,final int pageSize,final String order);
public abstract List<Commodities> searchByComName(final String comName,final int key,final int index,final int pageSize,final String order);
public abstract List<Commodities> searchByComRegion(final String region,final int key,final int index,final int pageSize,final String order);
public boolean releaseCommodity(final Commodity commodity);
public abstract boolean payAttention(final int userId,final Commodity commodity);
public abstract boolean releaseCCment(final int userId,final CComment cComment);
public abstract List<CComment> searchCCmment(final int comId);
public abstract List<CComments> searchCCmments(final int comId);
public abstract boolean cancelCCmment(final int comId);
public List<Commodities> searchHotCommodities(final int key);
public abstract List<Commodities> searchCommoditiesOrderByTime(final int key,final int index,final int pageSize);
public abstract List<Commodities> searchCommoditiesOrderByPrice(final int key,final int index,final int pageSize);
public abstract List<Commodities> searchCommoditiesOrderByFocus(final int key,final int index,final int pageSize);
public List<Commodities> searchNewestCommodities(final int comType,final int index,final int pageSize);
public List<Commodities> searchByComType(final int key,final int index,final int pageSize,final String order);
public List<Commodities> searchByComPrice(final int key,final int index,final int pageSize,final String order);
public List<Commodities> searchByComTime(final int key,final int index,final int pageSize,final String order);
public List<Commodities> searchByComFocus(final int key,final int index,final int pageSize,final String order);
public List<Commodities> searchOwnerCommodities(final int userId,final int key,final int index,final int pageSize);

// 
}
