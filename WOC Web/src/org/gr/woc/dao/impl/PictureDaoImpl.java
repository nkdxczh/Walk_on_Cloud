package org.gr.woc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.gr.woc.dao.IPictureDao;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.DBUtils;
import org.gr.woc.po.Picture;

public class PictureDaoImpl implements IPictureDao {

	private ConnectionManager connectionManager;
	private Connection connection;
	private DBUtils dbUtils;

	public PictureDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.connectionManager = new ConnectionManager();
		this.dbUtils = new DBUtils();
		this.connection = null;
	}

	@Override
	public int insert(Picture picture) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：开启一个事务
		// TransactionManager.conn = this.connection;
		// TransactionManager.beginTransaction();

		// 步骤3：拆分对向属性
		int comId = picture.getComId();
		String path = picture.getPath();
		// 步骤4：设置添加SQL语句模板
		String strSQL = "insert into picture values(null,?,?)";
		Object[] params = new Object[] { comId, path };
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
	public int deleteById(int pictureId) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：开启事务
		// TransactionManager.connection = this.connection;
		// TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "delete from picture where pictureId=?";
		Object[] params = new Object[] { pictureId };
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
	public int update(Picture picture) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：开启事务
		// TransactionManager.connection = this.connection;
		// TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "update picture set comId=?, path=? where pictureId=?";
		Object[] params = new Object[] { picture.getComId(), picture.getPath(),
				picture.getPictureId() };
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
	public List<Picture> selectAll() {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<Picture> lstPicture = new ArrayList<Picture>();
		// 步骤2：获取一个数据库的连接对象
		this.connection = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from picture order by pictureId";
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils.execQuery(connection, strSQL,
				new Object[] {});
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while (resultSet.next()) {
				// 步骤5-1：创建一个对象
				Picture picture = new Picture();
				picture.setPictureId(resultSet.getInt(1));
				picture.setComId(resultSet.getInt(2));
				picture.setPath(resultSet.getString(3));
				// 步骤5-2：将封装好的对象添加到List集合中
				lstPicture.add(picture);
			}
			// 返回结果
			return lstPicture;
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
	public Picture selectById(int pictureId) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.connection = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strSQL = "select * from picture where pictureId=?";
		Object[] params = new Object[] { pictureId };
		// 步骤4：调用dbutils中的方法完成对数据库的查询操作
		ResultSet resultSet = this.dbUtils
				.execQuery(connection, strSQL, params);
		// 步骤5：获取结果集合并封装成一个对象
		try {
			if (resultSet.next()) {
				// 步骤6：创建一个对象
				Picture picture = new Picture();
				picture.setPictureId(resultSet.getInt(1));
				picture.setComId(resultSet.getInt(2));
				picture.setPath(resultSet.getString(3));
				// 步骤7：返回对象
				return picture;
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
	public List<Picture> selectByComId(int comId) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
				this.connection = this.connectionManager.openConnection();
				// 步骤2：创建SQL语句模板
				String strSQL = "select * from picture where comId=?";
				Object[] params = new Object[] { comId };
				// 步骤4：调用dbutils中的方法完成对数据库的查询操作
				ResultSet resultSet = this.dbUtils
						.execQuery(connection, strSQL, params);
				List<Picture> lstPicture=new ArrayList<Picture>();
				// 步骤5：获取结果集合并封装成一个对象
				try {
					while (resultSet.next()) {
						// 步骤6：创建一个对象
						Picture picture = new Picture();
						picture.setPictureId(resultSet.getInt(1));
						picture.setComId(resultSet.getInt(2));
						picture.setPath(resultSet.getString(3));
						// 步骤7：返回对象
						lstPicture.add(picture);
					} 
					return lstPicture;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				} finally {
					this.connectionManager.closeConnection(connection);
				}
	}

}
