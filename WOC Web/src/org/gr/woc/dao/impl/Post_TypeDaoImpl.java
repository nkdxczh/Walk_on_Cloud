package org.gr.woc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.gr.woc.biz.impl.Post_TypeBizImpl;
import org.gr.woc.dao.IPost_TypeDao;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.DBUtils;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Post_Type;

public class Post_TypeDaoImpl implements IPost_TypeDao {

	private Connection conn = null;
	private DBUtils dbUtils = null;
	public Post_TypeDaoImpl(Connection conn) {
		super();
		this.dbUtils = new DBUtils();
		this.conn = conn;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Post_Type selectById(Post_Type post_Type) {
		// TODO Auto-generated method stub
		int posttypeid=post_Type.getPostTypeId();
		String strSQL = "select * from post_type where posttypeid=?";
		ResultSet resultSet= this.dbUtils.execQuery(this.conn, strSQL, new Object[]{posttypeid});
		try {
			if(resultSet.next())
			{
				Post_Type post_Type2=new Post_Type();
				post_Type2.setPostTypeId(resultSet.getInt(1));
				post_Type2.setPostTypeName(resultSet.getString(2));
				return post_Type2;
			}
			else return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Post_Type> selectAll() {
		// TODO Auto-generated method stub
		String strSQL = "select * from post_type ";
		List<Post_Type> list=new ArrayList<Post_Type>();
		ResultSet resultSet= this.dbUtils.execQuery(this.conn, strSQL, new Object[]{});
		try {
			while(resultSet.next())
			{
				Post_Type post_Type2=new Post_Type();
				post_Type2.setPostTypeId(resultSet.getInt(1));
				post_Type2.setPostTypeName(resultSet.getString(2));
				list.add(post_Type2);	
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Post_Type> selectByBankuai(int bankuaiid) {
		// TODO Auto-generated method stub
		int low=(bankuaiid/100)*100;
		int high=low+100;
		List<Post_Type> list=new ArrayList<Post_Type>();
		String strSQL = "select * from post_type where posttypeid>? and posttypeid<?";
		ResultSet resultSet= this.dbUtils.execQuery(this.conn, strSQL, new Object[]{low,high});
		try {
			while(resultSet.next())
			{
				Post_Type post_Type2=new Post_Type();
				post_Type2.setPostTypeId(resultSet.getInt(1));
				post_Type2.setPostTypeName(resultSet.getString(2));
				list.add(post_Type2);
			}
			 return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	

}
