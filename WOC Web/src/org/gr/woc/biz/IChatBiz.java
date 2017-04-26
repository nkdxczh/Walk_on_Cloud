package org.gr.woc.biz;

import java.util.List;

import org.gr.woc.po.Chat;
import org.gr.woc.po.User;

public interface IChatBiz {
	public abstract boolean add(final Chat chat);
	public abstract List<Chat> searchByUserId(final User user,final User user2);

}
