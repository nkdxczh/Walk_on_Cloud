package org.gr.wocandroid.po;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Post_Comment implements Comparable{
	private int postComId,postId,userId,postReplyId,userLevel;
	private String postComContent,releaseUserName,userPhoto,replyUserName,postName;
	private Date  postComTime;
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public int getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}
	public String getReleaseUserName() {
		return releaseUserName;
	}
	public void setReleaseUserName(String releaseUserName) {
		this.releaseUserName = releaseUserName;
	}
	public String getReplyUserName() {
		return replyUserName;
	}
	public void setReplyUserName(String replyUserName) {
		this.replyUserName = replyUserName;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	public Date getPostComTime() {
		return postComTime;
	}
	public void setPostComTime(Date postComTime) {
		this.postComTime = postComTime;
	}
	public int getPostComId() {
		return postComId;
	}
	public void setPostComId(int postComId) {
		this.postComId = postComId;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPostReplyId() {
		return postReplyId;
	}
	public void setPostReplyId(int postReplyId) {
		this.postReplyId = postReplyId;
	}
	@Override
	public String toString() {
		return "Post_Comment [postComId=" + postComId + ", postId=" + postId
				+ ", userId=" + userId + ", postReplyId=" + postReplyId
				+ ", userLevel=" + userLevel + ", postComContent="
				+ postComContent + ", releaseUserName=" + releaseUserName
				+ ", userPhoto=" + userPhoto + ", replyUserName="
				+ replyUserName + ", postName=" + postName + ", postComTime="
				+ postComTime + "]";
	}
	public Post_Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPostComContent() {
		return postComContent;
	}
	public void setPostComContent(String postComContent) {
		this.postComContent = postComContent;
	}
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return this.getPostComId()-((Post_Comment)o).getPostComId();
	}

}
