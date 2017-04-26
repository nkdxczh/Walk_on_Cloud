package org.gr.woc.servlet;

import java.io.IOException;

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
import org.gr.woc.tuisong.Test;

/**
 * Servlet implementation class AddFriendServlet
 */
public class AddFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFriendServlet() {
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
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String nickname=((Detail_Inf)request.getSession().getAttribute("inf")).getNickName();
		int friendId=Integer.parseInt(request.getParameter("friendId"));
		IUserInfBiz userInfBiz = new UserInfBizImpl();
		User friend = new User();
		friend.setUserId(friendId);
		boolean flag = userInfBiz.applyFriend(user.getUserId(), friend);
		String url="";
	    if (flag) {
	       url="1";
		}
	    else{
	    	url="2";
	    }
	    System.out.println(flag);
	    System.out.println(user.getUserId());
	    System.out.println(friendId);
	    Test test=new Test();
	    test.sendMessageAuto(String.valueOf(friendId),
	    		nickname+"已申请加您为好友");
	    request.setAttribute("tip", url);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/forum/person.jsp?userid="+friendId);
		dispatcher.forward(request, response);
		
	}

}
