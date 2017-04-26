package org.gr.woc.aservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gr.woc.biz.ICommodityBiz;
import org.gr.woc.biz.impl.CommodityBizImpl;
import org.gr.woc.dao.IDesireDao;
import org.gr.woc.dao.impl.DesireDaoImpl;
import org.gr.woc.po.Commodity;
import org.gr.woc.po.Desire;
import org.gr.woc.po.User;
import org.gr.woc.vo.Commodities;

/**
 * Servlet implementation class ReleaseCommodityServletA
 */
public class ReleaseCommodityServletA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReleaseCommodityServletA() {
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
		ICommodityBiz commoditybiz = new CommodityBizImpl();
		Commodity commodity = new Commodity();
		int userId=Integer.parseInt(request.getParameter("userId"));
		String yongtu=request.getParameter("yongtu");
		String leixing=request.getParameter("leixing");
		String price=request.getParameter("price");
		String describe=request.getParameter("describe");
		String comRegion=request.getParameter("comRegion");
		String comName=request.getParameter("comName");
		String desire=null;
		int comType = 0;
		double dPrice = 0.0;
		switch (Integer.parseInt(yongtu)) {
		case 1:
			switch (Integer.parseInt(leixing)) {
			case 1:

			{
				comType = 1;
				desire = null;
				dPrice = Double.parseDouble(price);
				break;
			}
			case 2: {
				comType = 2;
				desire = null;
				dPrice = Double.parseDouble(price);
				break;
			}

			case 3: {
				comType = 3;
				desire = null;
				dPrice = Double.parseDouble(price);
				break;
			}
			}
			break;
		case 2:
			switch (Integer.parseInt(leixing)) {
			case 1:

			{
				comType = 4;
				dPrice = 0;
				desire=price;
				break;
			}
			case 2:

			{
				comType = 5;
				dPrice = 0;
				desire=price;
				break;
			}
			case 3:

			{
				comType = 6;
				dPrice = 0;
				break;
			}
			}
			break;
		case 3:
			switch (Integer.parseInt(leixing)) {
			case 1:

			{
				comType = 7;
				dPrice = 0.0;
				desire = null;
				break;
			}
			case 2:

			{
				comType = 8;
				dPrice = 0.0;
				desire = null;
				break;
			}
			case 3: {
				comType = 9;
				dPrice = 0.0;
				desire = null;
				break;
			}
			}
			break;
		default:
			break;
		}
		commodity.setUserId(userId);
		commodity.setComName(comName);
		commodity.setProperty(0);
		commodity.setType(comType);
		commodity.setPrice(dPrice);
		commodity.setComRegion(comRegion);
		commodity.setDescribe(describe);
		commodity.setStatus(0);
		// commodity.setOffTime(null);
		commodity.setRequiredScore(0);
		commoditybiz.releaseCommodity(commodity);
		List<Commodities> lstCommodities = commoditybiz.searchByUserId(
				userId, 0,1,0);
		int comId = lstCommodities.get(0).getComId();
		IDesireDao desireDao = new DesireDaoImpl();
		Desire desireCommodity = new Desire();
		desireCommodity.setComId(comId);
		desireCommodity.setDesire(desire);
		desireDao.insert(desireCommodity);
		System.out.println("suc");
		response.getWriter().println("suc");
	}

}
