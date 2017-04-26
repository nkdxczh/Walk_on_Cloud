package org.gr.woc.po;

public class Score_Record {
	private int scoreRecordId,postId,userId;

	public int getScoreRecordId() {
		return scoreRecordId;
	}

	public void setScoreRecordId(int scoreRecordId) {
		this.scoreRecordId = scoreRecordId;
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

	@Override
	public String toString() {
		return "Score_Record [scoreRecordId=" + scoreRecordId + ", postId="
				+ postId + ", userId=" + userId + "]";
	}

	
}
