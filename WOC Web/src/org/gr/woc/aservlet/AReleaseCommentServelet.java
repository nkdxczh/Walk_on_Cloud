package org.gr.woc.aservlet;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class ReleaseComment
 */
public class AReleaseCommentServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AReleaseCommentServelet() {
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
		request.setCharacterEncoding("UTF-8");
		if (TransactionManager.connection == null)
			TransactionManager.connection = new ConnectionManager()
					.openConnection();

		// --------------------接受数据JSon-----------------
		// 获取终端传入的JSon数据
		String comment_data = request.getParameter("comment_data");
		// 反序列化形成一个Users对象
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss")
				.create();
		Post_Comment pc = gson.fromJson(comment_data, Post_Comment.class);

		ICommentBiz biz = new CommentBizImpl();
		Post_Comment comment = new Post_Comment();
		int postId = pc.getPostId();
		Post post2 = new Post();
		post2.setPostId(postId);
		Post post = new PostBizImpl().searchInfById(post2);
		/*User user = (User) request.getSession().getAttribute("user");
		String nickname = ((Detail_Inf) request.getSession()
				.getAttribute("inf")).getNickName();*/
		String content = pc.getPostComContent();
		int postreplyid = pc.getPostReplyId();
		comment.setPostId(postId);
		comment.setUserId(Integer.parseInt(request.getParameter("userid")));
		comment.setPostComContent(content);
		comment.setPostReplyId(postreplyid);
		biz.releaseComment(comment);
		/*Test test = new Test();
		System.out.println("以下是回复的帖子的主人的ID");
		System.out.println(post.getUserId());
		String s = nickname + "回复了您名为" + post.getPostName() + "的帖子";
		test.sendMessageAuto(String.valueOf(post.getUserId()), s);
		test.sendMessageAuto(String.valueOf(postreplyid), nickname + "回复了您在名为"
				+ post.getPostName() + "的帖子中的评论");
		System.out.println("这里是releasecomment");
		System.out.println(s);;*/
	}

}
