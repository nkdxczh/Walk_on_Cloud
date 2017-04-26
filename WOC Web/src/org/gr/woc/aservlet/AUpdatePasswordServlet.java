package org.gr.woc.aservlet;

import java.io.IOException;
import java.io.PrintWriter;

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
import org.gr.woc.utils.MD5String;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class UpdatePasswordServlet
 */
public class AUpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AUpdatePasswordServlet() {
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
		// --------------------接受数据JSon-----------------
				// 获取终端传入的JSon数据
				String sInformation = request.getParameter("information_data");
				// 反序列化形成一个User对象
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss")
						.create();
				User user = gson.fromJson(sInformation, User.class);

				// -------------------处理数据-----------------------
				System.out.println(user);
				IUserInfBiz userInfBiz = new UserInfBizImpl();
				userInfBiz.changePassword(user.getUserId(), user.getUserPassword());
				// -------------------响应客户端 JSon----------------
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				String gson_data = gson.toJson(user);
				System.out.println(gson_data);
				out.println(gson_data);
				out.flush();
				out.close();
			}

}
