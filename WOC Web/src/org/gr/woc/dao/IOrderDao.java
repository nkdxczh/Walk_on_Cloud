package org.gr.woc.dao;

import java.sql.ResultSet;
import java.util.List;

import org.gr.woc.po.Order;
import org.gr.woc.po.User;

public interface IOrderDao {
	 public abstract int update(final Order order);
	 public abstract int delete(final Order order);
	 public abstract int insert(final Order order);
	 public abstract int completeOrder(final Order order);
	 public abstract List<Order> selectAll(final User user);
	 public abstract ResultSet infSearchById(final Order order);
	 public abstract List<Order> unfinshedInfSearchByUserId(User user);
	 public abstract List<Order> finshedInfSearchByUserId(User user);
	 public abstract boolean selectBycomid(final Order order);//增加订单的时候查询是否有重复订单
	 public abstract List<Order> selectAllSeller(final User user);
	 
}
