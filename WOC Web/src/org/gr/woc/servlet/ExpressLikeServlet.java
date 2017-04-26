package org.gr.woc.servlet;

import java.io.IOException;

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
import org.gr.woc.po.User;
import org.gr.woc.proxy.WocProxy;



/**
 * Servlet implementation class ExpressLikeServlet
 */
public class ExpressLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpressLikeServlet() {
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
		int postid=Integer.parseInt(request.getParameter("postid"));
		
		 IPostBiz biz =new PostBizImpl();
		Post post=new Post();
		post.setPostId(postid);
		IPostBiz biz2=new PostBizImpl();
		Post tpost=biz2.searchInfById(post);
		int para=Integer.parseInt(request.getParameter("para"));
		User user=(User)request.getSession().getAttribute("user");
		int userid=user.getUserId();
		biz.expressLike(tpost, para, userid);//para是一次点赞的数量，帖子的话一般为一个，
		//电影的话为评分数量
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/forum/post.jsp?postid="+postid);
		dispatcher.forward(request, response);
		//由于我不清楚改往哪里跳转，所有servlet中我每次跳转都是往forindex跳，你自己改一下
	}

}
