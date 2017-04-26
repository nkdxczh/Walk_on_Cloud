package org.gr.woc.servlet;

import java.io.IOException;
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

/**
 * Servlet implementation class ShowPostServelet
 */
public class ShowPostServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowPostServelet() {
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
		System.out.println("这里是showpostservlet");
		System.out.println((String)request.getParameter("postid"));
		System.out.println("这里是showpostservlet");
		if(TransactionManager.connection==null)
			TransactionManager.connection=new ConnectionManager().openConnection();
		request.setAttribute("flag", "OK");
		int postId=Integer.parseInt((String)request.getParameter("postid"));
		IPostBiz biz=new PostBizImpl();
		Post postTest=new Post();
		postTest.setPostId(postId);
		Post post=biz.searchInfById(postTest);
		System.out.println(post);
		int bankuaihao=post.getPostProperty();
		request.setAttribute("post", post);
		ICommentBiz commentBiz=new CommentBizImpl();
		List<Post_Comment> lstComment=commentBiz.searchInfByPostId(post);
		request.setAttribute("lstComment", lstComment);
		List<Post> lstRelatedPosts=biz.relatedPosts(bankuaihao);
		request.setAttribute("lstRelatedPosts", lstRelatedPosts);
		//System.out.println(post);
		Post_ResourceDaoImpl daoImpl=new Post_ResourceDaoImpl(TransactionManager.connection);
		List<Post_Resource> lstPostResource=daoImpl.selectById(post);
		request.setAttribute("lstPostResource", lstPostResource);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/forum/post.jsp?postid="+postId);
		dispatcher.forward(request, response);
		
	}

}
