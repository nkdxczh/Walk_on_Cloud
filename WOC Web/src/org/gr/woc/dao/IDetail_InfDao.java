package org.gr.woc.dao;

import java.util.List;

import org.gr.woc.po.Detail_Inf;

public interface IDetail_InfDao {
	public abstract int insert(final Detail_Inf detail_Inf);
	public abstract int deleteById(final int infId);
	public abstract int update(final Detail_Inf detail_Inf);
	public abstract List<Detail_Inf> selectAll();
	public abstract Detail_Inf selectById(final int infId);
	public abstract Detail_Inf selsectByUserId(final int userId);
}
