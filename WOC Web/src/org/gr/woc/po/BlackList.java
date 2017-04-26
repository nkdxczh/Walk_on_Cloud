package org.gr.woc.po;

import java.util.Date;



public class BlackList {
	private int bLId,UserId,managerId,bLLevel,lastTime;
	private String userName,managerName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public int getLastTime() {
		return lastTime;
	}
	public void setLastTime(int lastTime) {
		this.lastTime = lastTime;
	}
	private Date BLTime;
	public int getBLId() {
		return bLId;
	}
	public void setBLId(int bLId) {
		this.bLId = bLId;
	}
	public int getUserId() {
		return UserId;
	}
	public BlackList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public int getBLLevel() {
		return bLLevel;
	}
	public void setBLLevel(int bLLevel) {
		this.bLLevel = bLLevel;
	}
	public Date getBLTime() {
		return BLTime;
	}
	public void setBLTime(Date bLTime) {
		BLTime = bLTime;
	}
	@Override
	public String toString() {
		return "BlackList [BLId=" + bLId + ", UserId=" + UserId
				+ ", ManagerId=" + managerId + ", BLLevel=" + bLLevel
				+ ", LastTime=" + lastTime + ", UserName=" + userName
				+ ", ManagerName=" + managerName + ", BLTime=" + BLTime + "]";
	}
	

}
