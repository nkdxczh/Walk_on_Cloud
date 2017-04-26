package org.gr.woc.aservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gr.woc.biz.IPostBiz;
import org.gr.woc.biz.impl.PostBizImpl;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Post;
import org.gr.woc.po.Post_Comment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class SearchPostsServlet
 */
public class ASearchPostsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ASearchPostsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		// --------------------接受数据JSon-----------------
		// 获取终端传入的JSon数据
		String comment_data = request.getParameter("comment_data");
		// 反序列化形成一个Users对象
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss")
				.create();
		Post_Comment pc = gson.fromJson(comment_data, Post_Comment.class);
		
		List<Post> lstSearchResult;
		if (TransactionManager.connection == null)
			TransactionManager.connection = new ConnectionManager()
					.openConnection();
		IPostBiz biz = new PostBizImpl();
		String word = pc.getPostComContent();
		lstSearchResult = biz.searchInfResult(word);
		
		// 步骤1：创建Gson对象
		String gson_data = gson.toJson(lstSearchResult);
		
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		PrintWriter out = response.getWriter();
		
		out.print(gson_data);
		
		out.flush();
		out.close();
	}

}
