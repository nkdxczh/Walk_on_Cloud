package org.gr.woc.dao;

import java.util.List;

import org.gr.woc.vo.Friends;

public interface IFriendsDao {
public abstract List<Friends> selectAll(int userId);
public abstract Friends selsectByName(String friendName);
}
