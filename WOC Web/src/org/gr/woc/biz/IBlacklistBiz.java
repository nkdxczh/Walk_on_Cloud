package org.gr.woc.biz;

import java.util.List;

import org.gr.woc.po.BlackList;

public interface IBlacklistBiz {
	public abstract boolean add(BlackList blacklist);
	public abstract boolean cancel(BlackList blacklist);
	public abstract boolean update(BlackList blacklist);
	public abstract List<BlackList> searchAll();
}
