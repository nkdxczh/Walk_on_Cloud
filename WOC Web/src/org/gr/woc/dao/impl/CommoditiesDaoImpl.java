package org.gr.woc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.gr.woc.dao.ICommoditiesDao;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.DBUtils;
import org.gr.woc.vo.Commodities;

public class CommoditiesDaoImpl implements ICommoditiesDao {

	private ConnectionManager connectionManager;
	private Connection connection;
	private DBUtils dbUtils;

	public CommoditiesDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.connectionManager = new ConnectionManager();
		this.dbUtils = new DBUtils();
		this.connection = null;
	}

	@Override
	public List<Commodities> selectByComName(String comName, int index,
			int pageSize, String order) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strPreSQL = "select count(*) as commoditiesCount from commoditiesInf order by releaseTime desc";
		int commoditiesCount = 0;
		int pageCount = 0;
		ResultSet preResultSet = this.dbUtils.execQuery(connection, strPreSQL,
				new Object[] {});
		try {
			if (preResultSet.next()) {
				commoditiesCount = preResultSet.getInt(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (pageSize == 0) {
			pageSize = commoditiesCount;
			index = 1;
		} else {

		}
		if (commoditiesCount % pageSize == 0) {
			pageCount = commoditiesCount / pageSize;
		} else {
			pageCount = commoditiesCount / pageSize + 1;
		}
		String sComName = '%' + comName + '%';
		String strSQL = "select *  from commoditiesInf where comName like ? order by "
				+ order + " limit ?,?";
		Object[] params = new Object[] { sComName, (index - 1) * pageSize,
				pageSize };
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
				commodities.setNickName(resultSet.getString(4));
				commodities.setComName(resultSet.getString(5));
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
				commodities.setPageCount(pageCount);
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
	public List<Commodities> selectByComType(String comType, int index,
			int pageSize, String order) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strPreSQL = "select count(*) as commoditiesCount from commoditiesInf order by "+order+" desc";
		int commoditiesCount = 0;
		int pageCount = 0;
		ResultSet preResultSet = this.dbUtils.execQuery(connection, strPreSQL,
				new Object[] {});
		try {
			if (preResultSet.next()) {
				commoditiesCount = preResultSet.getInt(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (pageSize == 0) {
			pageSize = commoditiesCount;
			index = 1;
		} else {

		}
		if (commoditiesCount % pageSize == 0) {
			pageCount = commoditiesCount / pageSize;
		} else {
			pageCount = commoditiesCount / pageSize + 1;
		}
		String strSQL = "select * from commoditiesInf where comTypeName like ? order by "
				+ order + " limit ?,?";
		String sComType = '%' + comType + '%';
		Object[] params = new Object[] { sComType, (index - 1) * pageSize,
				pageSize };
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
				commodities.setNickName(resultSet.getString(4));
				commodities.setComName(resultSet.getString(5));
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
				commodities.setPageCount(pageCount);
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
	public List<Commodities> selectByOwnerName(String ownerName, int index,
			int pageSize, String order) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strPreSQL = "select count(*) as commoditiesCount from commoditiesInf order by releaseTime";
		int commoditiesCount = 0;
		int pageCount = 0;
		ResultSet preResultSet = this.dbUtils.execQuery(connection, strPreSQL,
				new Object[] {});
		try {
			if (preResultSet.next()) {
				commoditiesCount = preResultSet.getInt(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (pageSize == 0) {
			pageSize = commoditiesCount;
			index = 1;
		} else {

		}
		if (commoditiesCount % pageSize == 0) {
			pageCount = commoditiesCount / pageSize;
		} else {
			pageCount = commoditiesCount / pageSize + 1;
		}
		String strSQL = "select * from commoditiesInf where nickName like ? order by "
				+ order + " limit ?,?";
		String sOwnerName = '%' + ownerName + '%';
		Object[] params = new Object[] { sOwnerName,
				(index - 1) * pageSize, pageSize };
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
				commodities.setNickName(resultSet.getString(4));
				commodities.setComName(resultSet.getString(5));
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
				commodities.setPageCount(pageCount);
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
	public List<Commodities> selectByReleaseTime(int index, int pageSize) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strPreSQL = "select count(*) as commoditiesCount from commoditiesInf order by releaesTime desc";
		int commoditiesCount = 0;
		int pageCount = 0;
		ResultSet preResultSet = this.dbUtils.execQuery(connection, strPreSQL,
				new Object[] {});
		try {
			if (preResultSet.next()) {
				commoditiesCount = preResultSet.getInt(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (pageSize == 0) {
			pageSize = commoditiesCount;
			index = 1;
		} else {

		}
		if (commoditiesCount % pageSize == 0) {
			pageCount = commoditiesCount / pageSize;
		} else {
			pageCount = commoditiesCount / pageSize + 1;
		}
		String strSQL = "select * from commoditiesInf where order by releaseTime desc limit ?,?";
		Object[] params = new Object[] { (index - 1) * pageSize, pageSize };
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
				commodities.setNickName(resultSet.getString(4));
				commodities.setComName(resultSet.getString(5));
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
				commodities.setPageCount(pageCount);
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
	public Commodities selectByComId(int comId) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strSQL = "select * from commoditiesInf where comId=?";
		Object[] params = new Object[] { comId };
		// 步骤4：调用dbutils中的方法完成对数据库的查询操作
		ResultSet resultSet = this.dbUtils
				.execQuery(connection, strSQL, params);
		// 步骤5：获取结果集合并封装成一个对象
		Commodities commodities = new Commodities();
		try {
			if (resultSet.next()) {
				commodities.setComId(resultSet.getInt(1));
				commodities.setOwnerId(resultSet.getInt(2));
				commodities.setOwnerName(resultSet.getString(3));
				commodities.setNickName(resultSet.getString(4));
				commodities.setComName(resultSet.getString(5));
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
				return commodities;
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
	public List<Commodities> selectByUserId(int userId, int index, int pageSize) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strPreSQL = "select count(*) as commoditiesCount from commoditiesInf where ownerId=? order by releaseTime desc";
		int commoditiesCount = 0;
		int pageCount = 0;
		ResultSet preResultSet = this.dbUtils.execQuery(connection, strPreSQL,
				new Object[] {userId});
		try {
			if (preResultSet.next()) {
				commoditiesCount = preResultSet.getInt(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (pageSize == 0) {
			pageSize = commoditiesCount;
			if(pageSize==0)
			{
				pageSize=1;
			}
			index = 1;
		} else {

		}
		if (commoditiesCount % pageSize == 0) {
			pageCount = commoditiesCount / pageSize;
		} else {
			pageCount = commoditiesCount / pageSize + 1;
		}
		String strSQL = "select * from commoditiesInf where ownerId=? order by comId desc limit ?,?";
		Object[] params = new Object[] { userId, (index - 1) * pageSize,
				pageSize };
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
				commodities.setNickName(resultSet.getString(4));
				commodities.setComName(resultSet.getString(5));
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
				commodities.setPageCount(pageCount);
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
	public List<Commodities> selectByComRegion(String comRegion, int index,
			int pageSize, String order) {
		// TODO Auto-generated method stub
		this.connection = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strPreSQL = "select count(*) as commoditiesCount from commoditiesInf order by releaseTime";
		int commoditiesCount = 0;
		int pageCount = 0;
		ResultSet preResultSet = this.dbUtils.execQuery(connection, strPreSQL,
				new Object[] {});
		try {
			if (preResultSet.next()) {
				commoditiesCount = preResultSet.getInt(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (pageSize == 0) {
			pageSize = commoditiesCount;
			index = 1;
		} else {

		}
		if (commoditiesCount / pageSize == 0) {
			pageCount = commoditiesCount / pageSize;
		} else {
			pageCount = commoditiesCount / pageSize + 1;
		}
		String strSQL = "select * from commoditiesInf where comRegion like ? order by "
				+ order + " desc " + " limit ?,?";
		String sComRegion = '%' + comRegion + '%';
		Object[] params = new Object[] { sComRegion,
				(index - 1) * pageSize, pageSize };
		// 步骤4：调用dbutils中的方法完成对数据库的查询操作
		ResultSet resultSet = this.dbUtils
				.execQuery(connection, strSQL, params);
		// 步骤5：获取结果集合并封装成一个对象
		List<Commodities> lstCommodities = new ArrayList<Commodities>();
		try {
			while (resultSet.next()) {
				Commodities commodities = new Commodities();
				commodities.setComId(resultSet.getInt(1));
				commodities.setOwnerId(resultSet.getInt(2));
				commodities.setOwnerName(resultSet.getString(3));
				commodities.setNickName(resultSet.getString(4));
				commodities.setComName(resultSet.getString(5));
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
				commodities.setPageCount(pageCount);
				lstCommodities.add(commodities);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			this.connectionManager.closeConnection(connection);
		}
		return lstCommodities;
	}

	@Override
	public List<Commodities> selectCommodities(int index, int pageSize) {
		// TODO Auto-generated method stub
		this.connection = this.connectionManager.openConnection();
		// 步骤1：获取一个数据库连接对象
		// 步骤2：创建SQL语句模板
		String strPreSQL = "select count(*) as commoditiesCount from commoditiesInf";
		int commoditiesCount = 0;
		int pageCount = 0;
		ResultSet preResultSet = this.dbUtils.execQuery(connection, strPreSQL,
				new Object[] {});
		try {
			if (preResultSet.next()) {
				commoditiesCount = preResultSet.getInt(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (pageSize == 0) {
			pageSize = commoditiesCount;
			index = 1;
		} else {

		}
		if (commoditiesCount % pageSize == 0) {
			pageCount = commoditiesCount / pageSize;
		} else {
			pageCount = commoditiesCount / pageSize + 1;
		}
		String strSQL = "select * from commoditiesInf order by releaseTime desc";
		// 步骤4：调用dbutils中的方法完成对数据库的查询操作
		ResultSet resultSet = this.dbUtils.execQuery(connection, strSQL,
				new Object[] {});
		// 步骤5：获取结果集合并封装成一个对象
		List<Commodities> lstCommodities = new ArrayList<Commodities>();
		try {
			while (resultSet.next()) {
				Commodities commodities = new Commodities();
				commodities.setComId(resultSet.getInt(1));
				commodities.setOwnerId(resultSet.getInt(2));
				commodities.setOwnerName(resultSet.getString(3));
				commodities.setNickName(resultSet.getString(4));
				commodities.setComName(resultSet.getString(5));
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
				commodities.setPageCount(pageCount);
				lstCommodities.add(commodities);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			this.connectionManager.closeConnection(connection);
		}
		return lstCommodities;
	}

	@Override
	public List<Commodities> selectCommoditiesByType(int key, int index,
			int pageSize, String order) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		
		String comType = new String();
		switch (key) {
		case 1:
			comType = "交易书籍";
			break;
		case 2:
			comType = "交易用品";
			break;
		case 3:
			comType = "交易其它";
			break;
		case 4:
			comType = "交换书籍";
			break;
		case 5:
			comType = "交换用品";
			break;
		case 6:
			comType = "交换其它";
			break;
		case 7:
			comType = "漂流书籍";
			break;
		case 8:
			comType = "漂流物品";
			break;
		case 9:
			comType = "漂流其它";
			break;
		case 10:
			comType = "交易";
			break;
		case 11:
			comType = "交换";
			break;
		case 12:
			comType = "漂流";
			break;
		default:
			break;
		}
		String sComType = '%' + comType + '%';
		int commoditiesCount = 0;
		int pageCount = 0;
		String strPreSQL =  "select count(*) from commoditiesInf where (comTypeName like ? )and (offTime is null) order by releaseTime";
		ResultSet preResultSet = this.dbUtils.execQuery(connection, strPreSQL,
				new Object[] {sComType});
		try {
			if (preResultSet.next()) {
				commoditiesCount = preResultSet.getInt(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (pageSize == 0) {
			pageSize = commoditiesCount;
			index = 1;
		} else {

		}
		if (commoditiesCount % pageSize == 0) {
			pageCount = commoditiesCount / pageSize;
		} else {
			pageCount = commoditiesCount / pageSize + 1;
		}
		
		String strSQL = "select * from commoditiesInf where (comTypeName like ? )and (offTime is null) order by "
				+ order + " desc " + " limit ?,?";
		Object[] params = new Object[] { sComType, (index - 1) * pageSize,
				pageSize };
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
				commodities.setNickName(resultSet.getString(4));
				commodities.setComName(resultSet.getString(5));
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
				commodities.setPageCount(pageCount);
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
	public List<Commodities> selectCommoditiesByPrice(int key, int index,
			int pageSize, String order) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		int commoditiesCount = 0;
		int pageCount = 0;
		String strPreSQL = "";
		String strSQL = new String();
		String comTypeBook = new String();
		String comTypeGoods = new String();
		String comTypeOther = new String();
		switch (key) {
		case 1:
			comTypeBook = "交易书籍";
			comTypeGoods = "交易用品";
			comTypeOther = "交易其它";
			strPreSQL = "select count(*) from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (price between 0.0 and 50.0 ) and (offTime is null) order by releaseTime";
			strSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (price between 0.0 and 50.0 )and (offTime is null) order by "
					+ order + " limit ?,? ";
			break;
		case 2:
			comTypeBook = "交易书籍";
			comTypeGoods = "交易用品";
			comTypeOther = "交易其它";
			strPreSQL = "select count(*) from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (price between 50.0 and 200.0)and (offTime is null) order by releaseTime";
			strSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (price between 50.0 and 200.0) and (offTime is null) order by "
					+ order + " desc " + " limit ?,?";
			break;
		case 3:
			comTypeBook = "交易书籍";
			comTypeGoods = "交易用品";
			comTypeOther = "交易其它";
			strPreSQL = "select count(*) from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (price >=200.0) and (offTime is null) order by releaseTime";
			strSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (price >=200.0) and (offTime is null) order by "
					+ order + " desc " + " limit ?,?";
			break;

		case 4:
			comTypeBook = "漂流书籍";
			comTypeGoods = "漂流用品";
			comTypeOther = "漂流其它";
			strPreSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (requiredScore between 0.0 and 50.0) and (offTime is null) order by  releaseTime";
			strSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (requiredScore between 0.0 and 50.0) and (offTime is null) order by "
					+ order + " desc " + " limit ?,?";
			break;
		case 5:
			comTypeBook = "漂流书籍";
			comTypeGoods = "漂流用品";
			comTypeOther = "漂流其它";
			strPreSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (requiredScore between 50.0 and 200.0) and (offTime is null) order by  releaseTime";
			strSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (requiredScore between 50.0 and 200.0) and (offTime is null) order by "
					+ order + " desc " + " limit ?,?";
			break;
		case 6:
			comTypeBook = "漂流书籍";
			comTypeGoods = "漂流用品";
			comTypeOther = "漂流其它";
			strPreSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (requiredScore>=200) and (offTime is null) order by  releaseTime";
			strSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (requiredScore>=200) and (offTime is null) order by "
					+ order + " desc " + " limit ?,?";
			break;
		default:
			break;
		}
		String sComTypeBook = '%' + comTypeBook + '%';
		String sComTypeGoods = '%' + comTypeGoods + '%';
		String sComTypeOther = '%' + comTypeOther + '%';
		ResultSet preResultSet = this.dbUtils.execQuery(connection, strPreSQL,
				new Object[] { sComTypeBook, sComTypeGoods, sComTypeOther });
		try {
			if (preResultSet.next()) {
				commoditiesCount = preResultSet.getInt(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (pageSize == 0) {
			pageSize = commoditiesCount;
			index = 1;
		} else {

		}
		if (commoditiesCount % pageSize == 0) {
			pageCount = commoditiesCount / pageSize;
		} else {
			pageCount = commoditiesCount / pageSize + 1;
		}
		Object[] params = new Object[] { sComTypeBook, sComTypeGoods,
				sComTypeOther, (index - 1) * pageSize, pageSize };
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
				commodities.setNickName(resultSet.getString(4));
				commodities.setComName(resultSet.getString(5));
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
				commodities.setPageCount(pageCount);
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
	public List<Commodities> selectCommoditiesByTime(int key, int index,
			int pageSize, String order) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strPreSQL = "select count(*) as commoditiesCount from commoditiesInf";
		int commoditiesCount = 0;
		int pageCount = 0;
		String strSQL = new String();
		String comTypeBook = new String();
		String comTypeGoods = new String();
		String comTypeOther = new String();
		switch (key) {
		case 1:
			comTypeBook = "交易书籍";
			comTypeGoods = "交易用品";
			comTypeOther = "交易其它";
			strPreSQL = "select count(*) from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (DATE_SUB(CURDATE(),INTERVAL 1 WEEK)<=date(releaseTime) )and (offTime is null) order by releaseTime";
			strSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (DATE_SUB(CURDATE(),INTERVAL 1 WEEK)<=date(releaseTime) )and (offTime is null) order by "
					+ order + " desc " + " limit ?,?";
			break;
		case 2:
			comTypeBook = "交易书籍";
			comTypeGoods = "交易用品";
			comTypeOther = "交易其它";
			strPreSQL = "select count(*) from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (DATE_SUB(CURDATE(),INTERVAL 1 MONTH)<=date(releaseTime) )and (offTime is null) order by releaseTime";
			strSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (DATE_SUB(CURDATE(),INTERVAL 1 MONTH)<=date(releaseTime) ) and (offTime is null) order by "
					+ order + " desc " + "limit ?,?";
			break;
		case 3:
			comTypeBook = "交易书籍";
			comTypeGoods = "交易用品";
			comTypeOther = "交易其它";
			strPreSQL = "select count(*) from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (CURDATE()>=date(releaseTime))and (offTime is null) order by releaseTime";
			strSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (CURDATE()>=date(releaseTime)) and (offTime is null) order by "
					+ order + " desc " + " limit ?,?";
			break;
		case 4:
			comTypeBook = "交换书籍";
			comTypeGoods = "交换用品";
			comTypeOther = "交换其它";
			strPreSQL = "select count(*) from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (DATE_SUB(CURDATE(),INTERVAL 1 WEEK)<=date(releaseTime) )and (offTime is null) order by releaseTime";
			strSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (DATE_SUB(CURDATE(),INTERVAL 1 WEEK)<=date(releaseTime) )and (offTime is null) order by "
					+ order + " desc " + " limit ?,?";
			break;
		case 5:
			comTypeBook = "交换书籍";
			comTypeGoods = "交换用品";
			comTypeOther = "交换其它";
			strPreSQL = "select count(*) from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (DATE_SUB(CURDATE(),INTERVAL 1 MONTH)<=date(releaseTime) )and (offTime is null) order by releaseTime";
			strSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (DATE_SUB(CURDATE(),INTERVAL 1 MONTH)<=date(releaseTime) ) and (offTime is null) order by "
					+ order + " desc " + " limit ?,?";
			break;
		case 6:
			comTypeBook = "交换书籍";
			comTypeGoods = "交换用品";
			comTypeOther = "交换其它";
			strPreSQL = "select count(*) from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (CURDATE()>=date(releaseTime))and (offTime is null) order by releaseTime";
			strSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (CURDATE()>=date(releaseTime)) and (offTime is null) order by "
					+ order + " desc " + " limit ?,?";
			break;

		case 7:
			comTypeBook = "漂流书籍";
			comTypeGoods = "漂流用品";
			comTypeOther = "漂流其它";
			strPreSQL = "select count(*) from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (DATE_SUB(CURDATE(),INTERVAL 1 WEEK)<=date(releaseTime) )and (offTime is null) order by releaseTime";
			strSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (DATE_SUB(CURDATE(),INTERVAL 1 WEEK)<=date(releaseTime) ) and (offTime is null) order by "
					+ order + " desc " + " limit ?,?";
			break;
		case 8:
			comTypeBook = "漂流书籍";
			comTypeGoods = "漂流用品";
			comTypeOther = "漂流其它";
			strPreSQL = "select count(*) from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (DATE_SUB(CURDATE(),INTERVAL 1 MONTH)<=date(releaseTime) )and (offTime is null) order by releaseTime";
			strSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (DATE_SUB(CURDATE(),INTERVAL 1 MONTH)<=date(releaseTime) ) and (offTime is null) order by "
					+ order + " desc " + " limit ?,?";
			break;
		case 9:
			comTypeBook = "漂流书籍";
			comTypeGoods = "漂流用品";
			comTypeOther = "漂流其它";
			strPreSQL = "select count(*) from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (CURDATE()>=date(releaseTime) )and (offTime is null) order by releaseTime";
			strSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (CURDATE()>=date(releaseTime) ) and (offTime is null) order by "
					+ order + " desc " + " limit ?,?";
			break;
		default:
			break;
		}
		String sComTypeBook = '%' + comTypeBook + '%';
		String sComTypeGoods = '%' + comTypeGoods + '%';
		String sComTypeOther = '%' + comTypeOther + '%';
		ResultSet preResultSet = this.dbUtils.execQuery(connection, strPreSQL,
				new Object[] { sComTypeBook, sComTypeGoods, sComTypeOther });
		try {
			if (preResultSet.next()) {
				commoditiesCount = preResultSet.getInt(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (pageSize == 0) {
			pageSize = commoditiesCount;
			index = 1;
		} else {
		}

		if (commoditiesCount % pageSize == 0) {
			pageCount = commoditiesCount / pageSize;
		} else {
			pageCount = commoditiesCount / pageSize + 1;
		}
		Object[] params = new Object[] { sComTypeBook, sComTypeGoods,
				sComTypeOther, (index - 1) * pageSize, pageSize };
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
				commodities.setNickName(resultSet.getString(4));
				commodities.setComName(resultSet.getString(5));
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
				commodities.setPageCount(pageCount);
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
	public List<Commodities> selectCommoditiesByFocus(int key, int index,
			int pageSize, String order) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strPreSQL = "select count(*) as commoditiesCount from commoditiesInf order by releaseTime desc";
		int commoditiesCount = 0;
		int pageCount = 0;

		String strSQL = new String();
		String comTypeBook = new String();
		String comTypeGoods = new String();
		String comTypeOther = new String();
		switch (key) {
		case 1:
			comTypeBook = "交易书籍";
			comTypeGoods = "交易用品";
			comTypeOther = "交易其它";
			strPreSQL = "select count(*) from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and ( comFocus between 0.0 and 20.0 )and (offTime is null) order by releaseTime";
			strSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and ( comFocus between 0.0 and 20.0 )and (offTime is null) order by "
					+ order + " desc "+" limit ?,?";
			break;
		case 2:
			comTypeBook = "交易书籍";
			comTypeGoods = "交易用品";
			comTypeOther = "交易其它";
			strPreSQL = "select count(*) from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and ( comFocus between 20.0 and 100.0 )and (offTime is null) order by releaseTime";
			strSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and ( comFocus between 20.0 and 100.0) and (offTime is null) order by "
					+ order + " desc "+" limit ?,?";
			break;
		case 3:
			comTypeBook = "交易书籍";
			comTypeGoods = "交易用品";
			comTypeOther = "交易其它";
			strPreSQL = "select count(*) from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and ( comFocus >=100.0 )and (offTime is null) order by releaseTime";
			strSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and ( comFocus >=100.0) and (offTime is null) order by "
					+ order + " desc "+" limit ?,?";
			break;
		case 4:
			comTypeBook = "交换书籍";
			comTypeGoods = "交换用品";
			comTypeOther = "交换其它";
			strPreSQL = "select count(*) from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and ( comFocus between 0.0 and 20.0 )and (offTime is null) order by releaseTime";
			strSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and ( comFocus between 0.0 and 20.0 )and (offTime is null) order by "
					+ order + " desc "+" limit ?,?";
			break;
		case 5:
			comTypeBook = "交换书籍";
			comTypeGoods = "交换用品";
			comTypeOther = "交换其它";
			strPreSQL = "select count(*) from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and ( comFocus between 20.0 and 100.0 )and (offTime is null) order by releaseTime";
			strSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and ( comFocus between 20.0 and 100.0) and (offTime is null) order by "
					+ order + " desc "+" limit ?,?";
			break;
		case 6:
			comTypeBook = "交换书籍";
			comTypeGoods = "交换用品";
			comTypeOther = "交换其它";
			strPreSQL = "select count(*) from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and ( comFocus >=100.0)and (offTime is null) order by releaseTime";
			strSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and ( comFocus >=100.0) and (offTime is null) order by "
					+ order + " desc "+" limit ?,?";
			break;

		case 7:
			comTypeBook = "漂流书籍";
			comTypeGoods = "漂流用品";
			comTypeOther = "漂流其它";
			strPreSQL = "select count(*) from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and ( comFocus between 0.0 and 20.0 )and (offTime is null) order by releaseTime";
			strSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and ( comFocus between 0.0 and 20.0) and (offTime is null) order by "
					+ order + " desc "+" limit ?,?";
			break;
		case 8:
			comTypeBook = "漂流书籍";
			comTypeGoods = "漂流用品";
			comTypeOther = "漂流其它";
			strPreSQL = "select count(*) from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and ( comFocus between 20.0 and 100.0)and (offTime is null) order by releaseTime";
			strSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and ( comFocus between 20.0 and 100.0) and (offTime is null) order by "
					+ order + " desc "+" limit ?,?";
			break;
		case 9:
			comTypeBook = "漂流书籍";
			comTypeGoods = "漂流用品";
			comTypeOther = "漂流其它";
			strPreSQL = "select count(*) from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and (  comFocus>=100)and (offTime is null) order by releaseTime";
			strSQL = "select * from commoditiesInf where (comTypeName like ? or comTypeName like ? or comTypeName like ?) and ( comFocus>=100) and (offTime is null) order by "
					+ order + " desc "+" limit ?,?";
			break;
		default:
			break;
		}
		String sComTypeBook = '%' + comTypeBook + '%';
		String sComTypeGoods = '%' + comTypeGoods + '%';
		String sComTypeOther = '%' + comTypeOther + '%';
		ResultSet preResultSet = this.dbUtils.execQuery(connection, strPreSQL,
				new Object[] { sComTypeBook, sComTypeGoods, sComTypeOther });
		try {
			if (preResultSet.next()) {
				commoditiesCount = preResultSet.getInt(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (pageSize == 0) {
			pageSize = commoditiesCount;
			index = 1;
		} else {

		}
		if (commoditiesCount % pageSize == 0) {
			pageCount = commoditiesCount / pageSize;
		} else {
			pageCount = commoditiesCount / pageSize + 1;
		}
		Object[] params = new Object[] { sComTypeBook, sComTypeGoods,
				sComTypeOther, (index - 1) * pageSize, pageSize };
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
				commodities.setNickName(resultSet.getString(4));
				commodities.setComName(resultSet.getString(5));
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
				commodities.setPageCount(pageCount);
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

}
