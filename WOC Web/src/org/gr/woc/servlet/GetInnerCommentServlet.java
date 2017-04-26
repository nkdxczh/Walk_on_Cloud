package org.gr.woc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gr.woc.biz.ICommentBiz;
import org.gr.woc.biz.impl.CommentBizImpl;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Post;
import org.gr.woc.po.Post_Comment;

/**
 * Servlet implementation class GetInnerCommentServlet
 */
public class GetInnerCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetInnerCommentServlet() {
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
		int postcomid=Integer.parseInt((String)request.getAttribute("postcomid"));
		int postid=Integer.parseInt((String)request.getAttribute("postid"));
		ICommentBiz biz=new CommentBizImpl();
		Post post=new Post();
		post.setPostId(postid);
		List<Post_Comment> lst=new ArrayList<Post_Comment>();
		biz.infSearchByLayerId(post, postcomid, lst);
		request.setAttribute("lstInnerComment", lst);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/forum/post.jsp?postid="+postid);
		dispatcher.forward(request, response);
	}

}
