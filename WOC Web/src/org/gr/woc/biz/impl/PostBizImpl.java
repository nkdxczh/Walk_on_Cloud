package org.gr.woc.biz.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.gr.woc.biz.IPostBiz;
import org.gr.woc.dao.IPostDao;
import org.gr.woc.dao.IScore_RecordDao;
import org.gr.woc.dao.impl.PostDaoImpl;
import org.gr.woc.dao.impl.Score_RecordDaoImpl;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Post;
import org.gr.woc.po.Score_Record;
import org.gr.woc.po.User;

public class PostBizImpl implements IPostBiz {

	private IPostDao postDao=null;
	private IScore_RecordDao dao=null;
	public PostBizImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean releasePost(Post post) {
		// TODO Auto-generated method stub
		postDao=new PostDaoImpl(TransactionManager.connection);
		if(postDao.insert(post)>0)
			return true;
		else
			return false;
	}

	@Override
	public boolean cancelPost(Post post) {
		// TODO Auto-generated method stub
		postDao=new PostDaoImpl(TransactionManager.connection);
		if(postDao.delete(post)>0)
			return true;
		else
			return false;	
	}

	@Override
	public boolean expressLike(Post post, int para, int userid) {
		// TODO Auto-generated method stub
		postDao=new PostDaoImpl(TransactionManager.connection);
		dao=new Score_RecordDaoImpl(TransactionManager.connection);
		Score_Record record=new Score_Record();
		record.setPostId(post.getPostId());
		record.setUserId(userid);
		if(dao.insert(record)>0)
			{postDao.expressLike(post, para);
			return true;
			}
		else
			return false;
	}

	@Override
	public List<Post> refreshResult() {
		// TODO Auto-generated method stub
		postDao=new PostDaoImpl(TransactionManager.connection);
		List<Post> list=new ArrayList<Post>();
		return postDao.refreshResult();
		
	}

	@Override
	public List<Post> latastPosts(int bankuaihao) {
		// TODO Auto-generated method stub
		postDao=new PostDaoImpl(TransactionManager.connection);
		List<Post> list=new ArrayList<Post>();
		return postDao.latastPosts(bankuaihao);
		
	}

	@Override
	public List<Post> relatedPosts(int bankuaihao) {
		// TODO Auto-generated method stub
		postDao=new PostDaoImpl(TransactionManager.connection);
		List<Post> list=new ArrayList<Post>();
		return postDao.relatedPosts(bankuaihao);
	
	}

	@Override
	public List<Post> hotPosts() {
		// TODO Auto-generated method stub
		postDao=new PostDaoImpl(TransactionManager.connection);
		List<Post> list=new ArrayList<Post>();
		return postDao.hotPosts();
		
	}

	@Override
	public List<Post> hotPostsBybankuai(int bankuaihao) {
		// TODO Auto-generated method stub
		postDao=new PostDaoImpl(TransactionManager.connection);
		List<Post> list=new ArrayList<Post>();
		return postDao.hotPostsBybankuai(bankuaihao);
		
	}

	@Override
	public List<Post> searchInfByUserId(User user) {
		// TODO Auto-generated method stub
		postDao=new PostDaoImpl(TransactionManager.connection);
		List<Post> list=new ArrayList<Post>();
		return postDao.infSearchByUserId(user);
		
	}

	@Override
	public Post searchInfById(Post post2) {
		// TODO Auto-generated method stub
		postDao=new PostDaoImpl(TransactionManager.connection);
		ResultSet resultSet=postDao.selectById(post2);
		Post post=new Post();
		try {
			if(resultSet.next()) {
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
				
				return post;
			}
			else
				return null;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public List<Post> searchInfResult(String word) {
		// TODO Auto-generated method stub
		postDao=new PostDaoImpl(TransactionManager.connection);
		return postDao.selectPosts(word);
	}

	@Override
	public int searchLatest() {
		// TODO Auto-generated method stub
		postDao=new PostDaoImpl(TransactionManager.connection);
		return postDao.selectLatest();
	}

}
