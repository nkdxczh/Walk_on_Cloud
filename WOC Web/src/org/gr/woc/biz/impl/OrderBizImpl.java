package org.gr.woc.biz.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.gr.woc.biz.IOrderBiz;
import org.gr.woc.dao.IOrderDao;
import org.gr.woc.dao.impl.OrderDaoImpl;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Order;
import org.gr.woc.po.User;

public class OrderBizImpl implements IOrderBiz {

	private IOrderDao orderDao=null;
	
	public OrderBizImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Order> searchUnfinshedInfByUserId(User user) {
		// TODO Auto-generated method stub
		orderDao=new OrderDaoImpl(TransactionManager.connection);
		return orderDao.unfinshedInfSearchByUserId(user);
	}

	@Override
	public List<Order> searchFinshedInfByUserId(User user) {
		// TODO Auto-generated method stub
		orderDao=new OrderDaoImpl(TransactionManager.connection);
		return orderDao.finshedInfSearchByUserId(user);
		
	}

	@Override
	public List<Order> searchAllOrdersByUserId(User user) {
		// TODO Auto-generated method stub
		orderDao=new OrderDaoImpl(TransactionManager.connection);
		return orderDao.selectAll(user);
		
	}

	@Override
	public Order searchInfById(Order order) {
		// TODO Auto-generated method stub
		orderDao=new OrderDaoImpl(TransactionManager.connection);
		ResultSet resultSet=orderDao.infSearchById(order);
		Order order2=new Order();
		try {
			if(resultSet.next())
			{
				order2.setComId(resultSet.getInt(2));
				order2.setOrderId(resultSet.getInt(1));
				order2.setUserId(resultSet.getInt(3));
				order2.setOrdSubTime(resultSet.getDate(4));
				order2.setOrdSucTime(resultSet.getDate(5));
				order2.setBuyerUserName(resultSet.getString(6));
				order2.setComName(resultSet.getString(7));
				order2.setSellerUserName(resultSet.getString(8));
				order2.setPrice(resultSet.getDouble(9));
				order2.setComType(resultSet.getInt(10));
				order2.setComRegion(resultSet.getString(11));
				order2.setDescribe(resultSet.getString(12));
				order2.setStatus(resultSet.getInt(13));
				order2.setOffTime(resultSet.getDate(14));
				order2.setRequrScore(resultSet.getInt(15));
				order2.setReleaseTime(resultSet.getDate(16));
				order2.setOrderName(resultSet.getString(17));
				order2.setOrderLocation(resultSet.getString(18));
				order2.setOrderEmail(resultSet.getString(19));
				order2.setOrderPhone(resultSet.getString(20));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order2;
		
	}

	@Override
	public boolean cancelOrder(Order order) {
		// TODO Auto-generated method stub
		orderDao=new OrderDaoImpl(TransactionManager.connection);
		if(orderDao.delete(order)>0)
			return true;
		else
			return false;
		
	}

	@Override
	public boolean applyOrder(Order order) {
		// TODO Auto-generated method stub
		orderDao=new OrderDaoImpl(TransactionManager.connection);
		if(orderDao.selectBycomid(order))
			return false;
		else
		{
			if(orderDao.insert(order)>0)
				return true;
			else
				return false;
		}
		
	}

	@Override
	public boolean completeOrder(Order order) {
		// TODO Auto-generated method stub
		orderDao=new OrderDaoImpl(TransactionManager.connection);
		if(orderDao.completeOrder(order)>0)
			return true;
		else
			return false;
	}

	@Override
	public List<Order> searchAllSellerOrdersByUserId(User user) {
		// TODO Auto-generated method stub
		orderDao=new OrderDaoImpl(TransactionManager.connection);
		
		return orderDao.selectAllSeller(user);
	}

}
