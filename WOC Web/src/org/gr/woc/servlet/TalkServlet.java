package org.gr.woc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gr.woc.biz.impl.UserInfBizImpl;
import org.gr.woc.po.Detail_Inf;
import org.gr.woc.po.User;
import org.gr.woc.tuisong.Test;

/**
 * Servlet implementation class TalkServlet
 */
public class TalkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TalkServlet() {
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
		ServletContext application= this.getServletContext();
		
	
		int aimid=Integer.parseInt((request.getParameter("aimId")));
		User user=(User)request.getSession().getAttribute("user");
		Detail_Inf inf=(Detail_Inf)request.getSession().getAttribute("inf");
		Test test=new Test();
		test.sendMessageAuto(String.valueOf(aimid),
				"<a href='/WOC/TalkServlet?aimId="+user.getUserId()+"'>"+inf.getNickName()+"向您发起了聊天邀请"+"</a>");
		UserInfBizImpl bizImpl=new UserInfBizImpl();
		String younickname=bizImpl.searchInfById(aimid).getNickName();
		request.getSession().setAttribute("younickname", younickname);
		String w="words"+user.getUserId()+aimid;
		String s="";
		application.setAttribute(w, s);
		w="words"+aimid+user.getUserId();
		application.setAttribute(w, s);
		boolean ifrefresh=false;
		application.setAttribute("ifrefresh",ifrefresh);
		request.getSession().setAttribute("myuserid", user.getUserId());
		request.getSession().setAttribute("youuserid", aimid);
		
		request.getSession().setAttribute("nickname", inf.getNickName());
		boolean boo=false;
		request.getSession().setAttribute("bool", boo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Talkindex.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
