package org.gr.woc.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.gr.woc.dao.IChatDao;
import org.gr.woc.db.DBUtils;
import org.gr.woc.po.Chat;
import org.gr.woc.po.Post_Type;
import org.gr.woc.po.User;

public class ChatDaoImpl implements IChatDao {

	private Connection conn = null;
	private DBUtils dbUtils = null;
	public ChatDaoImpl(Connection conn) {
		super();
		this.dbUtils = new DBUtils();
		this.conn = conn;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int insert(Chat chat) {
		// TODO Auto-generated method stub
		int senderid=chat.getSenderId();
		int receid=chat.getReceiverId();
		String content=chat.getChatContent();
		String strSQL = "insert into `chat` values(null,?,?,?)";
		return this.dbUtils.execOthers(this.conn, strSQL, new Object[]{senderid,receid,content});
	}

	@Override
	public List<Chat> selectByUserId(User user,User user2) {
		// TODO Auto-generated method stub
		int sender=user.getUserId();
		int reciever=user2.getUserId();
		String strSQL = "select chatid,senderid,receiverid,chatcontent,u1.username,u2.username from chat ,user u1,user u2 where senderid=? and receiverid=? and u1.userid=senderid and u2.userid=receiverid   order by chatid desc";
		List<Chat> list=new ArrayList<Chat>();
		ResultSet resultSet= this.dbUtils.execQuery(this.conn, strSQL, new Object[]{sender,reciever});
		try {
			while(resultSet.next())
			{
				Chat chat=new Chat();
				chat.setChatId(resultSet.getInt(1));
				chat.setSenderId(resultSet.getInt(2));
				chat.setReceiverId(resultSet.getInt(3));
				chat.setChatContent(resultSet.getString(4));
				chat.setSenderName(resultSet.getString(5));
				chat.setReceiverName(resultSet.getString(6));
				list.add(chat);	
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
