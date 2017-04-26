package org.gr.woc.dao;

import java.util.List;

import org.gr.woc.vo.Commodities;

public interface ICommoditiesDao {
	public abstract List<Commodities> selectByComName(final String comName,
			final int index, final int pageSize,final String order);

	public abstract List<Commodities> selectByComType(final String typeName,
			final int index, final int pageSize,final String order);

	public abstract List<Commodities> selectByOwnerName(final String ownerName,
			final int index, final int pageSize,final String order);

	public abstract List<Commodities> selectByReleaseTime(final int index,
			final int pageSize);

	public abstract Commodities selectByComId(final int comId);

	public abstract List<Commodities> selectByUserId(final int userId,
			final int index, final int pageSize);

	public abstract List<Commodities> selectByComRegion(final String region,
			final int index, final int pageSize,final String order);

	public abstract List<Commodities> selectCommodities(final int index,
			final int pageSize);

	public abstract List<Commodities> selectCommoditiesByType(final int key,
			final int index, final int pageSize,final String order);

	public abstract List<Commodities> selectCommoditiesByPrice(final int key,
			final int index, final int pageSize,final String order);

	public abstract List<Commodities> selectCommoditiesByTime(final int key,
			final int index, final int pageSize,final String order);

	public abstract List<Commodities> selectCommoditiesByFocus(final int key,
			final int index, final int pageSize,final String order);
}
