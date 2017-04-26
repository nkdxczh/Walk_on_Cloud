package org.gr.woc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gr.woc.biz.IChatBiz;
import org.gr.woc.biz.impl.ChatBizImpl;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Chat;
import org.gr.woc.po.User;

/**
 * Servlet implementation class AddChatServlet
 * 这是增加用户之间通讯的消息的servlet
 */
public class AddChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddChatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		if(TransactionManager.connection==null)
			TransactionManager.connection=new ConnectionManager().openConnection();
		IChatBiz biz=new ChatBizImpl();
		String content=request.getParameter("content");
		//聊天内容
		User user=(User)request.getSession().getAttribute("user");
		int senderid=user.getUserId();
		//发送者ID
		int receiverid=Integer.parseInt(request.getParameter("receiverid"));
		//接受者ID
		Chat chat=new Chat();
		chat.setChatContent(content);
		chat.setReceiverId(receiverid);
		chat.setSenderId(senderid);
		biz.add(chat);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/forum/forindex.jsp");
		dispatcher.forward(request, response);
		//由于我不清楚改往哪里跳转，所有servlet中我每次跳转都是往forindex跳，你自己改一下
		
	}

}
