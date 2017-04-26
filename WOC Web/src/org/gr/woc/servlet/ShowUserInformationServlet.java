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

import org.gr.woc.biz.IOrderBiz;
import org.gr.woc.biz.IUserInfBiz;
import org.gr.woc.biz.impl.OrderBizImpl;
import org.gr.woc.biz.impl.UserInfBizImpl;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Detail_Inf;
import org.gr.woc.po.Order;
import org.gr.woc.po.User;
import org.gr.woc.vo.Friends;

/**
 * Servlet implementation class ShowUserInformation
 */
public class ShowUserInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowUserInformationServlet() {
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
		request.setAttribute("flag", "OK");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		IUserInfBiz userInfBiz = new UserInfBizImpl();
		User userRes = userInfBiz.searchByName(user.getUserName());
		request.setAttribute("userRes", userRes);
		userInfBiz = new UserInfBizImpl();
		Detail_Inf infRes = userInfBiz.searchInfById(user.getUserId());
		request.setAttribute("infRes", infRes);
		List<Friends> lstFriends = userInfBiz.searchFriendInformation(user.getUserId());
		request.setAttribute("lstFriends",lstFriends);
		

		if (TransactionManager.connection == null)
			TransactionManager.connection = new ConnectionManager()
					.openConnection();
		User user1 = (User) session.getAttribute("user");
		List<Order> lstUnfinishedOrdersByUser=new ArrayList<Order>();
		if(user!=null){
			IOrderBiz biz = new OrderBizImpl();
			lstUnfinishedOrdersByUser = biz.searchUnfinshedInfByUserId(user1);
		}
		request.setAttribute("lstUnfinishedOrdersByUser",
				lstUnfinishedOrdersByUser);
		
		String url=Integer.parseInt(request.getParameter("page"))==0?"user_regif.jsp":"user_basif.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/forum/"+url);
		dispatcher.forward(request, response);
	}

}
