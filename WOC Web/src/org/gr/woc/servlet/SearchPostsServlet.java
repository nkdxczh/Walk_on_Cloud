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
 * Servlet implementation class SearchPostsServlet
 */
public class SearchPostsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPostsServlet() {
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
		List<Post> lstSearchResult ;
		request.setCharacterEncoding("UTF-8");
		if(TransactionManager.connection==null)
			TransactionManager.connection=new ConnectionManager().openConnection();
		IPostBiz biz=new PostBizImpl();
		String word=request.getParameter("content");
		lstSearchResult=biz.searchInfResult(word);
		request.setAttribute("lstSearchResult", lstSearchResult);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/forum/search_result.jsp");
		dispatcher.forward(request, response);
	}

}
