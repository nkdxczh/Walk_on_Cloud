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
import org.gr.woc.biz.IUserInfBiz;
import org.gr.woc.biz.impl.OrderBizImpl;
import org.gr.woc.biz.impl.PostBizImpl;
import org.gr.woc.biz.impl.UserInfBizImpl;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Detail_Inf;
import org.gr.woc.po.Order;
import org.gr.woc.po.Post;
import org.gr.woc.po.User;
import org.gr.woc.vo.Friends;

/**
 * Servlet implementation class ShowOthersInfServlet
 */
public class ShowOthersInfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowOthersInfServlet() {
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
		request.setAttribute("flag", "Ok");
		User user=new User();
		int userid=Integer.parseInt(request.getParameter("userid"));
		user.setUserId(userid);
		UserInfBizImpl userInfBiz = new UserInfBizImpl();
		Detail_Inf userinf = userInfBiz.searchInfById(user.getUserId());
		User user2=(User)request.getSession().getAttribute("user");
		IOrderBiz orderbiz=new OrderBizImpl();
		List<Order> lstOrd=orderbiz.searchFinshedInfByUserId(user);
		IPostBiz postbiz=new PostBizImpl();
		List<Post> lstpost=postbiz.searchInfByUserId(user);
		IUserInfBiz biz=new UserInfBizImpl();
		List<Friends> lstyoufriends=biz.searchFriendInformation(userid);
		List<Friends> lstmyfriends=biz.searchFriendInformation(user2.getUserId());
		List<Friends> lstfriends=new ArrayList<Friends>();
		for(int i=0;i<lstyoufriends.size();i++)
			for(int j=0;j<lstmyfriends.size();j++)
			{
				
				if(lstyoufriends.get(i).getFriendName().equals(lstmyfriends.get(j).getFriendName()))
				lstfriends.add(lstyoufriends.get(i));
			}
		request.setAttribute("userinf", userinf);
		request.setAttribute("lstOrd", lstOrd);
		request.setAttribute("lstpost", lstpost);
		request.setAttribute("lstfriends", lstfriends);
		
		if (TransactionManager.connection == null)
			TransactionManager.connection = new ConnectionManager()
					.openConnection();
		HttpSession session = request.getSession();
		User user1 = (User) session.getAttribute("user");
		List<Order> lstUnfinishedOrdersByUser=new ArrayList<Order>();
		if(user!=null){
			IOrderBiz biz1 = new OrderBizImpl();
			lstUnfinishedOrdersByUser = biz1.searchUnfinshedInfByUserId(user1);
		}
		request.setAttribute("lstUnfinishedOrdersByUser",
				lstUnfinishedOrdersByUser);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("jsp/forum/person.jsp?userid="+userid);
		dispatcher.forward(request, response);
	}

}
