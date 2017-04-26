package org.gr.woc.dao;

import java.sql.ResultSet;
import java.util.List;

import org.gr.woc.po.Post;
import org.gr.woc.po.User;
public interface IPostDao {

	 public abstract int update(final Post post);
	 public abstract int delete(final Post post);
	 public abstract int updateComTime(final Post post);
	 public abstract int insert(final Post post);
	 public abstract List<Post> infSearchByUserId(final User user);
	 public abstract int expressLike(final Post post,final int para);
	 public abstract List<Post>  refreshResult();
	 public abstract List<Post>  hotPosts();//今日热帖
	 public abstract List<Post>  hotPostsBybankuai(int bankuaiid);
	 public abstract List<Post> latastPosts(int bankuaiid);
	 public abstract List<Post> relatedPosts(int bankuaiid);
	 public abstract List<Post> selectPosts(String word);
	 public abstract ResultSet selectById(Post post);
	 public abstract int selectLatest();
}
