package org.gr.woc.servlet;

import java.io.IOException;
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

/**
 * Servlet implementation class GetLatastPostsByLayoutServlet
 * 每个版块的最新帖子
 */
public class GetLatestPostsByLayoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetLatestPostsByLayoutServlet() {
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
		request.setAttribute("flag", "OK");
		IPostBiz biz=new PostBizImpl();
		int bankuaihao=Integer.parseInt(request.getParameter("posttype"));
		List<Post> lstLatestPostByLayout=biz.latastPosts(bankuaihao);
		request.setAttribute("lstLatestPostByLayout", lstLatestPostByLayout);
		String url="jsp/forum/forindex.jsp";
		switch(bankuaihao){
		case 100:
			url="jsp/forum/movie_index.jsp";
			break;
		case 200:
			url="jsp/forum/education_index.jsp";
			break;
		case 300:
			url="jsp/forum/music_index.jsp";
			break;
		case 400:
			url="jsp/forum/book_index.jsp";
			break;
		case 500:
			url="jsp/forum/chat_index.jsp";
			break;
		case 600:
			url="jsp/forum/activity_index.jsp";
			break;
			
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		//由于我不清楚改往哪里跳转，所有servlet中我每次跳转都是往forindex跳，你自己改一下
	}

}
