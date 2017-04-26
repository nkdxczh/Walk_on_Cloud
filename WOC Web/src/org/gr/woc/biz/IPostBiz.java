package org.gr.woc.biz;

import java.util.List;

import org.gr.woc.po.Post;
import org.gr.woc.po.User;

public interface IPostBiz {
	public abstract boolean releasePost(final Post post);
	public abstract boolean cancelPost(final Post post);
	public abstract boolean expressLike(final Post post,int para,int  userid);
	//需要对score_record操作
	public abstract List<Post> refreshResult();
	public abstract List<Post> latastPosts(int bankuaihao);
	public abstract List<Post> relatedPosts(int bankuaihao);
	public abstract List<Post> hotPosts();
	public abstract List<Post> hotPostsBybankuai(int bankuaihao);
	public abstract List<Post> searchInfByUserId(final User user);
	public abstract List<Post> searchInfResult(String word);
	public abstract Post searchInfById(final Post post);
	public int searchLatest();
}
