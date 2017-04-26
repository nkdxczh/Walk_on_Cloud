package org.gr.woc.dao;

import java.util.List;

import org.gr.woc.po.Commodity_Type;

public interface ICommodity_TypeDao {
	public abstract int insert(final Commodity_Type commodity_Type);
	public abstract int deleteById(final int comTypeId);
	public abstract int update(final Commodity_Type commodity_Type);
	public abstract List<Commodity_Type> selectAll();
	public abstract Commodity_Type selectById(final int comTypeId);
}
