package org.gr.woc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.gr.woc.biz.IUserInfBiz;
import org.gr.woc.biz.impl.UserInfBizImpl;
import org.gr.woc.po.Detail_Inf;
import org.gr.woc.po.User;
import org.gr.woc.vo.Friends;

/**
 * Servlet implementation class ShowFriendsInfServlet
 */
public class ShowFriendsInfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowFriendsInfServlet() {
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
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("flag", "OK");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		IUserInfBiz userInfBiz = new UserInfBizImpl();
		List<Friends> lstFriends = userInfBiz.searchFriendInformation(user.getUserId());
		request.setAttribute("lstFriends",lstFriends);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/forum/user_friif.jsp");
		dispatcher.forward(request, response);
	}

}
