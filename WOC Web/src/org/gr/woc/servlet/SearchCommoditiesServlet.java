package org.gr.woc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gr.woc.biz.ICommodityBiz;
import org.gr.woc.biz.impl.CommodityBizImpl;
import org.gr.woc.vo.Commodities;

/**
 * Servlet implementation class SearchCommoditiesServlet
 */
public class SearchCommoditiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchCommoditiesServlet() {
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
		ICommodityBiz commodityBiz = new CommodityBizImpl();
		String key = request.getParameter("key");
		key=new String(key.getBytes("ISO-8859-1"),"utf-8");
		String key1=key;
		int sort = Integer.parseInt(request.getParameter("sort"));
		String option = request.getParameter("option");
		int index = Integer.parseInt(request.getParameter("pageNumber"));
		int pageSize=12;
		String order="";
		List<Commodities> lstCommodities = new ArrayList<Commodities>();
		String url = "jsp/Market/SearchResult.jsp";
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
		switch (Integer.parseInt(option)) {
		case 1: {
			lstCommodities = commodityBiz.searchByComName(key, 0,index,pageSize,order);

			break;
		}
		case 3: {
			lstCommodities=commodityBiz.searchByComType(key, 0,index,pageSize,order);
			break;
		}
		case 2: {
			lstCommodities=commodityBiz.searchByComRegion(key, 0,index,pageSize,order);
			break;
		}
		case 4: {
			lstCommodities=commodityBiz.searchByUserName(key, 0,index,pageSize,order);
			break;
		}
		default:
			break;
		}
		
		request.setAttribute("lstCommodities", lstCommodities);
		System.out.println(lstCommodities);
		request.setAttribute("pageNumber",index );
		request.setAttribute("key",key1 );
		request.setAttribute("option",option );
		request.setAttribute("sort",sort );
		request.setAttribute("flag", "OK");
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
