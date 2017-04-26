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
 * Servlet implementation class ShowNewestCommoditiesServlet
 */
public class ShowNewestCommoditiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowNewestCommoditiesServlet() {
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
		request.setAttribute("flag", "OK");
		ICommodityBiz commodityBiz = new CommodityBizImpl();
		List<Commodities> lstCommodities = new ArrayList<Commodities>();
		int key=Integer.parseInt(request.getParameter("key"));
		lstCommodities=commodityBiz.searchHotCommodities(key);
		request.setAttribute("lstCommodities",lstCommodities);
		String url="";
		// 跳转？
		switch(key){
		case 0:
			url="jsp/Market/Store.jsp";
			break;
		case 1:
			url="jsp/Market/TStore.jsp";
			break;
		case 2:
			url="jsp/Market/EStore.jsp";
			break;
		case 3:
			url="jsp/Market/FStore.jsp";
			break;
			default:break;
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
