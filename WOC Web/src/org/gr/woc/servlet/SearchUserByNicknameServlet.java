package org.gr.woc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gr.woc.biz.ICommentBiz;
import org.gr.woc.biz.IOrderBiz;
import org.gr.woc.biz.IPostBiz;
import org.gr.woc.biz.IUserInfBiz;
import org.gr.woc.biz.impl.CommentBizImpl;
import org.gr.woc.biz.impl.OrderBizImpl;
import org.gr.woc.biz.impl.PostBizImpl;
import org.gr.woc.biz.impl.UserInfBizImpl;
import org.gr.woc.dao.IFriendsDao;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Detail_Inf;
import org.gr.woc.po.Order;
import org.gr.woc.po.Post;
import org.gr.woc.po.Post_Comment;
import org.gr.woc.po.User;
import org.gr.woc.vo.Friends;

/**
 * Servlet implementation class SearchUserByNicknameServlet
 */
public class SearchUserByNicknameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchUserByNicknameServlet() {
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
		User user=(User)request.getSession().getAttribute("user");
		IPostBiz postbiz=new PostBizImpl();
		List<Post> lstposts=postbiz.searchInfByUserId(user);
		ICommentBiz commentBiz=new CommentBizImpl();
		List<Post_Comment> lstcomments=commentBiz.searchInfByUserId(user);
		IOrderBiz orderBiz=new OrderBizImpl();
		List<Order> lstorders=orderBiz.searchFinshedInfByUserId(user);
		IUserInfBiz userInfBiz=new UserInfBizImpl();
		List<Friends> lstfriends=userInfBiz.searchFriendInformation(user.getUserId());
		Detail_Inf detailinf=userInfBiz.searchInfById(user.getUserId());
		request.setAttribute("lstposts", lstposts);
		request.setAttribute("lstcomments", lstcomments);
		request.setAttribute("lstorders", lstorders);
		request.setAttribute("lstfriends", lstfriends);
		request.setAttribute("detailinf", detailinf);
	}

}
