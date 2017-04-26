package org.gr.woc.aservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gr.woc.biz.IOrderBiz;
import org.gr.woc.biz.impl.OrderBizImpl;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Detail_Inf;
import org.gr.woc.po.Order;

/**
 * Servlet implementation class ApplyOrderServletA
 */
public class ApplyOrderServletA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyOrderServletA() {
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
		if(TransactionManager.connection==null)
			TransactionManager.connection=new ConnectionManager().openConnection();
		int userid=Integer.parseInt(request.getParameter("userId"));
		int comid=Integer.parseInt(request.getParameter("comId"));
		String ordername=request.getParameter("name");
		String location=request.getParameter("location");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		//需要申请的订单的商品ID
		IOrderBiz biz=new OrderBizImpl();
		Order order=new Order();
		order.setComId(comid);
		order.setUserId(userid);
		order.setOrderName(ordername);
		order.setOrderEmail(email);
		order.setOrderPhone(phone);
		order.setOrderLocation(location);
		System.out.println(order);
		biz.applyOrder(order);
		response.getWriter().println("suc");
	}

}
