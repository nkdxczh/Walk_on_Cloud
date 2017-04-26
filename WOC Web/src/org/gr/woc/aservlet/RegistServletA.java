package org.gr.woc.aservlet;

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
import org.gr.woc.vo.Person;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class RegistServletA
 */
public class RegistServletA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServletA() {
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
		response.setContentType("text/html; charset=UTF-8");
		String userdata=request.getParameter("user");
		String detaildata=request.getParameter("detail");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		User u=gson.fromJson(userdata, User.class);
		Detail_Inf di=gson.fromJson(detaildata, Detail_Inf.class);
		MD5String md5=new MD5String();
		User newUser=new User();
		Detail_Inf inf=new Detail_Inf();
		String username=u.getUserName();
		String password=md5.getMD5(u.getUserPassword());
		String gender=di.getGender();
		String nickname=di.getNickName();
		String location=di.getLocation();
		System.out.println(u.toString());
		System.out.println(di.toString());
		IUserInfBiz userbiz=new UserInfBizImpl();
		User user=userbiz.searchByName(username);
		
		System.out.println("hehehehe"+gender+location);
		if(user!=null){
			response.getWriter().println("yonghuchongfu");
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
		Person person=new Person(newUser.getUserId(), newUser.getUserName(), nickname);
		response.getWriter().println(gson.toJson(person));
		HttpSession session = request.getSession();
		session.setAttribute("user", newUser);
		session.setAttribute("inf", inf);
	}

}
