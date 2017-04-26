package org.gr.woc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.gr.woc.dao.IDetail_InfoDao;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.DBUtils;
import org.gr.woc.po.Detail_Info;

public class Detail_InfoDaoImpl implements IDetail_InfoDao {

	private ConnectionManager connectionManager;
	private Connection connection;
	private DBUtils dbUtils;

	public Detail_InfoDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.connectionManager = new ConnectionManager();
		this.dbUtils = new DBUtils();
		this.connection = null;
	}

	@Override
	public int insert(Detail_Info detail_Info) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：开启一个事务
		// TransactionManager.conn = this.connection;
		// TransactionManager.beginTransaction();

		// 步骤3：拆分对向属性
		int userId = detail_Info.getUserId();
		String location = detail_Info.getLocation();
		String phone = detail_Info.getPhone();
		int postCode = detail_Info.getPostCode();
		String email = detail_Info.getEmail();
		String gender = detail_Info.getGender();
		String hobby = detail_Info.getHobby();
		String major = detail_Info.getMajor();
		int score = detail_Info.getScore();
		String nickName = detail_Info.getNickName();
		String userRegion = detail_Info.getUserRegion();
		String userPhoto = detail_Info.getUserPhoto();
		// 步骤4：设置添加SQL语句模板
		String strSQL = "insert into detail_info values(null,?,?,?,?,?,?,?,?,?,?,?,?)";
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
		String strSQL = "delete from detail_info where infId=?";
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
	public int update(Detail_Info detail_Info) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：开启事务
		// TransactionManager.connection = this.connection;
		// TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "update detail_info set userId=?, location=?, phone=?, postCode=?, email=?, gender=?, hobby=?, major=?, score=?, nickName=?, userRegion=?, userPhoto=? where infId=?";
		Object[] params = new Object[] { detail_Info.getUserId(),
				detail_Info.getLocation(), detail_Info.getPhone(),
				detail_Info.getPostCode(), detail_Info.getEmail(),
				detail_Info.getGender(), detail_Info.getHobby(),
				detail_Info.getMajor(), detail_Info.getScore(),
				detail_Info.getNickName(), detail_Info.getUserRegion(),
				detail_Info.getUserPhoto() ,detail_Info.getInfId()};
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
	public List<Detail_Info> selectAll() {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<Detail_Info> lstInf = new ArrayList<Detail_Info>();
		// 步骤2：获取一个数据库的连接对象
		this.connection = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from detai_info order by infId";
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils.execQuery(connection, strSQL,
				new Object[] {});
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while (resultSet.next()) {
				// 步骤5-1：创建一个对象
				Detail_Info detail_Info = new Detail_Info();
				detail_Info.setInfId(resultSet.getInt(1));
				detail_Info.setUserId(resultSet.getInt(2));
				detail_Info.setLocation(resultSet.getString(3));
				detail_Info.setPhone(resultSet.getString(4));
				detail_Info.setPostCode(resultSet.getInt(5));
				detail_Info.setEmail(resultSet.getString(6));
				detail_Info.setGender(resultSet.getString(7));
				detail_Info.setHobby(resultSet.getString(8));
				detail_Info.setMajor(resultSet.getString(9));
				detail_Info.setScore(resultSet.getInt(10));
				detail_Info.setNickName(resultSet.getString(11));
				detail_Info.setUserRegion(resultSet.getString(12));
				detail_Info.setUserPhoto(resultSet.getString(13));
				// 步骤5-2：将封装好的对象添加到List集合中
				lstInf.add(detail_Info);
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
	public Detail_Info selectById(int infId) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strSQL = "select * from detail_info where infId=?";
		Object[] params = new Object[] { infId };
		// 步骤4：调用dbutils中的方法完成对数据库的查询操作
		ResultSet resultSet = this.dbUtils
				.execQuery(connection, strSQL, params);
		// 步骤5：获取结果集合并封装成一个对象
		try {
			if (resultSet.next()) {
				// 步骤6：创建一个Customers对象
				Detail_Info detail_Info = new Detail_Info();
				detail_Info.setInfId(resultSet.getInt(1));
				detail_Info.setUserId(resultSet.getInt(2));
				detail_Info.setLocation(resultSet.getString(3));
				detail_Info.setPhone(resultSet.getString(4));
				detail_Info.setPostCode(resultSet.getInt(5));
				detail_Info.setEmail(resultSet.getString(6));
				detail_Info.setGender(resultSet.getString(7));
				detail_Info.setHobby(resultSet.getString(8));
				detail_Info.setMajor(resultSet.getString(9));
				detail_Info.setScore(resultSet.getInt(10));
				detail_Info.setNickName(resultSet.getString(11));
				detail_Info.setUserRegion(resultSet.getString(12));
				detail_Info.setUserPhoto(resultSet.getString(13));

				// 步骤7：返回对象
				return detail_Info;
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
	public Detail_Info selsectByUserId(int userId) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strSQL = "select * from detail_info where userId=?";
		Object[] params = new Object[] { userId };
		// 步骤4：调用dbutils中的方法完成对数据库的查询操作
		ResultSet resultSet = this.dbUtils
				.execQuery(connection, strSQL, params);
		// 步骤5：获取结果集合并封装成一个对象
		try {
			if (resultSet.next()) {
				// 步骤6：创建一个Customers对象
				Detail_Info detail_Info = new Detail_Info();
				detail_Info.setInfId(resultSet.getInt(1));
				detail_Info.setUserId(resultSet.getInt(2));
				detail_Info.setLocation(resultSet.getString(3));
				detail_Info.setPhone(resultSet.getString(4));
				detail_Info.setPostCode(resultSet.getInt(5));
				detail_Info.setEmail(resultSet.getString(6));
				detail_Info.setGender(resultSet.getString(7));
				detail_Info.setHobby(resultSet.getString(8));
				detail_Info.setMajor(resultSet.getString(9));
				detail_Info.setScore(resultSet.getInt(10));
				detail_Info.setNickName(resultSet.getString(11));
				detail_Info.setUserRegion(resultSet.getString(12));
				detail_Info.setUserPhoto(resultSet.getString(13));

				// 步骤7：返回对象
				return detail_Info;
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
