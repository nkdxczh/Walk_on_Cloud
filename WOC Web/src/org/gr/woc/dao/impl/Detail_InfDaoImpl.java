package org.gr.woc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.gr.woc.dao.IDetail_InfDao;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.DBUtils;
import org.gr.woc.po.Detail_Inf;

public class Detail_InfDaoImpl implements IDetail_InfDao {

	private ConnectionManager connectionManager;
	private Connection connection;
	private DBUtils dbUtils;

	public Detail_InfDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.connectionManager = new ConnectionManager();
		this.dbUtils = new DBUtils();
		this.connection = null;
	}

	@Override
	public int insert(Detail_Inf detail_Inf) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：开启一个事务
		// TransactionManager.conn = this.connection;
		// TransactionManager.beginTransaction();

		// 步骤3：拆分对向属性
		int userId = detail_Inf.getUserId();
		String location = detail_Inf.getLocation();
		String phone = detail_Inf.getPhone();
		int postCode = detail_Inf.getPostCode();
		String email = detail_Inf.getEmail();
		String gender = detail_Inf.getGender();
		String hobby = detail_Inf.getHobby();
		String major = detail_Inf.getMajor();
		int score = detail_Inf.getScore();
		String nickName = detail_Inf.getNickName();
		String userRegion = detail_Inf.getUserRegion();
		String userPhoto = detail_Inf.getUserPhoto();
		// 步骤4：设置添加SQL语句模板
		String strSQL = "insert into detail_inf values(null,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] { userId, location, phone, postCode,
				email, gender, hobby, major, score, nickName, userRegion,
				userPhoto };
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
	public int deleteById(int infId) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：开启事务
		// TransactionManager.connection = this.connection;
		// TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "delete from detail_inf where infId=?";
		Object[] params = new Object[] { infId };
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
	public int update(Detail_Inf detail_Inf) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：开启事务
		// TransactionManager.connection = this.connection;
		// TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "update detail_inf set userId=?, location=?, phone=?, postCode=?, email=?, gender=?, hobby=?, major=?, score=?, nickName=?, userRegion=?, userPhoto=? where infId=?";
		Object[] params = new Object[] { detail_Inf.getUserId(),
				detail_Inf.getLocation(), detail_Inf.getPhone(),
				detail_Inf.getPostCode(), detail_Inf.getEmail(),
				detail_Inf.getGender(), detail_Inf.getHobby(),
				detail_Inf.getMajor(), detail_Inf.getScore(),
				detail_Inf.getNickName(), detail_Inf.getUserRegion(),
				detail_Inf.getUserPhoto() ,detail_Inf.getInfId()};
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
	public List<Detail_Inf> selectAll() {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<Detail_Inf> lstInf = new ArrayList<Detail_Inf>();
		// 步骤2：获取一个数据库的连接对象
		this.connection = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from detai_inf order by infId";
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils.execQuery(connection, strSQL,
				new Object[] {});
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while (resultSet.next()) {
				// 步骤5-1：创建一个对象
				Detail_Inf detail_Inf = new Detail_Inf();
				detail_Inf.setInfId(resultSet.getInt(1));
				detail_Inf.setUserId(resultSet.getInt(2));
				detail_Inf.setLocation(resultSet.getString(3));
				detail_Inf.setPhone(resultSet.getString(4));
				detail_Inf.setPostCode(resultSet.getInt(5));
				detail_Inf.setEmail(resultSet.getString(6));
				detail_Inf.setGender(resultSet.getString(7));
				detail_Inf.setHobby(resultSet.getString(8));
				detail_Inf.setMajor(resultSet.getString(9));
				detail_Inf.setScore(resultSet.getInt(10));
				detail_Inf.setNickName(resultSet.getString(11));
				detail_Inf.setUserRegion(resultSet.getString(12));
				detail_Inf.setUserPhoto(resultSet.getString(13));
				// 步骤5-2：将封装好的对象添加到List集合中
				lstInf.add(detail_Inf);
			}
			// 返回结果
			return lstInf;
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
	public Detail_Inf selectById(int infId) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strSQL = "select * from detail_inf where infId=?";
		Object[] params = new Object[] { infId };
		// 步骤4：调用dbutils中的方法完成对数据库的查询操作
		ResultSet resultSet = this.dbUtils
				.execQuery(connection, strSQL, params);
		// 步骤5：获取结果集合并封装成一个对象
		try {
			if (resultSet.next()) {
				// 步骤6：创建一个Customers对象
				Detail_Inf detail_Inf = new Detail_Inf();
				detail_Inf.setInfId(resultSet.getInt(1));
				detail_Inf.setUserId(resultSet.getInt(2));
				detail_Inf.setLocation(resultSet.getString(3));
				detail_Inf.setPhone(resultSet.getString(4));
				detail_Inf.setPostCode(resultSet.getInt(5));
				detail_Inf.setEmail(resultSet.getString(6));
				detail_Inf.setGender(resultSet.getString(7));
				detail_Inf.setHobby(resultSet.getString(8));
				detail_Inf.setMajor(resultSet.getString(9));
				detail_Inf.setScore(resultSet.getInt(10));
				detail_Inf.setNickName(resultSet.getString(11));
				detail_Inf.setUserRegion(resultSet.getString(12));
				detail_Inf.setUserPhoto(resultSet.getString(13));

				// 步骤7：返回对象
				return detail_Inf;
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
	public Detail_Inf selsectByUserId(int userId) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strSQL = "select * from detail_inf where userId=?";
		Object[] params = new Object[] { userId };
		// 步骤4：调用dbutils中的方法完成对数据库的查询操作
		ResultSet resultSet = this.dbUtils
				.execQuery(connection, strSQL, params);
		// 步骤5：获取结果集合并封装成一个对象
		try {
			if (resultSet.next()) {
				// 步骤6：创建一个Customers对象
				Detail_Inf detail_Inf = new Detail_Inf();
				detail_Inf.setInfId(resultSet.getInt(1));
				detail_Inf.setUserId(resultSet.getInt(2));
				detail_Inf.setLocation(resultSet.getString(3));
				detail_Inf.setPhone(resultSet.getString(4));
				detail_Inf.setPostCode(resultSet.getInt(5));
				detail_Inf.setEmail(resultSet.getString(6));
				detail_Inf.setGender(resultSet.getString(7));
				detail_Inf.setHobby(resultSet.getString(8));
				detail_Inf.setMajor(resultSet.getString(9));
				detail_Inf.setScore(resultSet.getInt(10));
				detail_Inf.setNickName(resultSet.getString(11));
				detail_Inf.setUserRegion(resultSet.getString(12));
				detail_Inf.setUserPhoto(resultSet.getString(13));

				// 步骤7：返回对象
				return detail_Inf;
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
