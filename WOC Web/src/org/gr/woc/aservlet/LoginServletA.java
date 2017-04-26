package org.gr.woc.aservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.gr.woc.biz.IUserInfBiz;
import org.gr.woc.biz.impl.UserInfBizImpl;
import org.gr.woc.listener.UserSessionListener;
import org.gr.woc.po.Detail_Inf;
import org.gr.woc.po.User;
import org.gr.woc.utils.MD5String;
import org.gr.woc.vo.Person;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class LoginServletA
 */
public class LoginServletA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServletA() {
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
		IUserInfBiz infoBiz=new UserInfBizImpl();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		User userin = gson.fromJson(request.getParameter("user"), User.class);
		System.out.println(userin.toString());
		String username = userin.getUserName().trim();
		String password = new MD5String().getMD5(userin.getUserPassword());
		User user = new UserInfBizImpl().searchByName(username);
		PrintWriter out=response.getWriter();
		String gsondata="";
		if (user == null) {
			System.out.println("usernull");
			out.println("failure");
			out.flush();
			out.close();
			return;
		}
		boolean pwdflag = user.getUserPassword().equals(password);
		if (!pwdflag) {
			out.println("failure");
			System.out.println("pwdnull");
			out.flush();
			out.close();
			return;
		}
		String nickname=infoBiz.searchInfById(user.getUserId()).getNickName();
		int score=infoBiz.searchInfById(user.getUserId()).getScore();
		HttpSession session = request.getSession();
		Detail_Inf inf=new Detail_Inf();
		inf.setNickName(nickname);
		inf.setScore(score);
		Person person=new Person(user.getUserId(), user.getUserName(), nickname);
		if(UserSessionListener.sessionMap.get(username)!=null){
			HttpSession oldsession=(HttpSession) UserSessionListener.sessionMap.get(username);
			oldsession.invalidate();
			UserSessionListener.sessionMap.remove(username);
			UserSessionListener.sessionMap.put(username, session);
			UserSessionListener.sessionMap.remove(session.getId());
			session.setAttribute("user", user);
			session.setAttribute("inf", inf);
		}
		else{
			UserSessionListener.sessionMap.get(session.getId());
			UserSessionListener.sessionMap.put(username, UserSessionListener.sessionMap.get(session.getId()));
			UserSessionListener.sessionMap.remove(session.getId());
			session.setAttribute("user", user);
			session.setAttribute("inf", inf);
		}
		System.out.println("suc");
		out.println(gson.toJson(person));
		out.flush();
		out.close();
	}

}
