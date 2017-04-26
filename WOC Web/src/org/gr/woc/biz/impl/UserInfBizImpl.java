package org.gr.woc.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.gr.woc.biz.IUserInfBiz;
import org.gr.woc.dao.IAttentionDao;
import org.gr.woc.dao.ICommodityDao;
import org.gr.woc.dao.IDetail_InfDao;
import org.gr.woc.dao.IFriendDao;
import org.gr.woc.dao.IFriendsDao;
import org.gr.woc.dao.IUserDao;
import org.gr.woc.dao.impl.AttentionDaoImpl;
import org.gr.woc.dao.impl.CommodityDaoImpl;
import org.gr.woc.dao.impl.Detail_InfDaoImpl;
import org.gr.woc.dao.impl.FriendDaoImpl;
import org.gr.woc.dao.impl.FriendsDaoImpl;
import org.gr.woc.dao.impl.UserDaoImpl;
import org.gr.woc.po.Attention;
import org.gr.woc.po.Detail_Inf;
import org.gr.woc.po.Friend;
import org.gr.woc.po.User;
import org.gr.woc.vo.Commodities;
import org.gr.woc.vo.Friends;

public class UserInfBizImpl implements IUserInfBiz {
	private IDetail_InfDao detail_InfDao;
	private IUserDao userDao;
	private IFriendDao friendDao;
	private IFriendsDao friendsDao;
	private IAttentionDao attentionDao;
	private ICommodityDao commodityDao;

	public UserInfBizImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.detail_InfDao = new Detail_InfDaoImpl();
		this.userDao = new UserDaoImpl();
		this.friendDao = new FriendDaoImpl();
		this.friendsDao = new FriendsDaoImpl();
		this.attentionDao = new AttentionDaoImpl();
		this.commodityDao = new CommodityDaoImpl();
	}

	@Override
	public Detail_Inf searchInfById(int userId) {
		// TODO Auto-generated method stub
		Detail_Inf detail_Info = detail_InfDao.selsectByUserId(userId);
		return detail_Info;
	}

	@Override
	public boolean changePassword(int userId, String nPassword) {
		// TODO Auto-generated method stub
		return userDao.updatePassword(userId, nPassword);
	}

	@Override
	public boolean changeDetailInformation(Detail_Inf detail_Info) {
		// TODO Auto-generated method stub
		Detail_Inf detail_Inf=detail_InfDao.selsectByUserId(detail_Info.getUserId());
		detail_Info.setInfId(detail_Inf.getInfId());
		if(detail_Info.getNickName()==null)
		{
			detail_Info.setNickName(detail_Inf.getNickName());
		}
		if(detail_Info.getHobby()==null)
		{
			detail_Info.setHobby(detail_Inf.getHobby());
		}
		if(detail_Info.getGender()==null)
		{
			detail_Info.setGender(detail_Inf.getGender());
		}
		if(detail_Info.getMajor()==null)
		{
			detail_Info.setMajor(detail_Inf.getMajor());
		}
		if(detail_Info.getLocation()==null)
		{
			detail_Info.setLocation(detail_Inf.getLocation());
		}
		if(detail_Info.getPhone()==null)
		{
			detail_Info.setPhone(detail_Inf.getPhone());
		}
		if(detail_Info.getEmail()==null)
		{
			detail_Info.setEmail(detail_Inf.getEmail());
		}
		int affectedRows = detail_InfDao.update(detail_Info);
		if (affectedRows > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean applyFriend(int userId, User friend) {
		// TODO Auto-generated method stub
		Friend friendRelationship = new Friend();
		friendRelationship.setUserId(userId);
		List<Friend> lstFriend = friendDao.selectFriendshipById(userId,
				friend.getUserId());
		if (lstFriend.size()==0) {
			friendRelationship.setFriendId(friend.getUserId());
			int affectedRows = friendDao.insert(friendRelationship);

			if (affectedRows > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	@Override
	public List<Friends> searchFriendInformation(int userId) {
		// TODO Auto-generated method stub
		List<Friends> lstFriends = new ArrayList<Friends>();
		lstFriends = friendsDao.selectAll(userId);
		return lstFriends;
	}

	@Override
	public Friends searchFriendInformation(String friendName) {
		// TODO Auto-generated method stub
		Friends friend = new Friends();
		friend = friendsDao.selsectByName(friendName);
		return friend;
	}

	@Override
	public boolean cancelFriend(int userId, String friendName) {
		// TODO Auto-generated method stub
		int affectedRows = friendDao.deleteByFriendName(userId, friendName);
		if (affectedRows > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean cancelFriendById(int relationshipId) {
		// TODO Auto-generated method stub
		int affectedRows = friendDao.deleteById(relationshipId);
		if (affectedRows > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public User searchByName(String userName) {
		// TODO Auto-generated method stub
		return userDao.selsectByName(userName);
	}

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return userDao.insert(user) > 0 ? true : false;
	}

	@Override
	public boolean addDetail_Inf(Detail_Inf detail_Info) {
		// TODO Auto-generated method stub
		return detail_InfDao.insert(detail_Info) > 0 ? true : false;
	}

	@Override
	public List<Commodities> searchAttention(int userId) {
		// TODO Auto-generated method stub
		return attentionDao.selectByUserId(userId);
	}

	@Override
	public boolean cancelAttention(int userId, int comId) {
		// TODO Auto-generated method stub
		return attentionDao.deleteByIds(userId, comId) > 0 ? true : false;
	}

	@Override
	public boolean cancelCommodities(int comId) {
		// TODO Auto-generated method stub
		return commodityDao.deleteById(comId) > 0 ? true : false;
	}

	@Override
	public boolean applyAttention(int userId, int comId) {
		// TODO Auto-generated method stub
		Attention attention =new Attention();
		attention.setComId(comId);
		attention.setUserId(userId);
		IAttentionDao attentionDao = new AttentionDaoImpl();
		List<Attention> lstAttention= attentionDao.selectByIds(userId, comId);
		if (lstAttention.size()==0) {
			
			int affectedRows = attentionDao.insert(attention);

			if (affectedRows > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public List<Integer> seachAllUserId() {
		// TODO Auto-generated method stub
		List<Integer>toReturn=new ArrayList<Integer>();
		List<User>user=userDao.selectAll();
		for(int i=0;i<user.size();i++){
			toReturn.add(user.get(i).getUserId());
		}
		return toReturn;
	}

	@Override
	public User searchById(int userId) {
		// TODO Auto-generated method stub
		
		return userDao.selectById(userId);
	}

}
