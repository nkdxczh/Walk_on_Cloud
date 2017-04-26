package org.gr.woc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gr.woc.biz.IUserInfBiz;
import org.gr.woc.biz.impl.UserInfBizImpl;
import org.gr.woc.po.User;
import org.gr.woc.vo.Friends;

/**
 * Servlet implementation class FindCommonFriendServlet
 */
public class FindCommonFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindCommonFriendServlet() {
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
		int friendId=Integer.parseInt(request.getParameter(("friendId")));
		User user=(User) request.getSession().getAttribute("user");
		int userId=user.getUserId();
		IUserInfBiz userinfbiz=new UserInfBizImpl();
		List<Friends>userFriend=userinfbiz.searchFriendInformation(userId);
		List<Friends>friendFriend=userinfbiz.searchFriendInformation(friendId);
		List<Friends>commonFriend=new ArrayList<Friends>();
		for(int i=0;i<userFriend.size();i++){
			for(int j=0;j<friendFriend.size();i++){
				if(userFriend.get(i).equals(friendFriend.get(j))){
					commonFriend.add(userFriend.get(i));
					break;
				}
			}
		}
		request.setAttribute("commonFriend", commonFriend);
	}

}
