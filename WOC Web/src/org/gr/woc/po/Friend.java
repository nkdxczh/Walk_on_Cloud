package org.gr.woc.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Friend implements Serializable {
private int relationShipId;
private int userId;
private int friendId;
public Friend() {
	super();
	// TODO Auto-generated constructor stub
}
public Friend(int relationShipId, int userId, int friendId) {
	super();
	this.relationShipId = relationShipId;
	this.userId = userId;
	this.friendId = friendId;
}
public int getRelationShipId() {
	return relationShipId;
}
public void setRelationShipId(int relationShipId) {
	this.relationShipId = relationShipId;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public int getFriendId() {
	return friendId;
}
public void setFriendId(int friendId) {
	this.friendId = friendId;
}
@Override
public String toString() {
	return "Friend [relationShipId=" + relationShipId + ", userId=" + userId
			+ ", friendId=" + friendId + "]";
}


}
