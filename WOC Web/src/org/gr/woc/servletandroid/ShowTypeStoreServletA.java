package org.gr.woc.servletandroid;

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
 * Servlet implementation class ShowTypeStoreServletA
 */
public class ShowTypeStoreServletA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowTypeStoreServletA() {
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
		int operation = Integer.parseInt(new String(request.getParameter("key")
				.getBytes("ISO-8859-1"), "utf-8"));
		int key1=operation;
		System.out.println("1111111111111111111111key1 is:"+key1);
		int index = Integer.parseInt("1");
		int sort = Integer.parseInt(new String(request.getParameter("sort")
				.getBytes("ISO-8859-1"), "utf-8"));
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
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		response.getWriter().println(gson.toJson(lstCommodities));
	}

}
