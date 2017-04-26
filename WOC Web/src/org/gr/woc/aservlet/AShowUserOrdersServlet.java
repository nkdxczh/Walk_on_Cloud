package org.gr.woc.aservlet;

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

import org.gr.woc.biz.ICommodityBiz;
import org.gr.woc.biz.IOrderBiz;
import org.gr.woc.biz.IUserInfBiz;
import org.gr.woc.biz.impl.CommodityBizImpl;
import org.gr.woc.biz.impl.OrderBizImpl;
import org.gr.woc.biz.impl.UserInfBizImpl;
import org.gr.woc.dao.IOrderDao;
import org.gr.woc.dao.impl.OrderDaoImpl;
import org.gr.woc.po.Order;
import org.gr.woc.po.User;
import org.gr.woc.vo.Commodities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class ShowUserCommoditiesServlet
 */
public class AShowUserOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AShowUserOrdersServlet() {
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
		// --------------------接受数据JSon-----------------
		// 获取终端传入的JSon数据
		String sUser = request.getParameter("user");
		// 反序列化形成一个User对象
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss")
				.create();
		User user = gson.fromJson(sUser, User.class);

		// -------------------处理数据-----------------------
		IOrderBiz orderBiz = new OrderBizImpl();
		List<Order> lstInformation = new ArrayList<Order>();
		lstInformation=orderBiz.searchAllOrdersByUserId(user);
		// -------------------响应客户端 JSon----------------
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String gson_data = gson.toJson(lstInformation);
		System.out.println(gson_data);
		out.println(gson_data);
		out.flush();
		out.close();
	}
}
