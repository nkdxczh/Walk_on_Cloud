package org.gr.woc.biz;

import java.util.List;

import org.gr.woc.po.Post_Type;

public interface IPost_TypeBiz {

	public abstract Post_Type searchById(final Post_Type post_Type);
	public abstract List<Post_Type> searchAll();
}
