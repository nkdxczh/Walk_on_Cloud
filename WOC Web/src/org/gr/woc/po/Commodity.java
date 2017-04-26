package org.gr.woc.po;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Commodity implements Serializable {
private int comId;
private int userId;
private String comName;
private int property;
private int type;
private double price;
private String comRegion;
private String describe;
private int status;
private Date releaseTime;
private Date OffTime;
private int requiredScore;
public Commodity() {
	super();
	// TODO Auto-generated constructor stub
}
public Commodity(int comId, int userId, String comName, int property, int type,
		double price, String comRegion, String describe, int status,
		Date releaseTime, Date offTime, int requiredScore) {
	super();
	this.comId = comId;
	this.userId = userId;
	this.comName = comName;
	this.property = property;
	this.type = type;
	this.price = price;
	this.comRegion = comRegion;
	this.describe = describe;
	this.status = status;
	this.releaseTime = releaseTime;
	OffTime = offTime;
	this.requiredScore = requiredScore;
}
public int getComId() {
	return comId;
}
public void setComId(int comId) {
	this.comId = comId;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
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
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
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
	return OffTime;
}
public void setOffTime(Date offTime) {
	OffTime = offTime;
}
public int getRequiredScore() {
	return requiredScore;
}
public void setRequiredScore(int requiredScore) {
	this.requiredScore = requiredScore;
}
@Override
public String toString() {
	return "Commodity [comId=" + comId + ", userId=" + userId + ", comName="
			+ comName + ", property=" + property + ", type=" + type
			+ ", price=" + price + ", comRegion=" + comRegion + ", describe="
			+ describe + ", status=" + status + ", releaseTime=" + releaseTime
			+ ", OffTime=" + OffTime + ", requiredScore=" + requiredScore + "]";
}


}
