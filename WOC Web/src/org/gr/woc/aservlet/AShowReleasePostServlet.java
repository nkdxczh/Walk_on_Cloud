package org.gr.woc.aservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gr.woc.biz.ICommentBiz;
import org.gr.woc.biz.IPostBiz;
import org.gr.woc.biz.impl.CommentBizImpl;
import org.gr.woc.biz.impl.PostBizImpl;
import org.gr.woc.dao.impl.Post_ResourceDaoImpl;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Post;
import org.gr.woc.po.Post_Comment;
import org.gr.woc.po.Post_Resource;
import org.gr.woc.po.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class ShowPostServelet
 */
public class AShowReleasePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AShowReleasePostServlet() {
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
		System.out.println("999999999999999999999999999999999999999");
		// --------------------接受数据JSon-----------------
		// 获取终端传入的JSon数据
		String sUser = request.getParameter("user");
		// 反序列化形成一个User对象
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss")
				.create();
		User user = gson.fromJson(sUser, User.class);

		// -------------------处理数据-----------------------
		IPostBiz postBiz=new PostBizImpl();
		List<Post> lstInformation = new ArrayList<Post>();
		lstInformation=postBiz.searchInfByUserId(user);
		// -------------------响应客户端 JSon----------------
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String gson_data = gson.toJson(lstInformation);
		System.out.println(gson_data);
		out.println(gson_data);
		out.flush();
		out.close();
	}
}
