package org.gr.woc.biz;

import java.util.List;

import org.gr.woc.po.Detail_Inf;
import org.gr.woc.po.User;
import org.gr.woc.vo.Commodities;
import org.gr.woc.vo.Friends;

public interface IUserInfBiz {
Detail_Inf searchInfById(int userId);
boolean addDetail_Inf(Detail_Inf detail_Inf);
User searchByName(final String userName);
boolean addUser(final User user);
boolean applyAttention(final int userId,final int comId);
boolean changePassword(int userId,String nPassword);
boolean changeDetailInformation(Detail_Inf detail_inf);
boolean applyFriend(final int userId,final User friend);
List<Friends> searchFriendInformation(int userId);
Friends searchFriendInformation(final String friendName);
boolean cancelFriend(final int userId,final String friendName);
boolean cancelFriendById(final int relationshipId);
List<Commodities> searchAttention(final int userId);
boolean cancelAttention(final int userId,final int comId);
boolean cancelCommodities(final int comId);
 User searchById(int userId);
 List<Integer> seachAllUserId();
}
