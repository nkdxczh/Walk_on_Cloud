package org.gr.woc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.gr.woc.biz.ILoginBiz;
import org.gr.woc.biz.IUserInfBiz;
import org.gr.woc.biz.impl.LoginBizImpl;
import org.gr.woc.biz.impl.UserInfBizImpl;
import org.gr.woc.dao.IDetail_InfoDao;
import org.gr.woc.listener.UserSessionListener;
import org.gr.woc.po.Detail_Inf;
import org.gr.woc.po.Detail_Info;
import org.gr.woc.po.User;
import org.gr.woc.utils.MD5String;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		IUserInfBiz infoBiz=new UserInfBizImpl();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String username = request.getParameter("log");
		String password = new MD5String().getMD5(request.getParameter("pwd"));
		User user = new UserInfBizImpl().searchByName(username);
		if (user == null) {
			request.setAttribute("errorInfo", "name");
			response.getWriter().write("usernameError");
			return;
		}
		boolean pwdflag = user.getUserPassword().equals(password);
		if (!pwdflag) {
			request.setAttribute("errorInfo", "password");
			response.getWriter().write("passwordError");
			return;
		}
		String nickname=infoBiz.searchInfById(user.getUserId()).getNickName();
		int score=infoBiz.searchInfById(user.getUserId()).getScore();
		HttpSession session = request.getSession();
		Detail_Inf inf=new Detail_Inf();
		inf.setNickName(nickname);
		inf.setScore(score);
		if(UserSessionListener.sessionMap.get(username)!=null){
			HttpSession oldsession=(HttpSession) UserSessionListener.sessionMap.get(username);
			oldsession.invalidate();
			UserSessionListener.sessionMap.remove(username);
			UserSessionListener.sessionMap.put(username, session);
			UserSessionListener.sessionMap.remove(session.getId());
			session.setAttribute("user", user);
			session.setAttribute("inf", inf);
		}
		else{
			UserSessionListener.sessionMap.get(session.getId());
			UserSessionListener.sessionMap.put(username, UserSessionListener.sessionMap.get(session.getId()));
			UserSessionListener.sessionMap.remove(session.getId());
			session.setAttribute("user", user);
			session.setAttribute("inf", inf);
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/WelcomePage/index.jsp");
		dispatcher.forward(request, response);
	}

}
