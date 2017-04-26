package org.gr.woc.dao;

import java.util.List;

import org.gr.woc.po.Commodity;

public interface ICommodityDao {
	public abstract int insert(final Commodity commodity);
	public abstract int deleteById(final int comId);
	public abstract int update(final Commodity commodity);
	public abstract List<Commodity> selectAll();
	public abstract Commodity selectById(final int comId);
}
