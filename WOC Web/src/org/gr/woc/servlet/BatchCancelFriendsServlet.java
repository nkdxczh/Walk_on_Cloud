package org.gr.woc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.gr.woc.biz.IUserInfBiz;
import org.gr.woc.biz.impl.UserInfBizImpl;
import org.gr.woc.po.User;

/**
 * Servlet implementation class BathCancelFriendsServlet
 */
public class BatchCancelFriendsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BatchCancelFriendsServlet() {
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
		String[] friendName=request.getParameterValues("friendName");
		for(int i = 0 ;i<friendName.length;i++)
		{
			String value=new String(friendName[i].getBytes("iso8859-1"),"UTF-8");
			
			System.out.println(value);
			userInfBiz.cancelFriend(user.getUserId(), value);
		}	
		String url="jsp/forum/user_friif.jsp";
		response.sendRedirect(url);
	}

}
