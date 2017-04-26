package org.gr.woc.dao;

import java.util.List;

import org.gr.woc.po.CComment;

public interface ICCommentDao {
	public abstract int insert(final CComment cComment);
	public abstract int deleteById(final int comCommentId);
	public abstract int update(final CComment cComment);
	public abstract List<CComment> selectAll();
	public abstract List<CComment> selectByComId(final int comId);
	public abstract CComment selectById(final int comCommentId);
}
