package org.gr.woc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.gr.woc.dao.IPostDao;
import org.gr.woc.db.DBUtils;
import org.gr.woc.po.Post;
import org.gr.woc.po.User;



public class PostDaoImpl implements IPostDao {

	private Connection conn = null;
	private DBUtils dbUtils = null;
	
	public PostDaoImpl(Connection conn) {
		super();
		// TODO Auto-generated constructor stub
		this.dbUtils = new DBUtils();
		this.conn = conn;
	}

	@Override
	public int update(Post post) {
		// TODO Auto-generated method stub
		int postid=post.getPostId();
		String strSQL = "update  `post` set lastcomtime=now()    where postid=?";
		return this.dbUtils.execOthers(this.conn, strSQL, new Object[]{postid});
		
	}

	@Override
	public int delete(Post post) {
		// TODO Auto-generated method stub
		int postid=post.getPostId();
	
		String strSQL="delete from post_comment where postid=?";
		int b=this.dbUtils.execOthers(this.conn, strSQL, new Object[]{postid});
		strSQL="delete from post_resource where postid=?";
		int c=this.dbUtils.execOthers(this.conn, strSQL, new Object[]{postid});
		strSQL="delete from post_score where postid=?";
		int d=this.dbUtils.execOthers(this.conn, strSQL, new Object[]{postid});
		 strSQL = "delete from  `post` where postid=?";
		int a= this.dbUtils.execOthers(this.conn, strSQL, new Object[]{postid});
		return a+b+c+d;
	}

	@Override
	public int insert(Post post) {
		// TODO Auto-generated method stub
		int userid=post.getUserId();
		int property=post.getPostProperty();
		int enternum=post.getPostEnterNum();
		double score=post.getPostScore();
		int scorecount=post.getScoreCount();
		String postname=post.getPostName();
		String strSQL = "insert into `post` values(null,?,?,?,?,now(),?,?,now())";
		return this.dbUtils.execOthers(this.conn, strSQL, new Object[]{userid,postname,property,enternum,score,scorecount});
	}

