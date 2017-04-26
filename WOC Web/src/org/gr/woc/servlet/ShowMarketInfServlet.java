package org.gr.woc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.gr.woc.biz.IOrderBiz;
import org.gr.woc.biz.impl.OrderBizImpl;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Order;
import org.gr.woc.po.User;
/**
 * Servlet implementation class ShowMarketInfServlet
 */
public class ShowMarketInfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowMarketInfServlet() {
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
		if (TransactionManager.connection == null)
			TransactionManager.connection = new ConnectionManager()
					.openConnection();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		IOrderBiz biz = new OrderBizImpl();
		List<Order> lstFinishedOrdersByUser = biz.searchFinshedInfByUserId(user);
		request.setAttribute("lstFinishedOrdersByUser", lstFinishedOrdersByUser);
		List<Order> lstUnfinishedOrdersByUser = biz.searchUnfinshedInfByUserId(user);
		request.setAttribute("lstUnfinishedOrdersByUser",
				lstUnfinishedOrdersByUser);
		System.out.println(lstUnfinishedOrdersByUser);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("ShowUserCommoditiesServlet");
		dispatcher.forward(request, response);
	}

}
