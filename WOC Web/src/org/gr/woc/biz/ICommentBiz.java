package org.gr.woc.biz;

import java.util.List;

import org.gr.woc.po.Post;
import org.gr.woc.po.Post_Comment;
import org.gr.woc.po.User;

public interface ICommentBiz {
	public abstract boolean releaseComment(final Post_Comment comment);
	public abstract boolean cancelComment(final Post_Comment comment);
	public abstract List<Post_Comment> searchInfByUserId(final User user);
	public abstract List<Post_Comment> searchInfByPostId(final Post post);
	public abstract void infSearchByLayerId(final Post post,int postcomId,List<Post_Comment> lst);
}
