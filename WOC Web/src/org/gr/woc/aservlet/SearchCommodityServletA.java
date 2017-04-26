package org.gr.woc.aservlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gr.woc.biz.ICommodityBiz;
import org.gr.woc.biz.impl.CommodityBizImpl;
import org.gr.woc.vo.Commodities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class SearchCommodityServletA
 */
public class SearchCommodityServletA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchCommodityServletA() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		ICommodityBiz commodityBiz = new CommodityBizImpl();
		String key = request.getParameter("key");
		String key1=key;
		int sort = 1;
		String option = request.getParameter("option");
		int index = Integer.parseInt("1");
		int pageSize=12;
		String order="";
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
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		response.getWriter().println(gson.toJson(lstCommodities));
	}

}