	@Override
	public List<Post> infSearchByUserId(User user) {
		// TODO Auto-generated method stub
		int userid=user.getUserId();
		String strSQL = "select *,count(p.postid) from  post p left join post_resource r  on p.postid = r.postid left join user u on u.userid = p.userid left join post_type t on t.posttypeid=p.postproperty left join detail_inf d on d.userid=u.userid left join post_comment c on c.postid=p.postid where p.userid=?  group by p.postid order by p.postid desc";
		ResultSet resultSet= this.dbUtils.execQuery(this.conn, strSQL, new Object[]{userid});
		List<Post> list=new ArrayList<Post>();
		try {
			while (resultSet.next()) {
				Post post=new Post();

				post.setPostId(resultSet.getInt(1));
				post.setPostName(resultSet.getString(3));
				post.setUserId(resultSet.getInt(2));
				post.setPostProperty(resultSet.getInt(4));
				post.setPostEnterNum(resultSet.getInt(5));
				post.setPostTime(resultSet.getDate(6));
				post.setPostScore(resultSet.getDouble(7));
				post.setScoreCount(resultSet.getInt(8));
				post.setLastComTime(resultSet.getDate(9));
				post.setResId(resultSet.getInt(10));
				post.setResPath(resultSet.getString(12));
				post.setUserName(resultSet.getString(14));
				post.setUserLevel(resultSet.getInt(16));
				post.setUserStatus(resultSet.getInt(17));
				post.setPropertyName(resultSet.getString(19));
				post.setLocation(resultSet.getString(22));
				post.setPhone(resultSet.getString(23));
				post.setPostCode(resultSet.getInt(24));
				post.setEmail(resultSet.getString(25));
				post.setGender(resultSet.getString(26));
				post.setHobby(resultSet.getString(27));
				post.setMajor(resultSet.getString(28));
				post.setScore(resultSet.getInt(29));
				post.setNickName(resultSet.getString(30));
				post.setUserRegion(resultSet.getString(31));
				post.setUserPhoto(resultSet.getString(32));
				post.setComCount(resultSet.getInt(39));
				list.add(post);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public int expressLike(Post post,int para) {
		// TODO Auto-generated method stub
		int postid=post.getPostId();
		int count=post.getScoreCount();
		double score=post.getPostScore();
		score+=para;
		count++;
		String strSQL = "update post set postscore=?,scorecount=? where postid=?";
		return this.dbUtils.execOthers(this.conn, strSQL, new Object[]{score,count,postid});	
	}

	@Override
	public List<Post> refreshResult() {
		// TODO Auto-generated method stub
		String strSQL = "select *,count(p.postid) from  post p left join post_resource r  on p.postid = r.postid left join user u on u.userid = p.userid left join post_type t on t.posttypeid=p.postproperty left join detail_inf d on d.userid=u.userid left join post_comment c on c.postid=p.postid   group by p.postid order by p.lastcomtime desc";
		ResultSet resultSet= this.dbUtils.execQuery(this.conn, strSQL, new Object[]{});
		List<Post> list=new ArrayList<Post>();
		try {
			while (resultSet.next()) {
				Post post=new Post();
				post.setPostId(resultSet.getInt(1));
				post.setPostName(resultSet.getString(3));
				post.setUserId(resultSet.getInt(2));
				post.setPostProperty(resultSet.getInt(4));
				post.setPostEnterNum(resultSet.getInt(5));
				post.setPostTime(resultSet.getDate(6));
				post.setPostScore(resultSet.getDouble(7));
				post.setScoreCount(resultSet.getInt(8));
				post.setLastComTime(resultSet.getDate(9));
				post.setResId(resultSet.getInt(10));
				post.setResPath(resultSet.getString(12));
				post.setUserName(resultSet.getString(14));
				post.setUserLevel(resultSet.getInt(16));
				post.setUserStatus(resultSet.getInt(17));
				post.setPropertyName(resultSet.getString(19));
				post.setLocation(resultSet.getString(22));
				post.setPhone(resultSet.getString(23));
				post.setPostCode(resultSet.getInt(24));
				post.setEmail(resultSet.getString(25));
				post.setGender(resultSet.getString(26));
				post.setHobby(resultSet.getString(27));
				post.setMajor(resultSet.getString(28));
				post.setScore(resultSet.getInt(29));
				post.setNickName(resultSet.getString(30));
				post.setUserRegion(resultSet.getString(31));
				post.setUserPhoto(resultSet.getString(32));
				post.setComCount(resultSet.getInt(39));
				list.add(post);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Post> hotPosts() {//当日热门帖子，按照回复数排序
		// TODO Auto-generated method stub
		String strSQL = "select *,count(p.postid) from  post p left join post_resource r  on p.postid = r.postid left join user u on u.userid = p.userid left join post_type t on t.posttypeid=p.postproperty left join detail_inf d on d.userid=u.userid left join post_comment c on c.postid=p.postid where day(p.posttime)=day(now()) group by p.postid order by count(p.postid) desc";
		ResultSet resultSet= this.dbUtils.execQuery(this.conn, strSQL, new Object[]{});
		List<Post> list=new ArrayList<Post>();
		try {
			while (resultSet.next()) {
				Post post=new Post();
				post.setPostId(resultSet.getInt(1));
				post.setPostName(resultSet.getString(3));
				post.setUserId(resultSet.getInt(2));
				post.setPostProperty(resultSet.getInt(4));
				post.setPostEnterNum(resultSet.getInt(5));
				post.setPostTime(resultSet.getDate(6));
				post.setPostScore(resultSet.getDouble(7));
				post.setScoreCount(resultSet.getInt(8));
				post.setLastComTime(resultSet.getDate(9));
				post.setResId(resultSet.getInt(10));
				post.setResPath(resultSet.getString(12));
				post.setUserName(resultSet.getString(14));
				post.setUserLevel(resultSet.getInt(16));
				post.setUserStatus(resultSet.getInt(17));
				post.setPropertyName(resultSet.getString(19));
				post.setLocation(resultSet.getString(22));
				post.setPhone(resultSet.getString(23));
				post.setPostCode(resultSet.getInt(24));
				post.setEmail(resultSet.getString(25));
				post.setGender(resultSet.getString(26));
				post.setHobby(resultSet.getString(27));
				post.setMajor(resultSet.getString(28));
				post.setScore(resultSet.getInt(29));
				post.setNickName(resultSet.getString(30));
				post.setUserRegion(resultSet.getString(31));
				post.setUserPhoto(resultSet.getString(32));
				post.setComCount(resultSet.getInt(39));
				list.add(post);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Post> latastPosts(int bankuaiid) {//按照版块号
		// TODO Auto-generated method stub
		int low=(bankuaiid/100)*100;
		int high=low+100;
		String strSQL = "select *,count(p.postid) from  post p left join post_resource r  on p.postid = r.postid left join user u on u.userid = p.userid left join post_type t on t.posttypeid=p.postproperty left join detail_inf d on d.userid=u.userid left join post_comment c on c.postid=p.postid where  p.postProperty>? and p.postProperty<? group by p.postid order by p.lastcomtime desc";
		ResultSet resultSet= this.dbUtils.execQuery(this.conn, strSQL, new Object[]{low,high});
		List<Post> list=new ArrayList<Post>();
		try {
			while (resultSet.next()) {
				Post post=new Post();
				post.setPostId(resultSet.getInt(1));
				post.setPostName(resultSet.getString(3));
				post.setUserId(resultSet.getInt(2));
				post.setPostProperty(resultSet.getInt(4));
				post.setPostEnterNum(resultSet.getInt(5));
				post.setPostTime(resultSet.getDate(6));
				post.setPostScore(resultSet.getDouble(7));
				post.setScoreCount(resultSet.getInt(8));
				post.setLastComTime(resultSet.getDate(9));
				post.setResId(resultSet.getInt(10));
				post.setResPath(resultSet.getString(12));
				post.setUserName(resultSet.getString(14));
				post.setUserLevel(resultSet.getInt(16));
				post.setUserStatus(resultSet.getInt(17));
				post.setPropertyName(resultSet.getString(19));
				post.setLocation(resultSet.getString(22));
				post.setPhone(resultSet.getString(23));
				post.setPostCode(resultSet.getInt(24));
				post.setEmail(resultSet.getString(25));
				post.setGender(resultSet.getString(26));
				post.setHobby(resultSet.getString(27));
				post.setMajor(resultSet.getString(28));
				post.setScore(resultSet.getInt(29));
				post.setNickName(resultSet.getString(30));
				post.setUserRegion(resultSet.getString(31));
				post.setUserPhoto(resultSet.getString(32));
				post.setComCount(resultSet.getInt(39));
				list.add(post);

			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Post> relatedPosts(int bankuaiid) {
		// TODO Auto-generated method stub
		String strSQL = "select *,count(p.postid) from  post p left join post_resource r  on p.postid = r.postid left join user u on u.userid = p.userid left join post_type t on t.posttypeid=p.postproperty left join detail_inf d on d.userid=u.userid left join post_comment c on c.postid=p.postid where  p.postProperty=? group by p.postid order by count(p.postid) desc";
		ResultSet resultSet= this.dbUtils.execQuery(this.conn, strSQL, new Object[]{bankuaiid});
		List<Post> list=new ArrayList<Post>();
		try {
			while (resultSet.next()) {
				Post post=new Post();
				post.setPostId(resultSet.getInt(1));
				post.setPostName(resultSet.getString(3));
				post.setUserId(resultSet.getInt(2));
				post.setPostProperty(resultSet.getInt(4));
				post.setPostEnterNum(resultSet.getInt(5));
				post.setPostTime(resultSet.getDate(6));
				post.setPostScore(resultSet.getDouble(7));
				post.setScoreCount(resultSet.getInt(8));
				post.setLastComTime(resultSet.getDate(9));
				post.setResId(resultSet.getInt(10));
				post.setResPath(resultSet.getString(12));
				post.setUserName(resultSet.getString(14));
				post.setUserLevel(resultSet.getInt(16));
				post.setUserStatus(resultSet.getInt(17));
				post.setPropertyName(resultSet.getString(19));
				post.setLocation(resultSet.getString(22));
				post.setPhone(resultSet.getString(23));
				post.setPostCode(resultSet.getInt(24));
				post.setEmail(resultSet.getString(25));
				post.setGender(resultSet.getString(26));
				post.setHobby(resultSet.getString(27));
				post.setMajor(resultSet.getString(28));
				post.setScore(resultSet.getInt(29));
				post.setNickName(resultSet.getString(30));
				post.setUserRegion(resultSet.getString(31));
				post.setUserPhoto(resultSet.getString(32));
				post.setComCount(resultSet.getInt(39));
				list.add(post);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int updateComTime(Post post) {
		// TODO Auto-generated method stub
		int postid=post.getPostId();
		String strSQL = "update post set lastcomtime=now() where postid=?";
		return this.dbUtils.execOthers(this.conn, strSQL, new Object[]{postid});	
	}

	@Override
	public List<Post> hotPostsBybankuai(int bankuaiid) {//每个版块的热帖
		// TODO Auto-generated method stub
		int low=(bankuaiid/100)*100;
		int high=low+100;
		String strSQL = "select *,count(p.postid) from  post p left join post_resource r  on p.postid = r.postid left join user u on u.userid = p.userid left join post_type t on t.posttypeid=p.postproperty left join detail_inf d on d.userid=u.userid left join post_comment c on c.postid=p.postid where  p.postProperty>? and p.postProperty<? and day(p.posttime)=day(now()) group by p.postid order by count(p.postid) desc";
		ResultSet resultSet= this.dbUtils.execQuery(this.conn, strSQL, new Object[]{low,high});
		List<Post> list=new ArrayList<Post>();
		try {
			while (resultSet.next()) {
				Post post=new Post();
				post.setPostId(resultSet.getInt(1));
				post.setPostName(resultSet.getString(3));
				post.setUserId(resultSet.getInt(2));
				post.setPostProperty(resultSet.getInt(4));
				post.setPostEnterNum(resultSet.getInt(5));
				post.setPostTime(resultSet.getDate(6));
				post.setPostScore(resultSet.getDouble(7));
				post.setScoreCount(resultSet.getInt(8));
				post.setLastComTime(resultSet.getDate(9));
				post.setResId(resultSet.getInt(10));
				post.setResPath(resultSet.getString(12));
				post.setUserName(resultSet.getString(14));
				post.setUserLevel(resultSet.getInt(16));
				post.setUserStatus(resultSet.getInt(17));
				post.setPropertyName(resultSet.getString(19));
				post.setLocation(resultSet.getString(22));
				post.setPhone(resultSet.getString(23));
				post.setPostCode(resultSet.getInt(24));
				post.setEmail(resultSet.getString(25));
				post.setGender(resultSet.getString(26));
				post.setHobby(resultSet.getString(27));
				post.setMajor(resultSet.getString(28));
				post.setScore(resultSet.getInt(29));
				post.setNickName(resultSet.getString(30));
				post.setUserRegion(resultSet.getString(31));
				post.setUserPhoto(resultSet.getString(32));
				post.setComCount(resultSet.getInt(39));
				list.add(post);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ResultSet selectById(Post post) {
		// TODO Auto-generated method stub
		int postid=post.getPostId();
		String strSQL = "select *,count(p.postid) from  post p left join post_resource r  on p.postid = r.postid left join user u on u.userid = p.userid left join post_type t on t.posttypeid=p.postproperty left join detail_inf d on d.userid=u.userid left join post_comment c on c.postid=p.postid where p.postid=?  group by p.postid order by p.postid desc";
		return this.dbUtils.execQuery(this.conn, strSQL, new Object[]{postid});	
		
	}

	@Override
	public List<Post> selectPosts(String word) {
		// TODO Auto-generated method stub
		String sword='%'+word+'%';
		String strSQL="select p.postid,p.postname,p.posttime,u.username from post p,user u where p.userid=u.userid and p.postname like ?";
		List<Post> list=new ArrayList<Post>();
		ResultSet resultSet= this.dbUtils.execQuery(this.conn, strSQL, new Object[]{sword});
		try {
			while (resultSet.next())
			{
				Post post=new Post();
				post.setPostId(resultSet.getInt(1));
				post.setPostName(resultSet.getString(2));
				post.setPostTime(resultSet.getDate(3));
				post.setUserName(resultSet.getString(4));
				list.add(post);
				
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int selectLatest() {
		// TODO Auto-generated method stub
		String strSQL="select postid from post order by postid desc";
		ResultSet resultSet= this.dbUtils.execQuery(this.conn, strSQL, new Object[]{});
		try {
			if(resultSet.next())
				return resultSet.getInt(1);
			else 
				return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

}
