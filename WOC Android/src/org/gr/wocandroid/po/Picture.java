package org.gr.wocandroid.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Picture implements Serializable {
	private int pictureId;
	private int comId;
	private String path;

	public Picture() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Picture(int pictureId, int comId, String path) {
		super();
		this.pictureId = pictureId;
		this.comId = comId;
		this.path = path;
	}

	public int getPictureId() {
		return pictureId;
	}

	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}

	public int getComId() {
		return comId;
	}

	public void setComId(int comId) {
		this.comId = comId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "Picture [pictureId=" + pictureId + ", comId=" + comId
				+ ", path=" + path + "]";
	}

}
