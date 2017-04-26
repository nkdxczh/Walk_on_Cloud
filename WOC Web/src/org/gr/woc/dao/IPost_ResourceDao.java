package org.gr.woc.dao;

import java.util.List;

import org.gr.woc.po.Post;
import org.gr.woc.po.Post_Resource;

public interface IPost_ResourceDao {

	public abstract int insert (final Post_Resource resource);
	public abstract List<Post_Resource> selectById(final Post post);
}
