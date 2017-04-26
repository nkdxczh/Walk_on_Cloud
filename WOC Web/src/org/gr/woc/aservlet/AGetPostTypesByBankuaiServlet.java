package org.gr.woc.aservlet;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class GetPostTypesByBankuaiServlet
 */
public class AGetPostTypesByBankuaiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AGetPostTypesByBankuaiServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (TransactionManager.connection == null)
			TransactionManager.connection = new ConnectionManager()
					.openConnection();
		request.setAttribute("flag", "OK");
		IPost_TypeDao dao = new Post_TypeDaoImpl(TransactionManager.connection);
		 int bankuaiid=Integer.parseInt(request.getParameter("bankuaiid"));
		List<Post_Type> lstPOstType = dao.selectByBankuai(bankuaiid);
		// 步骤1：创建Gson对象
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss")
				.create();
		String gson_data = gson.toJson(lstPOstType);

		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		out.print(gson_data);

		out.flush();
		out.close();

	}

}
