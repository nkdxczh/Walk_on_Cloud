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
import org.gr.woc.po.User;
import org.gr.woc.utils.MD5String;

/**
 * Servlet implementation class UpdatePasswordServlet
 */
public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePasswordServlet() {
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
		String nPassword=request.getParameter("nPassword");
		String oPassword=request.getParameter("oPassword");
		String rPassword=request.getParameter("rPassword");
		String authcode=request.getParameter("auth");
		String auth=(String)session.getAttribute("authcode");
		String pw=user.getUserPassword();
		MD5String md5=new  MD5String();
		oPassword=md5.getMD5(oPassword);
		System.out.println(nPassword);
		System.out.println(oPassword);
		System.out.println(rPassword);
		System.out.println(pw);
		String url="";
		if(!oPassword.equals(pw))url="1";
		else if(!auth.equals(authcode))url="-1";
		else if(!nPassword.equals(rPassword))url="2";
		else{
			nPassword=md5.getMD5(nPassword);
			IUserInfBiz userInfBiz = new UserInfBizImpl();
			boolean flag=userInfBiz.changePassword(user.getUserId(),nPassword);
			if(flag){
				url="3";
				}
			else{
				url="4";
				}
		}
		request.setAttribute("tip", url);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/forum/user_regif.jsp");
		dispatcher.forward(request, response);
	}

}
