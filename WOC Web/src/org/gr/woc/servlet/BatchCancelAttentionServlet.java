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
 * Servlet implementation class BatchCancelAttentionServlet
 */
public class BatchCancelAttentionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BatchCancelAttentionServlet() {
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
		String[] sComId=request.getParameterValues("ac");
		for(int i = 0 ;i<sComId.length;i++)
		{
			userInfBiz.cancelAttention(user.getUserId(), Integer.parseInt(sComId[i]));
		}	
		String url="jsp/forum/user_marif.jsp";
		response.sendRedirect(url);
	}

}
