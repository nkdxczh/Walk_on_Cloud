package org.gr.woc.dao;

import java.util.List;

import org.gr.woc.po.Post_Type;

public interface IPost_TypeDao {
	public abstract Post_Type selectById(final Post_Type post_Type);
	public abstract List<Post_Type> selectAll();
	public abstract List<Post_Type> selectByBankuai(int bankuaihao);
}
