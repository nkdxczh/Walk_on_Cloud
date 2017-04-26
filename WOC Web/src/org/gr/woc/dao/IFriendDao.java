package org.gr.woc.dao;

import java.util.List;

import org.gr.woc.po.Friend;

public interface IFriendDao {

	public abstract int insert(final Friend friend);
	public abstract int deleteById(final  int relationshipId);
	public abstract int deleteByFriendName(final int userId,final String friendName);
	public abstract int update(final Friend friend);
	public abstract List<Friend> selectAll();
	public abstract Friend selectById(final int relationshipId);
	public abstract List<Friend> selectFriendshipById(final int userId,final int friendId);
}

