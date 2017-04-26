package org.gr.woc.servlet;

import java.io.IOException;
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
import org.gr.woc.po.Post_Comment;
import org.gr.woc.po.User;

/**
 * Servlet implementation class GetAllCommentsByUser
 */
public class GetAllCommentsByUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllCommentsByUserServlet() {
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
		ICommentBiz biz=new CommentBizImpl();
		User user=(User)request.getSession().getAttribute("user");
		List<Post_Comment> lstAllCommentsByUser=biz.searchInfByUserId(user);
		request.setAttribute("lstAllCommentsByUser", lstAllCommentsByUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("FindRealizerServlet?u=2");
		dispatcher.forward(request, response);
		//由于我不清楚改往哪里跳转，所有servlet中我每次跳转都是往forindex跳，你自己改一下
	}

}
