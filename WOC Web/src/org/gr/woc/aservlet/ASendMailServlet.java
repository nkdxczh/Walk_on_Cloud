package org.gr.woc.aservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.gr.woc.biz.impl.RandomString;
import org.gr.woc.biz.impl.SendMail;
import org.gr.woc.biz.impl.UserInfBizImpl;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Detail_Inf;
import org.gr.woc.po.User;

/**
 * Servlet implementation class SendMailServlet
 */
public class ASendMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ASendMailServlet() {
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
		 SendMail SEmail = new SendMail();
		 User user=(User)request.getSession().getAttribute("user");
		 if(TransactionManager.connection==null)
				TransactionManager.connection=new ConnectionManager().openConnection();
		 UserInfBizImpl bizImpl=new UserInfBizImpl();
		 Detail_Inf detail=bizImpl.searchInfById(user.getUserId());
		 String receiveaddress=detail.getEmail();
		 String authcode=RandomString.randomString(5);
		 String content="这是由【漫步云端】[WOC]自动发送的验证邮件 \n"+
		 "您即将修改密码，如果确认是本人操作，请输入以下验证码："+authcode+"\n"+
		 "若不是您本人进行的操作，请尽快修改密码 \n"+"请勿回复此邮件";
		 SEmail.SendEmailTest(receiveaddress, content);
	}

}
