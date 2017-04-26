package org.gr.woc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.gr.woc.dao.ICCommentsDao;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.DBUtils;
import org.gr.woc.vo.CComments;

public class CCommentsDaoImpl implements ICCommentsDao {

	private ConnectionManager connectionManager;
	private Connection connection;
	private DBUtils dbUtils;
	
	public CCommentsDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.dbUtils=new DBUtils();
		this.connectionManager=new ConnectionManager();
	}

	@Override
	public List<CComments> selectByComId(int comId) {
		// TODO Auto-generated method stub
		List<CComments> lstCComments = new ArrayList<CComments>();
		// 步骤2：获取一个数据库的连接对象
		this.connection = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from ccomments where comId=? order by comId";
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils.execQuery(connection, strSQL,
				new Object[] {comId});
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while (resultSet.next()) {
				// 步骤5-1：创建一个Customers对象
				CComments cComments = new CComments();
				cComments.setComId(resultSet.getInt(1));
				cComments.setUserId(resultSet.getInt(2));
				cComments.setUserName(resultSet.getString(3));
				cComments.setUserLevel(resultSet.getInt(4));
				cComments.setUserStatus(resultSet.getInt(5));
				cComments.setComComment(resultSet.getString(6));
				cComments.setReleaseTime(resultSet.getDate(7));
				cComments.setNickName(resultSet.getString(8));
				
				// 步骤5-2：将封装好的对象添加到List集合中
				lstCComments.add(cComments);
			}
			// 返回结果
			return lstCComments;
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
