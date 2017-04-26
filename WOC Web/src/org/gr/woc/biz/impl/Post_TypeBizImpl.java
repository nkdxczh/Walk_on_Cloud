package org.gr.woc.biz.impl;

import java.util.List;

import org.gr.woc.biz.IPost_TypeBiz;
import org.gr.woc.dao.IPost_TypeDao;
import org.gr.woc.dao.impl.Post_TypeDaoImpl;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Post_Type;

public class Post_TypeBizImpl implements IPost_TypeBiz {

	IPost_TypeDao dao=null;
	
	public Post_TypeBizImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Post_Type searchById(Post_Type post_Type) {
		// TODO Auto-generated method stub
		dao=new Post_TypeDaoImpl(TransactionManager.connection);
		return dao.selectById(post_Type);
	}

	@Override
	public List<Post_Type> searchAll() {
		// TODO Auto-generated method stub
		dao=new Post_TypeDaoImpl(TransactionManager.connection);
		return dao.selectAll();
	}

}
