package org.gr.woc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gr.woc.biz.IUserInfBiz;
import org.gr.woc.biz.impl.UserInfBizImpl;

/**
 * Servlet implementation class BatchCancelCommoditiesServlet
 */
public class BatchCancelCommoditiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BatchCancelCommoditiesServlet() {
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
		// TODO Auto-generated method stubS
		request.setCharacterEncoding("UTF-8");
		IUserInfBiz userInfBiz = new UserInfBizImpl();
		String type=request.getParameter("type");
		System.out.println(type);
		switch (type) {
		case "1":
		{
			String[] sComId=request.getParameterValues("sc");
			for (int i = 0; i < sComId.length; i++) {
				userInfBiz.cancelCommodities(Integer.parseInt(sComId[i]));
			}
			break;
		}
		case "2":
		{
			String[] sComId=request.getParameterValues("ec");
			for (int i = 0; i < sComId.length; i++) {
				userInfBiz.cancelCommodities(Integer.parseInt(sComId[i]));
			}
			break;
		}
		case "3":
		{
			String[] sComId=request.getParameterValues("fc");
			for (int i = 0; i < sComId.length; i++) {
				userInfBiz.cancelCommodities(Integer.parseInt(sComId[i]));
			}
			break;
		}

		default:
			break;
		}
		
		
		String url="jsp/forum/user_marif.jsp";
		response.sendRedirect(url);
		
	}

}
