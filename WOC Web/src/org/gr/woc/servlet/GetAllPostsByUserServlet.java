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

import org.gr.woc.biz.IOrderBiz;
import org.gr.woc.biz.IPostBiz;
import org.gr.woc.biz.impl.OrderBizImpl;
import org.gr.woc.biz.impl.PostBizImpl;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Order;
import org.gr.woc.po.Post;
import org.gr.woc.po.User;

/**
 * Servlet implementation class GetAllPostsByUserServlet
 */
public class GetAllPostsByUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllPostsByUserServlet() {
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
		request.setAttribute("flag", "OK");
		IPostBiz biz=new PostBizImpl();
		User user=(User)request.getSession().getAttribute("user");
		 List<Post> lstAllPostsByUser=biz.searchInfByUserId(user);
		 request.setAttribute("lstAllPostsByUser", lstAllPostsByUser);
		 

			if (TransactionManager.connection == null)
				TransactionManager.connection = new ConnectionManager()
						.openConnection();
			List<Order> lstUnfinishedOrdersByUser=new ArrayList<Order>();
			if(user!=null){
				IOrderBiz biz1 = new OrderBizImpl();
				lstUnfinishedOrdersByUser = biz1.searchUnfinshedInfByUserId(user);
			}
			request.setAttribute("lstUnfinishedOrdersByUser",
					lstUnfinishedOrdersByUser);
		 
		 RequestDispatcher dispatcher = request.getRequestDispatcher("GetAllCommentsByUserServlet");
			dispatcher.forward(request, response);
			//由于我不清楚改往哪里跳转，所有servlet中我每次跳转都是往forindex跳，你自己改一下
		
		 
	}

}
