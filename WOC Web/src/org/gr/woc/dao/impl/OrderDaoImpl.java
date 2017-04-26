package org.gr.woc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.gr.woc.dao.IOrderDao;
import org.gr.woc.db.DBUtils;
import org.gr.woc.po.Order;
import org.gr.woc.po.User;

public class OrderDaoImpl implements IOrderDao {

	private Connection conn = null;
	private DBUtils dbUtils = null;
	
	public OrderDaoImpl(Connection conn) {
		super();
		this.dbUtils = new DBUtils();
		this.conn = conn;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public int update(Order order) {
		// TODO Auto-generated method stub
		int userid=order.getUserId();
		int comid=order.getComId();
		int orderid=order.getOrderId();
		String strSQL = "update `Order` set comid=?,userid=? where orderid=?";
		return this.dbUtils.execOthers(this.conn, strSQL, new Object[]{comid,userid,orderid});
	}

	@Override
	public int delete(Order order) {
		// TODO Auto-generated method stub
		int orderid=order.getOrderId();
		String sql="delete from `Order` where orderid=?";
		return dbUtils.execOthers(conn, sql, new Object[]{orderid});
	}

	@Override
	public int insert(Order order) {
		// TODO Auto-generated method stub
		int comid=order.getComId();
		String ordername=order.getOrderName();
		String orderlocation=order.getOrderLocation();
		String orderemail=order.getOrderEmail();
		String orderphone=order.getOrderPhone();
		int userid=order.getUserId();
		String strSQL = "insert into `Order` values(null,?,?,now(),null,?,?,?,?)";
		return this.dbUtils.execOthers(this.conn, strSQL, new Object[]{comid,userid,ordername,orderlocation,orderemail,orderphone});
	}

	@Override
	public List<Order> selectAll(User user) {
		// TODO Auto-generated method stub
		int userid=user.getUserId();
		String strSQL="SELECT distinct  o.orderid,o.comid,o.userid,o.ordsubtime,o.ordsuctime,u.nickname,c.comname,u2.nickname, c.price ,c.type ,c.comregion,c.describe ,c.status,c.offtime,c.requiredscore,c.releasetime, o.name  ,o.location,o.email,o.phone  FROM `order` o  left join detail_inf u  on u.userid=o.userid left join commodity c on c.comid=o.comid left join detail_inf u2 on c.userid= u2.userid  where o.userid=? order by o.ordsubtime desc";
		ResultSet resultSet= dbUtils.execQuery(conn, strSQL, new Object[]{userid});
		List<Order> list=new ArrayList<Order>();
		try {
			while(resultSet.next())
			{
				Order order=new Order();

				order.setComId(resultSet.getInt(2));
				order.setOrderId(resultSet.getInt(1));
				order.setUserId(resultSet.getInt(3));
				order.setOrdSubTime(resultSet.getDate(4));
				order.setOrdSucTime(resultSet.getDate(5));
				order.setBuyerUserName(resultSet.getString(6));
				order.setComName(resultSet.getString(7));
				order.setSellerUserName(resultSet.getString(8));
				order.setPrice(resultSet.getDouble(9));
				order.setComType(resultSet.getInt(10));
				order.setComRegion(resultSet.getString(11));
				order.setDescribe(resultSet.getString(12));
				order.setStatus(resultSet.getInt(13));
				order.setOffTime(resultSet.getDate(14));
				order.setRequrScore(resultSet.getInt(15));
				order.setReleaseTime(resultSet.getDate(16));
				order.setOrderName(resultSet.getString(17));
				order.setOrderLocation(resultSet.getString(18));
				order.setOrderEmail(resultSet.getString(19));
				order.setOrderPhone(resultSet.getString(20));
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public ResultSet infSearchById(Order order) {
		// TODO Auto-generated method stub
		int orderid=order.getOrderId();
		String strSQL="SELECT o.orderid,o.comid,o.userid,o.ordsubtime,o.ordsuctime,u.nickname,c.comname,u2.nickname, c.price ,c.type ,c.comregion,c.describe ,c.status,c.offtime,c.requiredscore,c.releasetime       , o.name  ,o.location,o.email,o.phone  FROM `order` o   left join detail_inf u  on u.userid=o.userid left join commodity c on c.comid=o.comid left join detail_inf u2 on c.userid= u2.userid  where o.orderid=? ";
		return dbUtils.execQuery(conn, strSQL, new Object[]{orderid});
		
	}

	@Override
	public List<Order> unfinshedInfSearchByUserId(User user) {
		// TODO Auto-generated method stub
		int userid=user.getUserId();
		String strSQL="SELECT o.orderid,o.comid,o.userid,o.ordsubtime,o.ordsuctime,u.nickname,c.comname,u2.nickname, c.price ,c.type ,c.comregion,c.describe ,c.status,c.offtime,c.requiredscore,c.releasetime   , o.name  ,o.location,o.email,o.phone  FROM `order` o left join detail_inf u  on u.userid=o.userid left join commodity c on c.comid=o.comid left join detail_inf u2 on c.userid= u2.userid  where o.userid=? and o.ordsuctime is  null";
		ResultSet resultSet= dbUtils.execQuery(conn, strSQL, new Object[]{userid});
		List<Order> list=new ArrayList<Order>();
		try {
			while(resultSet.next())
			{
				Order order=new Order();
				order.setComId(resultSet.getInt(2));
				order.setOrderId(resultSet.getInt(1));
				order.setUserId(resultSet.getInt(3));
				order.setOrdSubTime(resultSet.getDate(4));
				order.setOrdSucTime(resultSet.getDate(5));
				order.setBuyerUserName(resultSet.getString(6));
				order.setComName(resultSet.getString(7));
				order.setSellerUserName(resultSet.getString(8));
				order.setPrice(resultSet.getDouble(9));
				order.setComType(resultSet.getInt(10));
				order.setComRegion(resultSet.getString(11));
				order.setDescribe(resultSet.getString(12));
				order.setStatus(resultSet.getInt(13));
				order.setOffTime(resultSet.getDate(14));
				order.setRequrScore(resultSet.getInt(15));
				order.setReleaseTime(resultSet.getDate(16));
				order.setOrderName(resultSet.getString(17));
				order.setOrderLocation(resultSet.getString(18));
				order.setOrderEmail(resultSet.getString(19));
				order.setOrderPhone(resultSet.getString(20));
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<Order> finshedInfSearchByUserId(User user) {
		// TODO Auto-generated method stub
		System.out.println(user.getUserId());
		int userid=user.getUserId();
		String strSQL="SELECT o.orderid,o.comid,o.userid,o.ordsubtime,o.ordsuctime,u.nickname,c.comname,u2.nickname, c.price ,c.type ,c.comregion,c.describe ,c.status,c.offtime,c.requiredscore,c.releasetime    , o.name  ,o.location,o.email,o.phone  FROM `order` o  left join detail_inf u  on u.userid=o.userid left join commodity c on c.comid=o.comid left join detail_inf u2 on c.userid= u2.userid  where o.userid=? and o.ordsuctime is not null";
		ResultSet resultSet= dbUtils.execQuery(conn, strSQL, new Object[]{userid});
		List<Order> list=new ArrayList<Order>();
		try {
			while(resultSet.next())
			{
				Order order=new Order();
				order.setComId(resultSet.getInt(2));
				order.setOrderId(resultSet.getInt(1));
				order.setUserId(resultSet.getInt(3));
				order.setOrdSubTime(resultSet.getDate(4));
				order.setOrdSucTime(resultSet.getDate(5));
				order.setBuyerUserName(resultSet.getString(6));
				order.setComName(resultSet.getString(7));
				order.setSellerUserName(resultSet.getString(8));
				order.setPrice(resultSet.getDouble(9));
				order.setComType(resultSet.getInt(10));
				order.setComRegion(resultSet.getString(11));
				order.setDescribe(resultSet.getString(12));
				order.setStatus(resultSet.getInt(13));
				order.setOffTime(resultSet.getDate(14));
				order.setRequrScore(resultSet.getInt(15));
				order.setReleaseTime(resultSet.getDate(16));
				order.setOrderName(resultSet.getString(17));
				order.setOrderLocation(resultSet.getString(18));
				order.setOrderEmail(resultSet.getString(19));
				order.setOrderPhone(resultSet.getString(20));
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int completeOrder(Order order) {
		// TODO Auto-generated method stub
		int orderid=order.getOrderId();
		String strSQL = "update `Order` set ordsuctime=now() where orderid=?";
		return this.dbUtils.execOthers(this.conn, strSQL, new Object[]{orderid});
	}

	@Override
	public boolean selectBycomid(Order order)  {
		// TODO Auto-generated method stu
		int userid=order.getUserId();
		int comid=order.getComId();
		String strSQL = "SELECT * FROM `order` where userid=? and comid=?";
		ResultSet resultSet=this.dbUtils.execQuery(this.conn, strSQL, new Object[]{userid,comid});
			try {
				if(resultSet.next())
				{
					return true;
					
				}
				else
					return false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return true;
			}
		
		
	}

	@Override
	public List<Order> selectAllSeller(User user) {
		// TODO Auto-generated method stub
		int userid=user.getUserId();
		String strSQL="SELECT o.orderid,o.comid,o.userid,o.ordsubtime,o.ordsuctime,u.nickname,c.comname,u2.nickname, c.price ,c.type ,c.comregion,c.describe ,c.status,c.offtime,c.requiredscore,c.releasetime      , o.name  ,o.location,o.email,o.phone  FROM `order` o  left join detail_inf u  on u.userid=o.userid left join commodity c on c.comid=o.comid left join detail_inf u2 on c.userid= u2.userid  where u2.userid=?";
		ResultSet resultSet= dbUtils.execQuery(conn, strSQL, new Object[]{userid});
		List<Order> list=new ArrayList<Order>();
		try {
			while(resultSet.next())
			{
				Order order=new Order();
				order.setComId(resultSet.getInt(2));
				order.setOrderId(resultSet.getInt(1));
				order.setUserId(resultSet.getInt(3));
				order.setOrdSubTime(resultSet.getDate(4));
				order.setOrdSucTime(resultSet.getDate(5));
				order.setBuyerUserName(resultSet.getString(6));
				order.setComName(resultSet.getString(7));
				order.setSellerUserName(resultSet.getString(8));
				order.setPrice(resultSet.getDouble(9));
				order.setComType(resultSet.getInt(10));
				order.setComRegion(resultSet.getString(11));
				order.setDescribe(resultSet.getString(12));
				order.setStatus(resultSet.getInt(13));
				order.setOffTime(resultSet.getDate(14));
				order.setRequrScore(resultSet.getInt(15));
				order.setReleaseTime(resultSet.getDate(16));
				order.setOrderName(resultSet.getString(17));
				order.setOrderLocation(resultSet.getString(18));
				order.setOrderEmail(resultSet.getString(19));
				order.setOrderPhone(resultSet.getString(20));
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
