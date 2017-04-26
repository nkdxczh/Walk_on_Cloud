package org.gr.woc.aservlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.gr.woc.biz.IPostBiz;
import org.gr.woc.biz.IUserInfBiz;
import org.gr.woc.biz.impl.PostBizImpl;
import org.gr.woc.biz.impl.UserInfBizImpl;
import org.gr.woc.dao.IPost_ResourceDao;
import org.gr.woc.dao.impl.Post_ResourceDaoImpl;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Detail_Inf;
import org.gr.woc.po.Post;
import org.gr.woc.po.Post_Resource;
import org.gr.woc.po.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class UpdateInfServlet
 */
public class AUpdateInfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AUpdateInfServlet() {
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
		request.setCharacterEncoding("UTF-8");
		// --------------------接受数据JSon-----------------
		// 获取终端传入的JSon数据
		String sInformation = request.getParameter("information_data");
		// 反序列化形成一个User对象
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss")
				.create();
		Detail_Inf detail_Inf = gson.fromJson(sInformation, Detail_Inf.class);

		// -------------------处理数据-----------------------
		System.out.println(detail_Inf);
		IUserInfBiz userInfBiz = new UserInfBizImpl();
		userInfBiz.changeDetailInformation(detail_Inf);
		// -------------------响应客户端 JSon----------------
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String gson_data = gson.toJson(detail_Inf);
		System.out.println(gson_data);
		out.println(gson_data);
		out.flush();
		out.close();
	}

}