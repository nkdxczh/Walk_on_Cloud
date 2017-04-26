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
import org.gr.woc.vo.Commodities;

/**
 * Servlet implementation class SearchCommoditiesListServlet
 */
public class SearchCommoditiesListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchCommoditiesListServlet() {
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
		int operation = Integer.parseInt(new String(request.getParameter("key")
				.getBytes("ISO-8859-1"), "utf-8"));
		int key1=operation;
		System.out.println("1111111111111111111111key1 is:"+key1);
		int index = Integer.parseInt(request.getParameter("pageNumber"));
		int sort = Integer.parseInt(new String(request.getParameter("sort")
				.getBytes("ISO-8859-1"), "utf-8"));
		int jsp = Integer.parseInt(request.getParameter("jsp"));
		String order = "";
		int pageSize = 6;
		List<Commodities> lstCommodities = new ArrayList<Commodities>();
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
		System.out.println("key1 is:"+key1);

		int key = 0;
		if (operation == 0) {
			operation = 0;
			key=0;
		}
		if (0 < operation) {
			if (operation <= 12) {
				key = operation;
				operation = 1;
			} else {
				if (operation <= 21) {
					key = operation - 12;
					operation = 2;
				} else {
					if (operation <= 30) {
						key = operation - 21;
						operation = 3;
					} else {
						if (operation <= 36) {
							key = operation - 30;
							operation = 4;
						}
					}
				}
			}
		}
		System.out.println("000000000000000000000key is:"+key);
		String url = "";
		switch (operation) {
		case 0:{
			lstCommodities = commodityBiz.searchCommoditiesOrderByTime(key, index, pageSize);
		}
		case 1: {
			lstCommodities = commodityBiz.searchByComType(key, index, pageSize,
					order);
			break;
		}
		case 2: {
			lstCommodities = commodityBiz.searchByComFocus(key, index,
					pageSize, order);
			break;
		}
		case 3: {
			lstCommodities = commodityBiz.searchByComTime(key, index, pageSize,
					order);
			break;
		}
		case 4: {
			lstCommodities = commodityBiz.searchByComPrice(key, index,
					pageSize, order);
			break;
		}
		default:
			break;
		}
	   switch(jsp){
	   case 1: {
			url = "jsp/Market/SearchResult.jsp";
			break;
		}
		case 2: {
			url = "jsp/Market/TStore.jsp";
			break;
		}
		case 3: {
			url = "jsp/Market/EStore.jsp";
			break;
		}
		case 4: {
			url = "jsp/Market/FStore.jsp";
			break;
		}
		default:
			break;
	   
	   }


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
		
		request.setAttribute("lstCommodities", lstCommodities);
		request.setAttribute("pageNumber", index);
		request.setAttribute("key", key1);
		request.setAttribute("sort", sort);
		request.setAttribute("jsp", jsp);
		int pageCount=lstCommodities.size()==0?0:lstCommodities.get(0).getPageCount();
		request.setAttribute("pageCount",pageCount );
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
