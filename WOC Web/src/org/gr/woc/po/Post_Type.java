package org.gr.woc.po;

public class Post_Type {
	private int postTypeId;
	private String postTypeName;
	public int getPostTypeId() {
		return postTypeId;
	}
	public void setPostTypeId(int postTypeId) {
		this.postTypeId = postTypeId;
	}
	public String getPostTypeName() {
		return postTypeName;
	}
	public void setPostTypeName(String postTypeName) {
		this.postTypeName = postTypeName;
	}
	@Override
	public String toString() {
		return "Post_Type [postTypeId=" + postTypeId + ", postTypeName="
				+ postTypeName + "]";
	}
	
	

}
