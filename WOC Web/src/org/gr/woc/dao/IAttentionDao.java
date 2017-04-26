package org.gr.woc.dao;

import java.util.List;

import org.gr.woc.po.Attention;
import org.gr.woc.vo.Commodities;

public interface IAttentionDao {
	public abstract int insert(final Attention attention);
	public abstract int deleteById(final int attId);
	public abstract int update(final Attention attention);
	public abstract List<Attention> selectAll();
	public abstract Attention selectById(final int attId);
	public abstract List<Commodities> selectByUserId(final int userId);
	public abstract int deleteByIds(final int userId,final int comId);
	public abstract List<Attention> selectByIds(final int userId,final int comId);
}
