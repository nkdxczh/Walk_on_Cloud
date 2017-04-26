package org.gr.woc.biz.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.gr.woc.biz.ICommodityBiz;
import org.gr.woc.dao.IAttentionDao;
import org.gr.woc.dao.ICCommentDao;
import org.gr.woc.dao.ICCommentsDao;
import org.gr.woc.dao.ICommoditiesDao;
import org.gr.woc.dao.ICommodityDao;
import org.gr.woc.dao.impl.AttentionDaoImpl;
import org.gr.woc.dao.impl.CCommentDaoImpl;
import org.gr.woc.dao.impl.CCommentsDaoImpl;
import org.gr.woc.dao.impl.CommoditiesDaoImpl;
import org.gr.woc.dao.impl.CommodityDaoImpl;
import org.gr.woc.po.Attention;
import org.gr.woc.po.CComment;
import org.gr.woc.po.Commodity;
import org.gr.woc.vo.CComments;
import org.gr.woc.vo.Commodities;
import org.gr.woc.vo.sortmethods.SortByFocus;
import org.gr.woc.vo.sortmethods.SortByPrice;
import org.gr.woc.vo.sortmethods.SortByTime;

public class CommodityBizImpl implements ICommodityBiz {

	private ICommoditiesDao commoditiesDao;
	private ICommodityDao commodityDao;
	private IAttentionDao atttentionDao;
	private ICCommentDao cCommentDao;
	private ICCommentsDao cCommentsDao;

