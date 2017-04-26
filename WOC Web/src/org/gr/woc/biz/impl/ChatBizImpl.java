package org.gr.woc.biz.impl;

import java.util.List;

import org.gr.woc.biz.IChatBiz;
import org.gr.woc.dao.IChatDao;
import org.gr.woc.dao.impl.ChatDaoImpl;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Chat;
import org.gr.woc.po.User;

public class ChatBizImpl implements IChatBiz {

	IChatDao chatDao=null;
	
	public ChatBizImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean add(Chat chat) {
		// TODO Auto-generated method stub
		chatDao=new ChatDaoImpl(TransactionManager.connection);
		if(chatDao.insert(chat)>0)
			return true;
		else
			return false;
		
	}

	@Override
	public List<Chat> searchByUserId(User user,User user2) {
		// TODO Auto-generated method stub
		chatDao=new ChatDaoImpl(TransactionManager.connection);
		return chatDao.selectByUserId(user,user2);
	}

}
