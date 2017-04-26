package org.gr.woc.biz.impl;

import java.util.List;

import org.gr.woc.biz.ICommentBiz;
import org.gr.woc.dao.IPost_CommentDao;
import org.gr.woc.dao.impl.Post_CommentDaoImpl;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Post;
import org.gr.woc.po.Post_Comment;
import org.gr.woc.po.User;

public class CommentBizImpl implements ICommentBiz {

	IPost_CommentDao dao=null;
	
	public CommentBizImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean releaseComment(Post_Comment comment) {
		// TODO Auto-generated method stub
		dao=new Post_CommentDaoImpl(TransactionManager.connection);
		if(dao.insert(comment)>0)
			return true;
		else
			return false;
	}

	@Override
	public boolean cancelComment(Post_Comment comment) {
		// TODO Auto-generated method stub
		dao=new Post_CommentDaoImpl(TransactionManager.connection);
		if(dao.delete(comment)>0)
			return true;
		else
			return false;
	}

	@Override
	public List<Post_Comment> searchInfByUserId(User user) {
		// TODO Auto-generated method stub
		dao=new Post_CommentDaoImpl(TransactionManager.connection);
		
		return dao.selectByUserId(user);
		
	}

	@Override
	public List<Post_Comment> searchInfByPostId(Post post) {
		// TODO Auto-generated method stub
		dao=new Post_CommentDaoImpl(TransactionManager.connection);
		return dao.selectByPostId(post);
		
	}

	@Override
	public void infSearchByLayerId(Post post, int postcomid,List<Post_Comment> lst) {
		dao=new Post_CommentDaoImpl(TransactionManager.connection);
		// TODO Auto-generated method stub
		List<Post_Comment> commentWithLayer=dao.selectByComId(post, postcomid);
		lst.addAll(commentWithLayer);
		for(int i=0;i<commentWithLayer.size();i++)
		{
			int comid=commentWithLayer.get(i).getPostComId();
			infSearchByLayerId(post, comid, lst);
		}
	}
}

