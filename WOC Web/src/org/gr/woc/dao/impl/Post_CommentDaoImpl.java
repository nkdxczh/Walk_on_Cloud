package org.gr.woc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.gr.woc.dao.IPost_CommentDao;
import org.gr.woc.db.DBUtils;
import org.gr.woc.po.Post;
import org.gr.woc.po.Post_Comment;
import org.gr.woc.po.User;

public class Post_CommentDaoImpl implements IPost_CommentDao {

	private Connection conn = null;
	private DBUtils dbUtils = null;
	
	public Post_CommentDaoImpl(Connection conn) {
		super();
		this.dbUtils = new DBUtils();
		this.conn = conn;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int insert(Post_Comment comment) {
		// TODO Auto-generated method stub
		int userid=comment.getUserId();
		int postid=comment.getPostId();
		int postReplyId=comment.getPostReplyId();
		String content=comment.getPostComContent();
		String strSQL = "insert into post_comment values(null,?,?,?,?,now())";
		return this.dbUtils.execOthers(this.conn, strSQL, new Object[]{postid,userid,content,postReplyId});
	}
	

	@Override
	public int delete(Post_Comment comment) {
		// TODO Auto-generated method stub
		int comid=comment.getPostComId();
		String strSQL = "delete from post_comment where postcomid=?";
		return this.dbUtils.execOthers(this.conn, strSQL, new Object[]{comid});
	}

	@Override
	public List<Post_Comment> selectByUserId(User user) {
		// TODO Auto-generated method stub
		int userid=user.getUserId();
		String strSQL = "select p.postcomid,p.postid,p.userid,p.postcomcontent,p.postreplyid,p.postcomtime, d.nickname ,u.userlevel ,d.userphoto,d2.nickname ,p2.postname from  post_comment p left join user u on p.userid=u.userid left join post p2 on p2.postid=p.postid left join  detail_inf d on d.userid=p.userid left join post_comment c on c.postcomid=p.postreplyid left join user u2 on u2.userid=c.userid left join detail_inf d2 on d2.userid=u2.userid where p.userid=?  order by p.postcomid desc";
		ResultSet resultSet=this.dbUtils.execQuery(this.conn, strSQL, new Object[]{userid});
		List<Post_Comment> list=new ArrayList<Post_Comment>();
		try {
			while(resultSet.next())
			{
				Post_Comment comment=new Post_Comment();
				comment.setPostComId(resultSet.getInt(1));
				comment.setPostId(resultSet.getInt(2));
				comment.setUserId(resultSet.getInt(3));
				comment.setPostComContent(resultSet.getString(4));
				comment.setPostReplyId(resultSet.getInt(5));
				comment.setPostComTime(resultSet.getDate(6));
				comment.setReleaseUserName(resultSet.getString(7));
				comment.setUserLevel(resultSet.getInt(8));
				comment.setUserPhoto(resultSet.getString(9));
				comment.setReplyUserName(resultSet.getString(10));
				comment.setPostName(resultSet.getString(11));
				list.add(comment);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<Post_Comment> selectByPostId(Post post) {
		// TODO Auto-generated method stub
		int postid=post.getPostId();
		String strSQL = "select p.postcomid,p.postid,p.userid,p.postcomcontent,p.postreplyid,p.postcomtime, d.nickname ,u.userlevel ,d.userphoto,d2.nickname ,p2.postname from  post_comment p left join user u on p.userid=u.userid left join post p2 on p2.postid=p.postid left join  detail_inf d on d.userid=p.userid left join post_comment c on c.postcomid=p.postreplyid left join user u2 on u2.userid=c.userid left join detail_inf d2 on d2.userid=u2.userid    where  p.postid=? and p.postreplyid=0  order by p.postcomid asc";
		ResultSet resultSet=this.dbUtils.execQuery(this.conn, strSQL, new Object[]{postid});
		List<Post_Comment> list=new ArrayList<Post_Comment>();
		try {
			while(resultSet.next())
			{
				Post_Comment comment=new Post_Comment();
				comment.setPostComId(resultSet.getInt(1));
				comment.setPostId(resultSet.getInt(2));
				comment.setUserId(resultSet.getInt(3));
				comment.setPostComContent(resultSet.getString(4));
				comment.setPostReplyId(resultSet.getInt(5));
				comment.setPostComTime(resultSet.getDate(6));
				comment.setReleaseUserName(resultSet.getString(7));
				comment.setUserLevel(resultSet.getInt(8));
				comment.setUserPhoto(resultSet.getString(9));
				comment.setReplyUserName(resultSet.getString(10));
				comment.setPostName(resultSet.getString(11));
				list.add(comment);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<Post_Comment> selectByComId(Post post, int comid) {
		// TODO Auto-generated method stub
		int postid=post.getPostId();
		String strSQL="select p.postcomid,p.postid,p.userid,p.postcomcontent,p.postreplyid,p.postcomtime, d.nickname ,u.userlevel ,d.userphoto,d2.nickname ,p2.postname from  post_comment p left join user u on p.userid=u.userid left join post p2 on p2.postid=p.postid left join  detail_inf d on d.userid=p.userid left join post_comment c on c.postcomid=p.postreplyid left join user u2 on u2.userid=c.userid left join detail_inf d2 on d2.userid=u2.userid    where  p.postid=? and p.postreplyid=?  order by p.postcomid asc";
		ResultSet resultSet=this.dbUtils.execQuery(this.conn, strSQL, new Object[]{postid,comid});
		List<Post_Comment> list=new ArrayList<Post_Comment>();
		try {
			while(resultSet.next())
			{
				Post_Comment comment=new Post_Comment();
				comment.setPostComId(resultSet.getInt(1));
				comment.setPostId(resultSet.getInt(2));
				comment.setUserId(resultSet.getInt(3));
				comment.setPostComContent(resultSet.getString(4));
				comment.setPostReplyId(resultSet.getInt(5));
				comment.setPostComTime(resultSet.getDate(6));
				comment.setReleaseUserName(resultSet.getString(7));
				comment.setUserLevel(resultSet.getInt(8));
				comment.setUserPhoto(resultSet.getString(9));
				comment.setReplyUserName(resultSet.getString(10));
				comment.setPostName(resultSet.getString(11));
				list.add(comment);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
