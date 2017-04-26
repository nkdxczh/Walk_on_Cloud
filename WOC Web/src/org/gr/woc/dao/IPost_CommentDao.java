package org.gr.woc.dao;


import java.util.List;

import org.gr.woc.po.Post;
import org.gr.woc.po.Post_Comment;
import org.gr.woc.po.User;

public interface IPost_CommentDao {

	public abstract int insert(final Post_Comment comment);
	public abstract int delete(final Post_Comment comment);
	public abstract List<Post_Comment> selectByUserId(final User user);
	public abstract List<Post_Comment> selectByPostId(final Post post);
	public abstract List<Post_Comment> selectByComId(final Post post,final int comdid);
	
}
