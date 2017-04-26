package org.gr.woc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.gr.woc.dao.IFriendsDao;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.DBUtils;
import org.gr.woc.vo.Friends;

public class FriendsDaoImpl implements IFriendsDao {
	private ConnectionManager connectionManager;
	private Connection connection;
	private DBUtils dbUtils;

	public FriendsDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.connectionManager = new ConnectionManager();
		this.dbUtils = new DBUtils();
		this.connection = null;

	}

	@Override
	public List<Friends> selectAll(int userId) {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<Friends> lstFriends = new ArrayList<Friends>();
		System.out.println(lstFriends);
		// 步骤2：获取一个数据库的连接对象
		this.connection = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from friends where userId=? order by friendLevel";
		Object[] params=new Object[]{userId};
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils.execQuery(connection, strSQL,
				params);
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while (resultSet.next()) {
				// 步骤5-1：创建一个对象
				Friends friends = new Friends();
				friends.setUserId(resultSet.getInt(1));
				friends.setFriendId(resultSet.getInt(2));
				friends.setFriendName(resultSet.getString(3));
				friends.setFriendLevel(resultSet.getInt(4));
				friends.setFriendStatus(resultSet.getInt(5));
				friends.setLocation(resultSet.getString(6));
				friends.setPhone(resultSet.getString(7));
				friends.setPostCode(resultSet.getString(8));
				friends.setEmail(resultSet.getString(9));
				friends.setGender(resultSet.getString(10));
				friends.setHobby(resultSet.getString(11));
				friends.setMajor(resultSet.getString(12));
				friends.setScore(resultSet.getInt(13));
				friends.setNickName(resultSet.getString(14));
				friends.setFriendRegion(resultSet.getString(15));
				friends.setFriendPhoto(resultSet.getString(16));
				// 步骤5-2：将封装好的对象添加到List集合中
				lstFriends.add(friends);
			}
			// 返回结果
			return lstFriends;
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
	public Friends selsectByName(String friendName) {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		Friends friends = new Friends();
		// 步骤2：获取一个数据库的连接对象
		this.connection = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from friends where friendName=? order by userId";
		Object[] params = new Object[] { friendName };
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils.execQuery(connection, strSQL,
				params);
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			if (resultSet.next()) {
				
				// Friends friends = new Friends();
				friends.setUserId(resultSet.getInt(1));
				friends.setFriendId(resultSet.getInt(2));
				friends.setFriendName(resultSet.getString(3));
				friends.setFriendLevel(resultSet.getInt(4));
				friends.setFriendStatus(resultSet.getInt(5));
				friends.setLocation(resultSet.getString(6));
				friends.setPhone(resultSet.getString(7));
				friends.setPostCode(resultSet.getString(8));
				friends.setEmail(resultSet.getString(9));
				friends.setGender(resultSet.getString(10));
				friends.setHobby(resultSet.getString(11));
				friends.setMajor(resultSet.getString(12));
				friends.setScore(resultSet.getInt(13));
				friends.setNickName(resultSet.getString(14));
				friends.setFriendRegion(resultSet.getString(15));
				friends.setFriendPhoto(resultSet.getString(16));
				
			}
			// 返回结果
			return friends;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			// 步骤6：关闭数据库连接
			this.connectionManager.closeConnection(connection);
		}
	}

}
