package org.gr.wocandroid.po;

public class Post_Resource {
	private int resId,postId;
	private String resPath;
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getResPath() {
		return resPath;
	}
	public void setResPath(String resPath) {
		this.resPath = resPath;
	}
	public Post_Resource() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Post_Resource [ResId=" + resId + ", PostId=" + postId
				+ ", ResPath=" + resPath + "]";
	}

}
