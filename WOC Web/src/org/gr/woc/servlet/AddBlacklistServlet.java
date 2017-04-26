package org.gr.woc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gr.woc.biz.IBlacklistBiz;
import org.gr.woc.biz.impl.BlacklistBizImpl;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.BlackList;
import org.gr.woc.po.User;

/**
 * Servlet implementation class AddBlacklistServlet
 * 
 * 增加黑名单的servlet
 */
public class AddBlacklistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBlacklistServlet() {
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
		IBlacklistBiz biz=new BlacklistBizImpl();
		BlackList blackList=new BlackList();
		User user=(User)request.getSession().getAttribute("user");
		int userid=user.getUserId();
		int managerid=Integer.parseInt(request.getParameter("managerid"));
		//管理员的id
		int bllevel=Integer.parseInt(request.getParameter("bllevel"));
		//黑名单的等级
		int lastime=Integer.parseInt(request.getParameter("lastime"));
		//持续时间
		blackList.setBLLevel(bllevel);
		blackList.setManagerId(managerid);
		blackList.setUserId(userid);
		blackList.setLastTime(lastime);
		biz.add(blackList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/forum/forindex.jsp");
		dispatcher.forward(request, response);
		//由于我不清楚改往哪里跳转，所有servlet中我每次跳转都是往forindex跳，你自己改一下
	}

}
