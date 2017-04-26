package org.gr.wocandroid.po;

import java.util.Date;

public class Order {
	private int orderId,comId,userId;
	private Date ordSubTime,ordSucTime,offTime,releaseTime;
	private String orderName,orderLocation,orderEmail,orderPhone;
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getOrderLocation() {
		return orderLocation;
	}
	public void setOrderLocation(String orderLocation) {
		this.orderLocation = orderLocation;
	}
	public String getOrderEmail() {
		return orderEmail;
	}
	public void setOrderEmail(String orderEmail) {
		this.orderEmail = orderEmail;
	}
	public String getOrderPhone() {
		return orderPhone;
	}
	public void setOrderPhone(String orderPhone) {
		this.orderPhone = orderPhone;
	}
	public Date getOffTime() {
		return offTime;
	}
	public void setOffTime(Date offTime) {
		this.offTime = offTime;
	}
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
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
	public int getRequrScore() {
		return requrScore;
	}
	public void setRequrScore(int requrScore) {
		this.requrScore = requrScore;
	}
	private double price;
	private String buyerUserName,comName,sellerUserName,comRegion,describe;
	private int comType,status,requrScore;
	public int getComType() {
		return comType;
	}
	public void setComType(int comType) {
		this.comType = comType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getSellerUserName() {
		return sellerUserName;
	}
	public void setSellerUserName(String sellerUserName) {
		this.sellerUserName = sellerUserName;
	}
	public String getBuyerUserName() {
		return buyerUserName;
	}
	public void setBuyerUserName(String userName) {
		buyerUserName = userName;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
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
	public Date getOrdSubTime() {
		return ordSubTime;
	}
	public void setOrdSubTime(Date ordSubTime) {
		this.ordSubTime = ordSubTime;
	}
	public Date getOrdSucTime() {
		return ordSucTime;
	}
	public void setOrdSucTime(Date ordSucTime) {
		this.ordSucTime = ordSucTime;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", comId=" + comId + ", userId="
				+ userId + ", ordSubTime=" + ordSubTime + ", ordSucTime="
				+ ordSucTime + ", offTime=" + offTime + ", releaseTime="
				+ releaseTime + ", orderName=" + orderName + ", orderLocation="
				+ orderLocation + ", orderEmail=" + orderEmail
				+ ", orderPhone=" + orderPhone + ", price=" + price
				+ ", buyerUserName=" + buyerUserName + ", comName=" + comName
				+ ", sellerUserName=" + sellerUserName + ", comRegion="
				+ comRegion + ", describe=" + describe + ", comType=" + comType
				+ ", status=" + status + ", requrScore=" + requrScore + "]";
	}
	

}
