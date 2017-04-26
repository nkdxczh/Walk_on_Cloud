package org.gr.woc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.gr.woc.dao.IAttentionDao;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.DBUtils;
import org.gr.woc.po.Attention;
import org.gr.woc.vo.Commodities;

public class AttentionDaoImpl implements IAttentionDao {

	private ConnectionManager connectionManager;
	private Connection connection;
	private DBUtils dbUtils;

	public AttentionDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.connectionManager = new ConnectionManager();
		this.dbUtils = new DBUtils();
		this.connection = null;
	}

	@Override
	public int insert(Attention attention) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：开启一个事务
		// TransactionManager.conn = this.connection;
		// TransactionManager.beginTransaction();

		// 步骤3：拆分对向属性
		int comId = attention.getComId();
		int userId = attention.getUserId();
		// 步骤4：设置添加SQL语句模板
		String strSQL = "insert into attention values(null,?,?)";
		Object[] params = new Object[] { comId, userId };
		// 步骤5：使用dbutils方法实现添加操作
		int affectedRows = this.dbUtils.execOthers(connection, strSQL, params);
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
	public int deleteById(int attId) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：开启事务
		// TransactionManager.connection = this.connection;
		// TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "delete from attention where attId=?";
		Object[] params = new Object[] { attId };
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
	public int update(Attention attention) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：开启事务
		// TransactionManager.connection = this.connection;
		// TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "update attention set comId=?, userId=? where attId=?";
		Object[] params = new Object[] { attention.getComId(),
				attention.getUserId(), attention.getAttId() };
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
	public List<Attention> selectAll() {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<Attention> lstAttention = new ArrayList<Attention>();
		// 步骤2：获取一个数据库的连接对象
		this.connection = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from attention order by attId";
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils.execQuery(connection, strSQL,
				new Object[] {});
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while (resultSet.next()) {
				// 步骤5-1：创建一个对象
				Attention attention = new Attention();
				attention.setAttId(resultSet.getInt(1));
				attention.setComId(resultSet.getInt(2));
				attention.setUserId(resultSet.getInt(3));
				// 步骤5-2：将封装好的对象添加到List集合中
				lstAttention.add(attention);
			}
			// 返回结果
			return lstAttention;
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
	public Attention selectById(int attId) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strSQL = "select * from attention where attId=?";
		Object[] params = new Object[] { attId };
		// 步骤4：调用dbutils中的方法完成对数据库的查询操作
		ResultSet resultSet = this.dbUtils
				.execQuery(connection, strSQL, params);
		// 步骤5：获取结果集合并封装成一个对象
		try {
			if (resultSet.next()) {
				// 步骤6：创建一个Customers对象
				Attention attention = new Attention();
				attention.setAttId(resultSet.getInt(1));
				attention.setComId(resultSet.getInt(2));
				attention.setUserId(resultSet.getInt(3));
				// 步骤7：返回对象
				return attention;
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

	@Override
	public List<Commodities> selectByUserId(int userId) {
		// TODO Auto-generated method stub
		this.connection = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strSQL = "select * from commoditiesInf where comId in (select comId from attention where userId=?)";
		Object[] params = new Object[] { userId };
		// 步骤4：调用dbutils中的方法完成对数据库的查询操作
		ResultSet resultSet = this.dbUtils
				.execQuery(connection, strSQL, params);
		// 步骤5：获取结果集合并封装成一个对象
		List<Commodities> lstCommodities = new ArrayList<Commodities>();
		try {
			while (resultSet.next()) {
				// 步骤6：创建一个Customers对象
				Commodities commodities = new Commodities();
				commodities.setComId(resultSet.getInt(1));
				commodities.setOwnerId(resultSet.getInt(2));
				commodities.setOwnerName(resultSet.getString(3));
				commodities.setComName(resultSet.getString(4));
				commodities.setNickName(resultSet.getString(5));
				commodities.setProperty(resultSet.getInt(6));
				commodities.setComTypeName(resultSet.getString(7));
				commodities.setPrice(resultSet.getDouble(8));
				commodities.setComRegion(resultSet.getString(9));
				commodities.setDescribe(resultSet.getString(10));
				commodities.setStatus(resultSet.getInt(11));
				commodities.setReleaseTime(resultSet.getDate(12));
				commodities.setOffTime(resultSet.getDate(13));
				commodities.setRequiredScore(resultSet.getInt(14));
				commodities.setPicturePath(resultSet.getString(15));
				commodities.setDesire(resultSet.getString(16));
				commodities.setFocusNumber(resultSet.getInt(17));
				lstCommodities.add(commodities);
			}
			return lstCommodities;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			this.connectionManager.closeConnection(connection);
		}

	}

	@Override
	public int deleteByIds(int userId, int comId) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：开启事务
		// TransactionManager.connection = this.connection;
		// TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "delete from attention where userId=? and comid=?";
		Object[] params = new Object[] { userId, comId };
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
	public List<Attention> selectByIds(int userId, int comId) {
		// TODO Auto-generated method stub
		this.connection = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strSQL = "select * from attention where userId=? and comId=?";
		Object[] params = new Object[] { userId,comId };
		// 步骤4：调用dbutils中的方法完成对数据库的查询操作
		ResultSet resultSet = this.dbUtils
				.execQuery(connection, strSQL, params);
		// 步骤5：获取结果集合并封装成一个对象
		List<Attention> lstAttention = new ArrayList<Attention>();
		try {
			while (resultSet.next()) {
				// 步骤6：创建一个Customers对象
				Attention attention = new Attention();
				attention.setAttId(resultSet.getInt(1));
				attention.setComId(resultSet.getInt(2));
				attention.setUserId(resultSet.getInt(3));
				lstAttention.add(attention);
			}
			return lstAttention;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			this.connectionManager.closeConnection(connection);
		}

	}

}
