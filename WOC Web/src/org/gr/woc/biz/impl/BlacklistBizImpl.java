package org.gr.woc.biz.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.gr.woc.biz.IBlacklistBiz;
import org.gr.woc.dao.IBlackListDao;
import org.gr.woc.dao.impl.BlackListDaoImpl;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.BlackList;

public class BlacklistBizImpl implements IBlacklistBiz {

	private IBlackListDao blackListDao=null;
	
	public BlacklistBizImpl() {
		super();
	
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean add(BlackList blacklist) {
		// TODO Auto-generated method stub
		blackListDao=new BlackListDaoImpl(TransactionManager.connection);
		if( blackListDao.insert(blacklist)>0)
			return true;
		else {
			return false;
		}
		
	}

	@Override
	public boolean cancel(BlackList blacklist) {
		// TODO Auto-generated method stub
		blackListDao=new BlackListDaoImpl(TransactionManager.connection);
		if(blackListDao.delete(blacklist)>0)
			return true;
		else
		return false;
	}

	@Override
	public boolean update(BlackList blacklist) {
		// TODO Auto-generated method stub
		blackListDao=new BlackListDaoImpl(TransactionManager.connection);
		if(blackListDao.update(blacklist)>0)
			return true;
		else
		return false;
	}

	@Override
	public List<BlackList> searchAll() {
		// TODO Auto-generated method stub
		blackListDao=new BlackListDaoImpl(TransactionManager.connection);
		return blackListDao.findAll();
	}

}
