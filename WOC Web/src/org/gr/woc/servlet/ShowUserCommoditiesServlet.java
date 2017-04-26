package org.gr.woc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.gr.woc.biz.ICommodityBiz;
import org.gr.woc.biz.IUserInfBiz;
import org.gr.woc.biz.impl.CommodityBizImpl;
import org.gr.woc.biz.impl.UserInfBizImpl;
import org.gr.woc.po.User;
import org.gr.woc.vo.Commodities;

/**
 * Servlet implementation class ShowUserCommoditiesServlet
 */
public class ShowUserCommoditiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowUserCommoditiesServlet() {
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
		request.setAttribute("flag", "OK");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		// int index = Integer.parseInt(request.getParameter("pageNumber"));
		int index = 1;
		int pageSize = 0;
		ICommodityBiz commodityBiz = new CommodityBizImpl();
		List <Commodities> lstSCommodities= commodityBiz.searchOwnerCommodities(user.getUserId(),1,index,pageSize);
		request.setAttribute("lstSCommodities",lstSCommodities);
		List <Commodities> lstECommodities= commodityBiz.searchOwnerCommodities(user.getUserId(),2,index,pageSize);
		request.setAttribute("lstECommodities",lstECommodities);
		List <Commodities> lstFCommodities= commodityBiz.searchOwnerCommodities(user.getUserId(),3,index,pageSize);
		request.setAttribute("lstFCommodities",lstFCommodities);
		IUserInfBiz userInfBiz=new UserInfBizImpl();
		List<Commodities> lstACommodities =userInfBiz.searchAttention(user.getUserId());
		request.setAttribute("lstACommodities",lstACommodities);
		request.setAttribute("pageNumber", index);
		//跳转？
		RequestDispatcher dispatcher = request.getRequestDispatcher("FindRealizerServlet?u=1");
		dispatcher.forward(request, response);
	}

}
