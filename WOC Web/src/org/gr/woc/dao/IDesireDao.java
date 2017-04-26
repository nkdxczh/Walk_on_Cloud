package org.gr.woc.dao;

import java.util.List;

import org.gr.woc.po.Desire;

public interface IDesireDao {
	public abstract int insert(final Desire desire);
	public abstract int deleteById(final int desId);
	public abstract int update(final Desire desire);
	public abstract List<Desire> selectAll();
	public abstract Desire selectById(final int desId);
}
