package org.gr.woc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gr.woc.biz.IOrderBiz;
import org.gr.woc.biz.impl.CommodityBizImpl;
import org.gr.woc.biz.impl.OrderBizImpl;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Detail_Inf;
import org.gr.woc.po.Order;
import org.gr.woc.tuisong.Test;

/**
 * Servlet implementation class CompleteOrderServlet
 */
public class CompleteOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompleteOrderServlet() {
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
		//User user=(User)request.getSession().getAttribute("user");
		IOrderBiz biz=new OrderBizImpl();
		//List<Order> lstUnfinishedOrdersByUser=biz.unfinshedInfSearchByUserId(user);
		int orderid=Integer.parseInt(request.getParameter("orderid"));
		Order order=new Order();
		String nickname=((Detail_Inf)request.getSession().getAttribute("inf")).getNickName();
		order.setOrderId(orderid);
		CommodityBizImpl commodityBizImpl=new CommodityBizImpl();
		//(commodityBizImpl.searchByComId(biz.searchInfById(order).getComId())).getOwnerId();
		biz.completeOrder(order);
		Test test=new Test();
		test.sendMessageAuto(String.valueOf((commodityBizImpl.searchByComId(biz.searchInfById(order).getComId())).getOwnerId()),
				nickname+"已经确认收货了在您这里购买的商品："+
						biz.searchInfById(order).getComName());
		
		//request.setAttribute("detaiOrder", detaiOrder);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/forum/forindex.jsp");
		dispatcher.forward(request, response);
		//由于我不清楚改往哪里跳转，所有servlet中我每次跳转都是往forindex跳，你自己改一下
	}

}
