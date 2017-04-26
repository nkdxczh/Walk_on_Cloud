package org.gr.woc.aservlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gr.woc.dao.IPost_ResourceDao;
import org.gr.woc.dao.impl.Post_ResourceDaoImpl;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Post_Resource;

/**
 * Servlet implementation class AddPostResourceServlet
 * 
 * 增加帖子资源
 */
public class AddPostResourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPostResourceServlet() {
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
		if(TransactionManager.connection==null)
			TransactionManager.connection=new ConnectionManager().openConnection();
		IPost_ResourceDao dao=new Post_ResourceDaoImpl(TransactionManager.connection);
		Post_Resource resource=new Post_Resource();
		int postid=Integer.parseInt(request.getParameter("postid"));
		//帖子ID
		resource.setPostId(postid);
		String respath=request.getParameter("respath");
		//资源路径
		resource.setResPath(respath);
		dao.insert(resource);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/forum/forindex.jsp");
		dispatcher.forward(request, response);
		//由于我不清楚改往哪里跳转，所有servlet中我每次跳转都是往forindex跳，你自己改一下
	}

}
