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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class GetLatastPostsByLayoutServlet
 * 每个版块的最新帖子
 */
public class AGetLatestPostsByLayoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AGetLatestPostsByLayoutServlet() {
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
		if(TransactionManager.connection==null)
			TransactionManager.connection=new ConnectionManager().openConnection();
		IPostBiz biz=new PostBizImpl();
		int bankuaihao=Integer.parseInt(request.getParameter("posttype"));
		List<Post> lstLatestPostByLayout=biz.latastPosts(bankuaihao);
		// 步骤1：创建Gson对象
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		String gson_data = gson.toJson(lstLatestPostByLayout);
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		PrintWriter out = response.getWriter();
		
		out.print(gson_data);
		
		out.flush();
		out.close();
	}

}
