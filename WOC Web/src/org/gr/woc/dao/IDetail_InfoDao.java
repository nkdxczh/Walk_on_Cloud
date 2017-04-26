package org.gr.woc.dao;

import java.util.List;

import org.gr.woc.po.Detail_Info;

public interface IDetail_InfoDao {
	public abstract int insert(final Detail_Info detail_Info);
	public abstract int deleteById(final int infId);
	public abstract int update(final Detail_Info detail_Info);
	public abstract List<Detail_Info> selectAll();
	public abstract Detail_Info selectById(final int infId);
	public abstract Detail_Info selsectByUserId(final int userId);
}
