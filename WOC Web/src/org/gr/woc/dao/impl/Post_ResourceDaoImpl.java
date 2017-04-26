package org.gr.woc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.gr.woc.dao.IPost_ResourceDao;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.DBUtils;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Post;
import org.gr.woc.po.Post_Resource;

public class Post_ResourceDaoImpl implements IPost_ResourceDao {

	private Connection conn = null;
	private DBUtils dbUtils = null;
	public Post_ResourceDaoImpl(Connection conn) {
		super();
		this.dbUtils = new DBUtils();
		this.conn = conn;
		// TODO Auto-generated constructor stub
	}
	@Override
	public int insert(Post_Resource resource) {
		// TODO Auto-generated method stub
		int posid=resource.getPostId();
		String respath=resource.getResPath();
		String strSQL = "insert into post_resource values(null,?,?)";
		return this.dbUtils.execOthers(this.conn, strSQL, new Object[]{posid,respath});	
		
	}
	@Override
	public List<Post_Resource> selectById(Post post) {
		// TODO Auto-generated method stub
		int posid=post.getPostId();
		String strSQL = "select * from post_resource where postid=?";
		ResultSet resultSet= this.dbUtils.execQuery(this.conn, strSQL, new Object[]{posid});
		List<Post_Resource> list=new ArrayList<Post_Resource>();
		try {
			while (resultSet.next())
			{
				Post_Resource resource=new Post_Resource();
				resource.setPostId(resultSet.getInt(2));
				resource.setResId(resultSet.getInt(1));
				resource.setResPath(resultSet.getString(3));
				list.add(resource);
				
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
