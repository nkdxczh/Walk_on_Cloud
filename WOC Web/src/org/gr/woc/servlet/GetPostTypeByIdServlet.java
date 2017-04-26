package org.gr.woc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gr.woc.biz.IPost_TypeBiz;
import org.gr.woc.biz.impl.Post_TypeBizImpl;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Post_Type;

/**
 * Servlet implementation class GetPostTypeById
 */
public class GetPostTypeByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPostTypeByIdServlet() {
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
		IPost_TypeBiz biz=new Post_TypeBizImpl();
		int typeid=Integer.parseInt(request.getParameter("posttypeid"));
		Post_Type post_Type=new Post_Type();
		post_Type.setPostTypeId(typeid);
		Post_Type p=biz.searchById(post_Type);
		String postTypeName=p.getPostTypeName();
		request.setAttribute("postTypeName", postTypeName);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/forum/forindex.jsp");
		dispatcher.forward(request, response);
	}

}
