package org.gr.wocandroid.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Commodities implements Serializable {
private int comId;
private int ownerId;
private String ownerName;
private String nickName;
private String comName;
private int property;
private String comTypeName;
private double price;
private String comRegion;
private String describe;
private int status;
private Date releaseTime;
private Date offTime;
private int requiredScore;
private String picturePath;
private String desire;
private int focusNumber;
private int pageCount;
@Override
public String toString() {
	return "Commodities [comId=" + comId + ", ownerId=" + ownerId
			+ ", ownerName=" + ownerName + ", nickName=" + nickName
			+ ", comName=" + comName + ", property=" + property
			+ ", comTypeName=" + comTypeName + ", price=" + price
			+ ", comRegion=" + comRegion + ", describe=" + describe
			+ ", status=" + status + ", releaseTime=" + releaseTime
			+ ", offTime=" + offTime + ", requiredScore=" + requiredScore
			+ ", picturePath=" + picturePath + ", desire=" + desire
			+ ", focusNumber=" + focusNumber + ", pageCount=" + pageCount + "]";
}
public int getComId() {
	return comId;
}
public void setComId(int comId) {
	this.comId = comId;
}
public int getOwnerId() {
	return ownerId;
}
public void setOwnerId(int ownerId) {
	this.ownerId = ownerId;
}
public String getOwnerName() {
	return ownerName;
}
public void setOwnerName(String ownerName) {
	this.ownerName = ownerName;
}
public String getNickName() {
	return nickName;
}
public void setNickName(String nickName) {
	this.nickName = nickName;
}
public String getComName() {
	return comName;
}
public void setComName(String comName) {
	this.comName = comName;
}
public int getProperty() {
	return property;
}
public void setProperty(int property) {
	this.property = property;
}
public String getComTypeName() {
	return comTypeName;
}
public void setComTypeName(String comTypeName) {
	this.comTypeName = comTypeName;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public String getComRegion() {
	return comRegion;
}
public void setComRegion(String comRegion) {
	this.comRegion = comRegion;
}
public String getDescribe() {
	return describe;
}
public void setDescribe(String describe) {
	this.describe = describe;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public Date getReleaseTime() {
	return releaseTime;
}
public void setReleaseTime(Date releaseTime) {
	this.releaseTime = releaseTime;
}
public Date getOffTime() {
	return offTime;
}
public void setOffTime(Date offTime) {
	this.offTime = offTime;
}
public int getRequiredScore() {
	return requiredScore;
}
public void setRequiredScore(int requiredScore) {
	this.requiredScore = requiredScore;
}
public String getPicturePath() {
	return picturePath;
}
public void setPicturePath(String picturePath) {
	this.picturePath = picturePath;
}
public String getDesire() {
	return desire;
}
public void setDesire(String desire) {
	this.desire = desire;
}
public int getFocusNumber() {
	return focusNumber;
}
public void setFocusNumber(int focusNumber) {
	this.focusNumber = focusNumber;
}
public int getPageCount() {
	return pageCount;
}
public void setPageCount(int pageCount) {
	this.pageCount = pageCount;
}
public Commodities() {
	super();
	// TODO Auto-generated constructor stub
}

}
