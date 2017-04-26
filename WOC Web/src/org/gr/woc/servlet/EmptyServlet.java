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
import org.gr.woc.biz.impl.OrderBizImpl;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Order;
import org.gr.woc.po.User;

/**
 * Servlet implementation class EmptyServlet
 */
public class EmptyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmptyServlet() {
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
		String url="";
		request.setAttribute("flag", "OK");
		int i=Integer.parseInt(request.getParameter("key"));
		HttpSession session=request.getSession();
		if(session.getAttribute("user")==null)i=0;
		System.out.println(i);
		String comid=request.getParameter("comid");
		switch(i){
		case 0:
			url="jsp/WelcomePage/index.jsp";
			break;
		case 1:
			url="jsp/Market/Release.jsp";
			break;
		case 2:
			url="jsp/Market/Order.jsp?comid="+comid;
			break;
		case 3:
			url="jsp/forum/release_post.jsp";
			break;
		case 4:
			url="jsp/forum/update_pw.jsp";
			break;
		case 5:
			url="jsp/WelcomePage/index.jsp";
			break;
		default:
			url="jsp/WelcomePage/index.jsp";
			break;
		}
		

		if (TransactionManager.connection == null)
			TransactionManager.connection = new ConnectionManager()
					.openConnection();
		User user = (User) session.getAttribute("user");
		List<Order> lstUnfinishedOrdersByUser=new ArrayList<Order>();
		if(user!=null){
			IOrderBiz biz = new OrderBizImpl();
			lstUnfinishedOrdersByUser = biz.searchUnfinshedInfByUserId(user);
		}
		request.setAttribute("lstUnfinishedOrdersByUser",
				lstUnfinishedOrdersByUser);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