	public CommodityBizImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.commoditiesDao = new CommoditiesDaoImpl();
		this.atttentionDao = new AttentionDaoImpl();
		this.commodityDao = new CommodityDaoImpl();
		this.cCommentDao = new CCommentDaoImpl();
		this.cCommentsDao = new CCommentsDaoImpl();
	}

	@Override
	public Commodities searchByComId(int comId) {
		// TODO Auto-generated method stub
		return commoditiesDao.selectByComId(comId);
	}

	@Override
	public List<Commodities> searchByUserId(int userId, int key,final int index,final int pageSize) {
		// TODO Auto-generated method stub
		List<Commodities> lstCommodities = commoditiesDao
				.selectByUserId(userId,index,pageSize);
		List<Commodities> lstResCommodities = new ArrayList<Commodities>();
		switch (key) {
		case 0:
			for (Commodities commodities : lstCommodities) {
				if (commodities.getOffTime() == null)
					lstResCommodities.add(commodities);
			}
			break;
		case 1:
			for (Commodities commodities : lstCommodities) {
				if (commodities.getOffTime() != null)
					lstResCommodities.add(commodities);
			}

		default:
			break;
		}
		return lstResCommodities;
	}

	@Override
	public List<Commodities> searchByComType(String comType, int key,final int index,final int pageSize,String order) {
		// TODO Auto-generated method stub
		List<Commodities> lstCommodities = commoditiesDao
				.selectByComType(comType,index,pageSize,order);
		List<Commodities> lstResCommodities = new ArrayList<Commodities>();
		switch (key) {
		case 0:
			for (Commodities commodities : lstCommodities) {
				if (commodities.getOffTime() == null)
					lstResCommodities.add(commodities);
			}
			break;
		case 1:
			for (Commodities commodities : lstCommodities) {
				if (commodities.getOffTime() != null)
					lstResCommodities.add(commodities);
			}

		default:
			break;
		}
		return lstResCommodities;
	}

	@Override
	public List<Commodities> searchByComName(String comName, int key,final int index,final int pageSize,String order) {
		// TODO Auto-generated method stub
		List<Commodities> lstCommodities = commoditiesDao
				.selectByComName(comName,index,pageSize,order);
		List<Commodities> lstResCommodities = new ArrayList<Commodities>();
		switch (key) {
		case 0:
			for (Commodities commodities : lstCommodities) {
				if (commodities.getOffTime() == null)
					lstResCommodities.add(commodities);
			}
			break;
		case 1:
			for (Commodities commodities : lstCommodities) {
				if (commodities.getOffTime() != null)
					lstResCommodities.add(commodities);
			}

		default:
			break;
		}
		return lstResCommodities;
	}

	@Override
	public List<Commodities> searchByUserName(String ownerName, int key,final int index,final int pageSize,String order) {
		// TODO Auto-generated method stub
		List<Commodities> lstCommodities = commoditiesDao
				.selectByOwnerName(ownerName,index,pageSize,order);
		List<Commodities> lstResCommodities = new ArrayList<Commodities>();
		switch (key) {
		case 0:
			for (Commodities commodities : lstCommodities) {
				if (commodities.getOffTime() == null)
					lstResCommodities.add(commodities);
			}
			break;
		case 1:
			for (Commodities commodities : lstCommodities) {
				if (commodities.getOffTime() != null)
					lstResCommodities.add(commodities);
			}

		default:
			break;
		}
		return lstResCommodities;
	}

	@Override
	public List<Commodities> searchByComRegion(String comRegion, int key,final int index,final int pageSize,String order) {
		// TODO Auto-generated method stub
		List<Commodities> lstCommodities = commoditiesDao
				.selectByComRegion(comRegion,index,pageSize,order);
		List<Commodities> lstResCommodities = new ArrayList<Commodities>();
		switch (key) {
		case 0:
			for (Commodities commodities : lstCommodities) {
				if (commodities.getOffTime() == null)
					lstResCommodities.add(commodities);
			}
			break;
		case 1:
			for (Commodities commodities : lstCommodities) {
				if (commodities.getOffTime() != null)
					lstResCommodities.add(commodities);
			}

		default:
			break;
		}
		return lstResCommodities;
	}

	@Override
	public boolean releaseCommodity(Commodity commodity) {
		// TODO Auto-generated method stub
		return commodityDao.insert(commodity) > 0 ? true : false;
	}

	@Override
	public boolean payAttention(int userId, Commodity commodity) {
		// TODO Auto-generated method stub
		Attention attention = new Attention();
		attention.setUserId(userId);
		attention.setComId(commodity.getComId());
		return atttentionDao.insert(attention) > 0 ? true : false;
	}

	@Override
	public boolean releaseCCment(int userId, final CComment cComment) {
		// TODO Auto-generated method stub
		cComment.setUserId(userId);
		return cCommentDao.insert(cComment) > 0 ? true : false;
	}

	@Override
	public List<CComment> searchCCmment(int comId) {
		// TODO Auto-generated method stub
		return cCommentDao.selectByComId(comId);
	}

	@Override
	public boolean cancelCCmment(int comId) {
		// TODO Auto-generated method stub
		return cCommentDao.deleteById(comId) > 0 ? true : false;
	}

	@Override
	public List<Commodities> searchHotCommodities(int key) {
		// TODO Auto-generated method stub
		List<Commodities> lstCommodities = new ArrayList<Commodities>();
		List<Commodities> lstCommoditiesRes = new ArrayList<Commodities>();
		List<Commodities> lstSearchRes = new ArrayList<Commodities>();
		int index=0;int pageSize=10;
		lstCommodities = commoditiesDao.selectCommodities(index,pageSize);
		Collections.sort(lstCommodities, new SortByFocus());
		Collections.reverse(lstCommodities);
		int comNumber = 10;
		switch (key) {
		case 0: {
			if (lstCommodities.size() < 10)
				comNumber = lstCommodities.size();
			for (int i = 0; i < comNumber; i++) {
				lstSearchRes.add(lstCommodities.get(i));
			}
			return lstSearchRes;
		}
		case 1: {

			for (Commodities commodities : lstCommodities) {
				if (commodities.getComTypeName().equals("交易书籍")
						|| commodities.getComTypeName().equals("交易用品")
						|| commodities.getComTypeName().equals("交易其它"))
					lstCommoditiesRes.add(commodities);
			}
			if (lstCommoditiesRes.size() < 10)
				comNumber = lstCommoditiesRes.size();
			for (int i = 0; i < comNumber; i++) {
				lstSearchRes.add(lstCommoditiesRes.get(i));
			}
			return lstSearchRes;
		}
		case 2: {
			for (Commodities commodities : lstCommodities) {
				if (commodities.getComTypeName().equals("交换书籍")
						|| commodities.getComTypeName().equals("交换用品")
						|| commodities.getComTypeName().equals("交换其它"))
					lstCommoditiesRes.add(commodities);
			}
			if (lstCommoditiesRes.size() < 10)
				comNumber = lstCommoditiesRes.size();
			for (int i = 0; i < comNumber; i++) {
				lstSearchRes.add(lstCommoditiesRes.get(i));
			}
			return lstSearchRes;
		}
		case 3: {
			for (Commodities commodities : lstCommodities) {
				if (commodities.getComTypeName().equals("漂流书籍")
						|| commodities.getComTypeName().equals("漂流用品")
						|| commodities.getComTypeName().equals("漂流其它"))
					lstCommoditiesRes.add(commodities);
			}
			if (lstCommoditiesRes.size() < 10)
				comNumber = lstCommoditiesRes.size();
			for (int i = 0; i < comNumber; i++) {
				lstSearchRes.add(lstCommoditiesRes.get(i));
			}
			return lstSearchRes;
		}
		default:
			return null;
		}
	}

	@Override
	public List<Commodities> searchOwnerCommodities(int userId, int key,int index,int pageSize) {
		// TODO Auto-generated method stub
		List<Commodities> lstCommodities = new ArrayList<Commodities>();
		List<Commodities> lstCommoditiesRes = new ArrayList<Commodities>();
		lstCommodities = commoditiesDao.selectByUserId(userId,index,pageSize);
		System.out.println(lstCommodities);
		switch (key) {
		case 0: {
			return lstCommodities;
		}
		case 1: {

			for (Commodities commodities : lstCommodities) {
				if (commodities.getComTypeName().equals("交易书籍")
						|| commodities.getComTypeName().equals("交易用品")
						|| commodities.getComTypeName().equals("交易其它"))
					lstCommoditiesRes.add(commodities);
			}
			return lstCommoditiesRes;
		}
		case 2: {
			for (Commodities commodities : lstCommodities) {
				if (commodities.getComTypeName().equals("交换书籍")
						|| commodities.getComTypeName().equals("交换用品")
						|| commodities.getComTypeName().equals("交换其它"))
					lstCommoditiesRes.add(commodities);
			}
			return lstCommoditiesRes;
		}
		case 3: {
			for (Commodities commodities : lstCommodities) {
				if (commodities.getComTypeName().equals("漂流书籍")
						|| commodities.getComTypeName().equals("漂流用品")
						|| commodities.getComTypeName().equals("漂流其它"))
					lstCommoditiesRes.add(commodities);
			}
			return lstCommoditiesRes;

		}
		default:
			return null;
		}
	}

	@Override
	public List<CComments> searchCCmments(int comId) {
		// TODO Auto-generated method stub
		return cCommentsDao.selectByComId(comId);
	}

	@Override
	public List<Commodities> searchByComType(int key,int index,int pageSize,String order) {
		// TODO Auto-generated method stub
		return commoditiesDao.selectCommoditiesByType(key,index,pageSize,order);
	}

	@Override
	public List<Commodities> searchByComPrice(int key,int index,int pageSize,String order) {
		// TODO Auto-generated method stub
		return commoditiesDao.selectCommoditiesByPrice(key,index,pageSize,order);
	}

	@Override
	public List<Commodities> searchByComTime(int key,int index,int pageSize,String order) {
		// TODO Auto-generated method stub
		return commoditiesDao.selectCommoditiesByTime(key,index,pageSize,order);
	}

	@Override
	public List<Commodities> searchByComFocus(int key,int index,int pageSize,String order) {
		// TODO Auto-generated method stub
		return commoditiesDao.selectCommoditiesByFocus(key,index,pageSize,order);
	}

	@Override
	public List<Commodities> searchNewestCommodities(int comType,int index,int pageSize) {
		// TODO Auto-generated method stub
		List<Commodities> lstCommodities = new ArrayList<Commodities>();
		List<Commodities> lstCommoditiesRes = new ArrayList<Commodities>();
		List<Commodities> lstSearchRes = new ArrayList<Commodities>();
		lstCommodities = commoditiesDao.selectCommodities(0,0);
		Collections.sort(lstCommodities, new SortByTime());
		Collections.reverse(lstCommodities);
		int comNumber = 10;
		switch (comType) {
		case 0: {
			if (lstCommodities.size() < 10)
				comNumber = lstCommodities.size();
			for (int i = 0; i < comNumber; i++) {
				lstSearchRes.add(lstCommodities.get(i));
			}
			return lstCommodities;
		}
		case 1: {

			for (Commodities commodities : lstCommodities) {
				if (commodities.getComTypeName().equals("交易书籍")
						|| commodities.getComTypeName().equals("交易用品")
						|| commodities.getComTypeName().equals("交易其它"))
					lstCommoditiesRes.add(commodities);
			}
			if (lstCommoditiesRes.size() < 10)
				comNumber = lstCommoditiesRes.size();
			for (int i = 0; i < comNumber; i++) {
				lstSearchRes.add(lstCommoditiesRes.get(i));
			}
			return lstSearchRes;
		}
		case 2: {
			for (Commodities commodities : lstCommodities) {
				if (commodities.getComTypeName().equals("交换书籍")
						|| commodities.getComTypeName().equals("交换用品")
						|| commodities.getComTypeName().equals("交换其它"))
					lstCommoditiesRes.add(commodities);
			}
			if (lstCommoditiesRes.size() < 10)
				comNumber = lstCommoditiesRes.size();
			for (int i = 0; i < comNumber; i++) {
				lstSearchRes.add(lstCommoditiesRes.get(i));
			}
			return lstSearchRes;
		}
		case 3: {
			for (Commodities commodities : lstCommodities) {
				if (commodities.getComTypeName().equals("漂流书籍")
						|| commodities.getComTypeName().equals("漂流用品")
						|| commodities.getComTypeName().equals("漂流其它"))
					lstCommoditiesRes.add(commodities);
			}
			if (lstCommoditiesRes.size() < 10)
				comNumber = lstCommoditiesRes.size();
			for (int i = 0; i < comNumber; i++) {
				lstSearchRes.add(lstCommoditiesRes.get(i));
			}
			return lstSearchRes;
		}
		default:
			return null;
		}
	}

	@Override
	public List<Commodities> searchCommoditiesOrderByTime(int key,int index,int pageSize) {
		// TODO Auto-generated method stub
		List<Commodities> lstCommodities = new ArrayList<Commodities>();
		List<Commodities> lstSearchRes = new ArrayList<Commodities>();
		lstCommodities = commoditiesDao.selectCommodities(index,pageSize);
		Collections.sort(lstCommodities, new SortByTime());
		Collections.reverse(lstCommodities);
		switch (key) {
		case 0: {
			return lstCommodities;
		}
		case 1: {

			for (Commodities commodities : lstCommodities) {
				if (commodities.getComTypeName().equals("交易书籍")
						|| commodities.getComTypeName().equals("交易用品")
						|| commodities.getComTypeName().equals("交易其它"))
					lstSearchRes.add(commodities);
			}
			return lstSearchRes;
		}
		case 2: {
			for (Commodities commodities : lstCommodities) {
				if (commodities.getComTypeName().equals("交换书籍")
						|| commodities.getComTypeName().equals("交换用品")
						|| commodities.getComTypeName().equals("交换其它"))
					lstSearchRes.add(commodities);
			}
			return lstSearchRes;
		}
		case 3: {
			for (Commodities commodities : lstCommodities) {
				if (commodities.getComTypeName().equals("漂流书籍")
						|| commodities.getComTypeName().equals("漂流用品")
						|| commodities.getComTypeName().equals("漂流其它"))
					lstSearchRes.add(commodities);
			}
			return lstSearchRes;
		}
		default:
			return null;
		}
	}

	@Override
	public List<Commodities> searchCommoditiesOrderByPrice(int key,int index,int pageSize) {
		// TODO Auto-generated method stub
		List<Commodities> lstCommodities = new ArrayList<Commodities>();
		List<Commodities> lstSearchRes = new ArrayList<Commodities>();
		lstCommodities = commoditiesDao.selectCommodities(index,pageSize);
		Collections.sort(lstCommodities, new SortByPrice());
		Collections.reverse(lstCommodities);
		switch (key) {
		case 0: {
			return lstCommodities;
		}
		case 1: {

			for (Commodities commodities : lstCommodities) {
				if (commodities.getComTypeName().equals("交易书籍")
						|| commodities.getComTypeName().equals("交易用品")
						|| commodities.getComTypeName().equals("交易其它"))
					lstSearchRes.add(commodities);
			}
			return lstSearchRes;
		}
		case 2: {
			for (Commodities commodities : lstCommodities) {
				if (commodities.getComTypeName().equals("交换书籍")
						|| commodities.getComTypeName().equals("交换用品")
						|| commodities.getComTypeName().equals("交换其它"))
					lstSearchRes.add(commodities);
			}
			return lstSearchRes;
		}
		case 3: {
			for (Commodities commodities : lstCommodities) {
				if (commodities.getComTypeName().equals("漂流书籍")
						|| commodities.getComTypeName().equals("漂流用品")
						|| commodities.getComTypeName().equals("漂流其它"))
					lstSearchRes.add(commodities);
			}
			return lstSearchRes;
		}
		default:
			return null;
		}
	}

	@Override
	public List<Commodities> searchCommoditiesOrderByFocus(int key,int index,int pageSize) {
		// TODO Auto-generated method stub
		List<Commodities> lstCommodities = new ArrayList<Commodities>();
		List<Commodities> lstSearchRes = new ArrayList<Commodities>();
		lstCommodities = commoditiesDao.selectCommodities(index,pageSize);
		switch (key) {
		case 0: {
			return lstCommodities;
		}
		case 1: {

			for (Commodities commodities : lstCommodities) {
				if (commodities.getComTypeName().equals("交易书籍")
						|| commodities.getComTypeName().equals("交易用品")
						|| commodities.getComTypeName().equals("交易其它"))
					lstSearchRes.add(commodities);
			}
			return lstSearchRes;
		}
		case 2: {
			for (Commodities commodities : lstCommodities) {
				if (commodities.getComTypeName().equals("交换书籍")
						|| commodities.getComTypeName().equals("交换用品")
						|| commodities.getComTypeName().equals("交换其它"))
					lstSearchRes.add(commodities);
			}
			return lstSearchRes;
		}
		case 3: {
			for (Commodities commodities : lstCommodities) {
				if (commodities.getComTypeName().equals("漂流书籍")
						|| commodities.getComTypeName().equals("漂流用品")
						|| commodities.getComTypeName().equals("漂流其它"))
					lstSearchRes.add(commodities);
			}
			return lstSearchRes;
		}
		default:
			return null;
		}
	}
}
