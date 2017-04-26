package org.gr.woc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gr.woc.biz.ICommentBiz;
import org.gr.woc.biz.impl.CommentBizImpl;
import org.gr.woc.biz.impl.PostBizImpl;
import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.TransactionManager;
import org.gr.woc.po.Detail_Inf;
import org.gr.woc.po.Post;
import org.gr.woc.po.Post_Comment;
import org.gr.woc.po.User;
import org.gr.woc.tuisong.Test;

/**
 * Servlet implementation class ReleaseComment
 */
public class ReleaseCommentServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReleaseCommentServelet() {
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
		if(TransactionManager.connection==null)
			TransactionManager.connection=new ConnectionManager().openConnection();
		ICommentBiz biz=new CommentBizImpl();
		Post_Comment comment=new Post_Comment();
		int postId=Integer.parseInt(request.getParameter("postid"));
		Post post2=new Post();
		post2.setPostId(postId);
		Post post=new PostBizImpl().searchInfById(post2);
		User user=(User)request.getSession().getAttribute("user");
		String nickname=((Detail_Inf)request.getSession().getAttribute("inf")).getNickName();
		String content=request.getParameter("commentContent");
		int postreplyid=Integer.parseInt((String)request.getParameter("postreplyid"));
		comment.setPostId(postId);
		comment.setUserId(user.getUserId());
		comment.setPostComContent(content);
		comment.setPostReplyId(postreplyid);
		biz.releaseComment(comment);
		Test test=new Test();
		System.out.println("以下是回复的帖子的主人的ID");
		System.out.println(post.getUserId());
		String s=nickname+"回复了您名为"+post.getPostName()+"的帖子";
		test.sendMessageAuto(String.valueOf(post.getUserId()),
				s);
		test.sendMessageAuto(String.valueOf(postreplyid),
				nickname+"回复了您在名为"+
						post.getPostName()+"的帖子中的评论");
		System.out.println("这里是releasecomment");
		System.out.println(s);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/forum/post.jsp?postid="+postId);
		dispatcher.forward(request, response);
	}

}
