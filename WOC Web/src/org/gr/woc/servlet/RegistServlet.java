package org.gr.woc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.gr.woc.biz.IUserInfBiz;
import org.gr.woc.biz.impl.UserInfBizImpl;
import org.gr.woc.po.Detail_Inf;
import org.gr.woc.po.User;
import org.gr.woc.utils.MD5String;

/**
 * Servlet implementation class RegistServlet
 */
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		MD5String md5=new MD5String();
		User newUser=new User();
		Detail_Inf inf=new Detail_Inf();
		String username=request.getParameter("nick");
		String password=md5.getMD5(request.getParameter("password"));
		String gender=request.getParameter("sexes");
		String nickname=request.getParameter("nickname");
		String location=request.getParameter("province")+request.getParameter("city")+request.getParameter("area");
		IUserInfBiz userbiz=new UserInfBizImpl();
		User user=userbiz.searchByName(username);
		gender=new String(gender.getBytes("iso8859-1"), "UTF-8");
		location=new String(location.getBytes("iso8859-1"), "UTF-8");
		nickname=new String(nickname.getBytes("iso8859-1"),"UTF-8");
		System.out.println(gender+location);
		if(user!=null){
			response.getWriter().write("usernameRep");
			return;
		}
		newUser.setUserName(username);
		newUser.setUserPassword(password);
		userbiz.addUser(newUser);
		newUser=userbiz.searchByName(username);
		inf.setGender(gender);
		inf.setLocation(location);
		inf.setUserId(newUser.getUserId());
		inf.setNickName(nickname);
		userbiz.addDetail_Inf(inf);
		HttpSession session = request.getSession();
		session.setAttribute("user", newUser);
		session.setAttribute("inf", inf);
	}

}
