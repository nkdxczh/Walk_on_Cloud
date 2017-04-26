package org.gr.woc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gr.woc.biz.ICommodityBiz;
import org.gr.woc.biz.IOrderBiz;
import org.gr.woc.biz.impl.CommodityBizImpl;
import org.gr.woc.biz.impl.OrderBizImpl;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Detail_Inf;
import org.gr.woc.po.Order;
import org.gr.woc.po.User;
import org.gr.woc.tuisong.Test;

/**
 * Servlet implementation class ApplyOrderServlet
 * 申请订单
 */
public class ApplyOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyOrderServlet() {
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
		if(TransactionManager.connection==null)
			TransactionManager.connection=new ConnectionManager().openConnection();
		User user=(User)request.getSession().getAttribute("user");
		int userid=user.getUserId();
		int comid=Integer.parseInt(request.getParameter("comid"));
		String ordername=new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8");
		String location=new String(request.getParameter("location").getBytes("iso-8859-1"),"utf-8");
		String email=new String(request.getParameter("email").getBytes("iso-8859-1"),"utf-8");
		String phone=new String(request.getParameter("phone").getBytes("iso-8859-1"),"utf-8");
		//需要申请的订单的商品ID
		String nickname=((Detail_Inf)request.getSession().getAttribute("inf")).getNickName();
		IOrderBiz biz=new OrderBizImpl();
		Order order=new Order();
		order.setComId(comid);
		order.setUserId(userid);
		order.setOrderName(ordername);
		order.setOrderEmail(email);
		order.setOrderPhone(phone);
		order.setOrderLocation(location);
		biz.applyOrder(order);
		//request.setAttribute("detaiOrder", detaiOrder);
		Test test=new Test();
		ICommodityBiz iCommodityBiz=new CommodityBizImpl();
		test.sendMessageAuto(String.valueOf((iCommodityBiz.searchByComId(comid)).getOwnerId()),
				nickname+"申请了您名为"+(iCommodityBiz.searchByComId(comid)).getComName()
				+"的商品"
				);		
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Market/OrderSuccess.html");
		dispatcher.forward(request, response);
		//由于我不清楚改往哪里跳转，所有servlet中我每次跳转都是往forindex跳，你自己改一下
	}

}
