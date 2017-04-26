package org.gr.woc.biz;

import java.util.List;

import org.gr.woc.po.Order;
import org.gr.woc.po.User;

public interface IOrderBiz {
	public abstract List<Order> searchUnfinshedInfByUserId(final User user);
	public abstract List<Order> searchFinshedInfByUserId(final User user);
	public abstract List<Order> searchAllOrdersByUserId(final User user);
	public abstract Order searchInfById(final Order order);
	public abstract boolean cancelOrder(final Order order);
	public abstract boolean applyOrder(final Order order);
	public abstract boolean completeOrder(final Order order);
	public abstract List<Order> searchAllSellerOrdersByUserId(final User user);
}
