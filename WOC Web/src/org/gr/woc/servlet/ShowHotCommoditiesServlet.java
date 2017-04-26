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

import org.gr.woc.biz.ICommodityBiz;
import org.gr.woc.biz.IOrderBiz;
import org.gr.woc.biz.impl.CommodityBizImpl;
import org.gr.woc.biz.impl.OrderBizImpl;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Order;
import org.gr.woc.po.User;
import org.gr.woc.utils.SendByUserCookie;
import org.gr.woc.vo.Commodities;

/**
 * Servlet implementation class ShowHotCommoditiesServlet
 */
public class ShowHotCommoditiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowHotCommoditiesServlet() {
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
		ICommodityBiz commodityBiz = new CommodityBizImpl();
		List<Commodities> lstCommodities = new ArrayList<Commodities>();
		lstCommodities=commodityBiz.searchHotCommodities(0);
		// int sort=Integer.parseInt(request.getParameter("sort"));
		int sort=1;
		String order="";
		switch (sort) {
		case 1:
			order = "releaseTime";
			break;
		case 2:
			order = "price";
			break;
		case 3:
			order = "requiredSorce";
			break;
		case 4:
			order = "comFocus";
			break;
		default:
			break;
		}
		List<Commodities> lstCommoditiesInf = new ArrayList<Commodities>();
		int index=1;int pageSize=6;
		lstCommoditiesInf=commodityBiz.searchByComName(new String(""), 0, index, pageSize, order);;
		request.setAttribute("lstCommoditiesInf",lstCommoditiesInf);
		int key=Integer.parseInt(request.getParameter("key"));
		lstCommodities=commodityBiz.searchHotCommodities(key);
		request.setAttribute("lstCommodities",lstCommodities);
		
		if (TransactionManager.connection == null)
			TransactionManager.connection = new ConnectionManager()
					.openConnection();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<Order> lstUnfinishedOrdersByUser=new ArrayList<Order>();
		if(user!=null){
			IOrderBiz biz = new OrderBizImpl();
			lstUnfinishedOrdersByUser = biz.searchUnfinshedInfByUserId(user);
		}
		request.setAttribute("lstUnfinishedOrdersByUser",
				lstUnfinishedOrdersByUser);
		
		
		String url="";
		// 跳转？
		switch(key){
		case 1:
			url="jsp/Market/Store.jsp";
			break;
		case 2:
			url="jsp/Market/TStore.jsp";
			break;
		case 3:
			url="jsp/Market/EStore.jsp";
			break;
		case 4:
			url="jsp/Market/FStore.jsp";
			break;
			default:break;
		}
		SendByUserCookie userLike=new SendByUserCookie(commodityBiz);
		List<Commodities>lstLike=null;
		if(user!=null){
			lstLike=userLike.findUserLike(user.getUserName(), request);
		}
		request.setAttribute("lstLike", lstLike);
		request.setAttribute("flag","OK");
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
