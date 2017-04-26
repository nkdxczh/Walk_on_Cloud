package org.gr.woc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.gr.woc.dao.ICommodityDao;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.DBUtils;
import org.gr.woc.po.Commodity;

public class CommodityDaoImpl implements ICommodityDao {
	private ConnectionManager connectionManager;
	private Connection connection;
	private DBUtils dbUtils;

	public CommodityDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.connectionManager = new ConnectionManager();
		this.dbUtils = new DBUtils();
		this.connection = null;
	}

	@Override
	public int insert(Commodity commodity) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：开启一个事务
		// TransactionManager.conn = this.connection;
		// TransactionManager.beginTransaction();

		// 步骤3：拆分对向属性
		int userId = commodity.getUserId();
		String comName = commodity.getComName();
		int property = commodity.getProperty();
		int type = commodity.getType();
		double price = commodity.getPrice();
		String comRegion = commodity.getComRegion();
		String describe = commodity.getDescribe();
		int status = commodity.getStatus();
		Date offTime = commodity.getOffTime();
		int requiredScore = commodity.getRequiredScore();

		// 步骤4：设置添加SQL语句模板
		String strSQL = "insert into commodity values(null,?,?,?,?,?,?,?,?,?,?,now())";
		Object[] params = new Object[] { userId, comName, property, type,
				price, comRegion, describe, status, offTime, requiredScore};
		// 步骤5：使用dbutils方法实现添加操作
		int affectedRows=this.dbUtils.execOthers(connection, strSQL, params);
		// 步骤6：提交事务
		// if (affectedRows > 0) {
		// 提交事务
		// TransactionManager.commit();
		// } else {
		// 回滚事务
		// TransactionManager.rollback();
		// }
		return affectedRows;
	}

	@Override
	public int deleteById(int comId) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：开启事务
		// TransactionManager.connection = this.connection;
		// TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "delete from picture where comId=?";
		Object[] params = new Object[] { comId };
		int affectedRwos = this.dbUtils.execOthers(connection, strSQL, params);
		
	    strSQL = "delete from attention where comId=?";
		params = new Object[] { comId };
		// 步骤4：调用dbutils中的方法完成对数据库的删除操作
		affectedRwos = this.dbUtils.execOthers(connection, strSQL, params);

	    strSQL = "delete from desire where comId=?";
		params = new Object[] { comId };
		// 步骤4：调用dbutils中的方法完成对数据库的删除操作
		affectedRwos = this.dbUtils.execOthers(connection, strSQL, params);
		
	    strSQL = "delete from order where comId=?";
		params = new Object[] { comId };
		// 步骤4：调用dbutils中的方法完成对数据库的删除操作
		affectedRwos = this.dbUtils.execOthers(connection, strSQL, params);
		
		  strSQL = "delete from cComment where comId=?";
			params = new Object[] { comId };
			// 步骤4：调用dbutils中的方法完成对数据库的删除操作
			affectedRwos = this.dbUtils.execOthers(connection, strSQL, params);

	    strSQL = "delete from commodity where comId=?";
		params = new Object[] { comId };
		// 步骤4：调用dbutils中的方法完成对数据库的删除操作
		affectedRwos = this.dbUtils.execOthers(connection, strSQL, params);
		// 步骤5：根据步骤4的操作结果提交或回滚事务
		// if (affectedRwos > 0) {
		// TransactionManager.commit(); // 事务提交
		// } else {
		// TransactionManager.rollback(); // 事务的回滚
		// }
		// 步骤6：返回影响行数
		return affectedRwos;
	}

	@Override
	public int update(Commodity commodity) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：开启事务
		// TransactionManager.connection = this.connection;
		// TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "update commodity set userId=?, comName=?, property=?, type=? ,price=?, comRegion=?, describe=?, status=?, offTime=?,requiredScore=?,releaseTime=? where comId=?";
		Object[] params = new Object[] { commodity.getUserId(),
				commodity.getComName(), commodity.getProperty(),
				commodity.getType(), commodity.getPrice(),
				commodity.getComRegion(), commodity.getDescribe(),
				commodity.getStatus(), commodity.getOffTime(),
				commodity.getRequiredScore(), commodity.getReleaseTime(),
				commodity.getComId() };
		// 步骤4：调用dbutils中的方法完成对数据库的删除操作
		int affectedRwos = this.dbUtils.execOthers(connection, strSQL, params);
		// 步骤5：根据步骤4的操作结果提交或回滚事务
		// if (affectedRwos > 0) {
		// TransactionManager.commit(); // 事务提交
		// } else {
		// TransactionManager.rollback(); // 事务的回滚
		// }
		// 步骤6：返回影响行数
		return affectedRwos;
	}

	@Override
	public List<Commodity> selectAll() {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<Commodity> lstCommodity = new ArrayList<Commodity>();
		// 步骤2：获取一个数据库的连接对象
		this.connection = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from commodity order by comId";
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils.execQuery(connection, strSQL,
				new Object[] {});
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while (resultSet.next()) {
				// 步骤5-1：创建一个对象
				Commodity commodity = new Commodity();
				commodity.setComId(resultSet.getInt(1));
				commodity.setUserId(resultSet.getInt(2));
				commodity.setComName(resultSet.getString(3));
				commodity.setProperty(resultSet.getInt(4));
				commodity.setType(resultSet.getInt(5));
				commodity.setPrice(resultSet.getDouble(6));
				commodity.setComRegion(resultSet.getString(7));
				commodity.setDescribe(resultSet.getString(8));
				commodity.setStatus(resultSet.getInt(9));
				commodity.setOffTime(resultSet.getDate(10));
				commodity.setRequiredScore(resultSet.getInt(11));
				commodity.setReleaseTime(resultSet.getDate(12));

				// 步骤5-2：将封装好的对象添加到List集合中
				lstCommodity.add(commodity);
			}
			// 返回结果
			return lstCommodity;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			// 步骤6：关闭数据库连接
			this.connectionManager.closeConnection(connection);
		}
	}

	@Override
	public Commodity selectById(int comId) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strSQL = "select * from commodity where comId=?";
		Object[] params = new Object[] { comId };
		// 步骤4：调用dbutils中的方法完成对数据库的查询操作
		ResultSet resultSet = this.dbUtils
				.execQuery(connection, strSQL, params);
		// 步骤5：获取结果集合并封装成一个对象
		try {
			if (resultSet.next()) {
				// 步骤6：创建一个Customers对象
				Commodity commodity = new Commodity();
				commodity.setComId(resultSet.getInt(1));
				commodity.setUserId(resultSet.getInt(2));
				commodity.setComName(resultSet.getString(3));
				commodity.setProperty(resultSet.getInt(4));
				commodity.setType(resultSet.getInt(5));
				commodity.setPrice(resultSet.getDouble(6));
				commodity.setComRegion(resultSet.getString(7));
				commodity.setDescribe(resultSet.getString(8));
				commodity.setStatus(resultSet.getInt(9));
				commodity.setOffTime(resultSet.getDate(10));
				commodity.setRequiredScore(resultSet.getInt(11));
			    commodity.setReleaseTime(resultSet.getDate(12));

				// 步骤7：返回对象
				return commodity;
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			this.connectionManager.closeConnection(connection);
		}

	}

}
