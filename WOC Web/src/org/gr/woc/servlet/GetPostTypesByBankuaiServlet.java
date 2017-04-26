package org.gr.woc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gr.woc.biz.IPost_TypeBiz;
import org.gr.woc.biz.impl.Post_TypeBizImpl;
import org.gr.woc.dao.IPost_TypeDao;
import org.gr.woc.dao.impl.Post_TypeDaoImpl;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Post_Type;

/**
 * Servlet implementation class GetPostTypesByBankuaiServlet
 */
public class GetPostTypesByBankuaiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPostTypesByBankuaiServlet() {
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
		IPost_TypeDao dao=new Post_TypeDaoImpl(TransactionManager.connection);
		//int bankuaiid=Integer.parseInt(request.getParameter("bankuaiid"));
		List<Post_Type> lstPOstType1=dao.selectByBankuai(101);
		List<Post_Type> lstPOstType2=dao.selectByBankuai(201);
		List<Post_Type> lstPOstType3=dao.selectByBankuai(301);
		List<Post_Type> lstPOstType4=dao.selectByBankuai(401);
		List<Post_Type> lstPOstType5=dao.selectByBankuai(501);
		List<Post_Type> lstPOstType6=dao.selectByBankuai(601);
		request.setAttribute("lstPostType1", lstPOstType1);
		request.setAttribute("lstPostType2", lstPOstType2);
		request.setAttribute("lstPostType3", lstPOstType3);
		request.setAttribute("lstPostType4", lstPOstType4);
		request.setAttribute("lstPostType5", lstPOstType5);
		request.setAttribute("lstPostType6", lstPOstType6);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/forum/release_post.jsp");
		dispatcher.forward(request, response);
		

		
	}

}
