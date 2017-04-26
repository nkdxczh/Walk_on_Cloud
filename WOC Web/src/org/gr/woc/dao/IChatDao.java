package org.gr.woc.dao;

import java.util.List;

import org.gr.woc.po.Chat;
import org.gr.woc.po.User;

public interface IChatDao {
	public abstract int insert(final Chat chat);
	public abstract List<Chat> selectByUserId(final User user,final User user2);

}
