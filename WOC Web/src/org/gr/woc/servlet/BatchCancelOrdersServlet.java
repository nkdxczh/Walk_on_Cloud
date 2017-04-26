package org.gr.woc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gr.woc.biz.IOrderBiz;
import org.gr.woc.biz.impl.OrderBizImpl;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Order;

/**
 * Servlet implementation class BatchCancelOrdersServlet
 */
public class BatchCancelOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BatchCancelOrdersServlet() {
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
		System.out.println("ffffffffffffffffffffffffffffffffffffffffffffffffffffff");
		IOrderBiz biz=new OrderBizImpl();
		String type=request.getParameter("type");
		String[] orderids;
		if(type.equals("1"))orderids=request.getParameterValues("UnfinishedLst");
		else orderids=request.getParameterValues("FinishedLst");
		System.out.println(orderids);
		Order order=new Order();
		for(int i=0;i<orderids.length;i++)
		{
			order.setOrderId(Integer.parseInt(orderids[i]));
			biz.cancelOrder(order);
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/forum/user_marif.jsp");
		dispatcher.forward(request, response);
	}

}
