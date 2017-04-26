package org.gr.woc.po;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CComment implements Serializable{
	private String comComment;
	private int comCommentId;
	private int comId;
	private int userId;
	private Date releaseTime;
	public CComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CComment(String comComment, int comCommentId, int comId, int userId) {
		super();
		this.comComment = comComment;
		this.comCommentId = comCommentId;
		this.comId = comId;
		this.userId = userId;
	}
	public String getComComment() {
		return comComment;
	}
	public void setComComment(String comComment) {
		this.comComment = comComment;
	}
	public int getComCommentId() {
		return comCommentId;
	}
	public void setComCommentId(int comCommentId) {
		this.comCommentId = comCommentId;
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
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	@Override
	public String toString() {
		return "CComment [comComment=" + comComment + ", comCommentId="
				+ comCommentId + ", comId=" + comId + ", userId=" + userId
				+ ", releaseTime=" + releaseTime + "]";
	}


}
