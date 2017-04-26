package org.gr.woc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.gr.woc.biz.IUserInfBiz;
import org.gr.woc.biz.impl.UserInfBizImpl;
import org.gr.woc.po.User;

/**
 * Servlet implementation class ApplyAttenttionSevlet
 */
public class ApplyAttenttionSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApplyAttenttionSevlet() {
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
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int comId = Integer.parseInt(request.getParameter("comId"));
		String url = "jsp/Market/Store.jsp";

		IUserInfBiz userInfBiz = new UserInfBizImpl();
		boolean flag=false;
		System.out.println(comId);
		System.out.println(user.getUserId());
		if(user!=null)flag = userInfBiz.applyAttention(user.getUserId(), comId);
		request.setAttribute("tip", flag);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
