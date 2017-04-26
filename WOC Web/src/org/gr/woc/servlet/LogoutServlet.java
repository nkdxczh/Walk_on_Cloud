package org.gr.woc.servlet;

import java.io.IOException;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.gr.woc.listener.UserSessionListener;
import org.gr.woc.po.User;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//this.doPost(request, response);
		HttpSession thisSession=request.getSession();
		User user=(User)thisSession.getAttribute("user");
		String username=user.getUserName();
		HttpSession oldsession=(HttpSession) UserSessionListener.sessionMap.get(username);
		if(oldsession==null){
			response.sendRedirect("/WOC");
			return;
		}
		oldsession.invalidate();
		UserSessionListener.sessionMap.remove(username);
		response.sendRedirect("/WOC");
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*HttpSession thisSession=request.getSession();
		User user=(User)thisSession.getAttribute("user");
		String username=user.getUserName();
		HttpSession oldsession=(HttpSession) UserSessionListener.sessionMap.get(username);
		oldsession.invalidate();
		UserSessionListener.sessionMap.remove(username);
		response.sendRedirect("/WOC");*/
	}

}
