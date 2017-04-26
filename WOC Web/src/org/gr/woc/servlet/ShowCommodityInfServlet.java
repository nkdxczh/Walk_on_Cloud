package org.gr.woc.servlet;

import java.io.IOException;
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
import org.gr.woc.biz.impl.CommodityBizImpl;
import org.gr.woc.biz.impl.OrderBizImpl;
import org.gr.woc.dao.IPictureDao;
import org.gr.woc.dao.impl.PictureDaoImpl;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Order;
import org.gr.woc.po.Picture;
import org.gr.woc.po.User;
import org.gr.woc.utils.SendByUserCookie;
import org.gr.woc.vo.CComments;
import org.gr.woc.vo.Commodities;

/**
 * Servlet implementation class ShowCommodityInfServlet
 */
public class ShowCommodityInfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowCommodityInfServlet() {
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
		request.getParameter("comId");
		String order="";
		/*int sort=Integer.parseInt(request.getParameter("sort"));
		switch (sort) {
		case 1:
			order = "releaseTime";
			break;
		case 2:
			order = "price";
			break;
		case 3:
			order = "requiredSorce";
			break;
		case 4:
			order = "comFocus";
			break;
		default:
			break;
		}
		int index = Integer.parseInt(request.getParameter("pageNumber"));
		int pageSize=6;*/
		ICommodityBiz commodityBiz = new CommodityBizImpl();
		int comId=Integer.parseInt(request.getParameter("comId"));
		IPictureDao pictureDao=new PictureDaoImpl(); 
		List<Picture> lstPicture=pictureDao.selectByComId(comId);
		if(lstPicture.size()>=1)lstPicture.remove(0);
		request.setAttribute("lstPicture",lstPicture );
		Commodities commodity=commodityBiz.searchByComId(comId);
		request.setAttribute("commodity", commodity);
		int type=0;
		if(commodity.getComTypeName().equals("交易书籍")||commodity.getComTypeName().equals("交易日用品")||commodity.getComTypeName().equals("交易其它"))type=1;
		if(commodity.getComTypeName().equals("交换书籍")||commodity.getComTypeName().equals("交换日用品")||commodity.getComTypeName().equals("交换其它"))type=2;
		if(commodity.getComTypeName().equals("漂流书籍")||commodity.getComTypeName().equals("漂流日用品")||commodity.getComTypeName().equals("漂流其它"))type=3;
		List<CComments> lstCComments = new ArrayList<CComments>();
		lstCComments=commodityBiz.searchCCmments(comId);
		List<Commodities> lstRCommodities = new ArrayList<Commodities>();
		request.setAttribute("flag", "OK");
		request.setAttribute("type", type);
		
		/*lstRCommodities=commodityBiz.searchByComType(commodityBiz.searchByComId(comId).getComTypeName(), 0,index,pageSize,order);
		request.setAttribute("lstRCommodities",lstRCommodities);
		request.setAttribute("lstCComments",lstCComments);
		request.setAttribute("pageNuber",index);*/
		

		HttpSession session=request.getSession();
		User user = (User) session.getAttribute("user");
		SendByUserCookie userLike=new SendByUserCookie(commodityBiz);
		List<Commodities>lstLike=null;
		if(user!=null){
			lstLike=userLike.findUserLike(user.getUserName(), request);
		}
		request.setAttribute("lstLike", lstLike);
		
		
		if (TransactionManager.connection == null)
			TransactionManager.connection = new ConnectionManager()
					.openConnection();
		List<Order> lstUnfinishedOrdersByUser=new ArrayList<Order>();
		if(user!=null){
			IOrderBiz biz = new OrderBizImpl();
			lstUnfinishedOrdersByUser = biz.searchUnfinshedInfByUserId(user);
		}
		request.setAttribute("lstUnfinishedOrdersByUser",
				lstUnfinishedOrdersByUser);
		//跳转？s上
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Market/Detail.jsp?comId="+comId);
		dispatcher.forward(request, response);
		
	}

}
