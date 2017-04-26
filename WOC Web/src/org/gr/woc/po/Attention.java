package org.gr.woc.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Attention implements Serializable {
private int attId;
private int comId;
private int userId;
public Attention() {
	super();
	// TODO Auto-generated constructor stub
}
public Attention(int attId, int comId, int userId) {
	super();
	this.attId = attId;
	this.comId = comId;
	this.userId = userId;
}
public int getAttId() {
	return attId;
}
public void setAttId(int attId) {
	this.attId = attId;
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
@Override
public String toString() {
	return "Attention [attId=" + attId + ", comId=" + comId + ", userId="
			+ userId + "]";
}



}
