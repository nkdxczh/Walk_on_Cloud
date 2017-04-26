package org.gr.woc.servletandroid;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gr.woc.biz.ICommodityBiz;
import org.gr.woc.biz.impl.CommodityBizImpl;
import org.gr.woc.dao.IPictureDao;
import org.gr.woc.dao.impl.PictureDaoImpl;
import org.gr.woc.po.Picture;
import org.gr.woc.vo.Commodities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class ShowCommodityInfoServletA
 */
public class ShowCommodityInfoServletA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowCommodityInfoServletA() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		request.getParameter("comId");
		ICommodityBiz commodityBiz = new CommodityBizImpl();
		int comId=Integer.parseInt(request.getParameter("comId"));
		IPictureDao pictureDao=new PictureDaoImpl(); 
		List<Picture> lstPicture=pictureDao.selectByComId(comId);
		if(lstPicture.size()>=1)lstPicture.remove(0);
		request.setAttribute("lstPicture",lstPicture );
		Commodities commodity=commodityBiz.searchByComId(comId);
		response.getWriter().println(gson.toJson(commodity));
	}

}
