package org.gr.woc.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Desire implements Serializable {
	private String desire;
	private int desId;
	private int comId;
	public Desire() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Desire(String desire, int desId, int comId) {
		super();
		this.desire = desire;
		this.desId = desId;
		this.comId = comId;
	}
	public String getDesire() {
		return desire;
	}
	public void setDesire(String desire) {
		this.desire = desire;
	}
	public int getDesId() {
		return desId;
	}
	public void setDesId(int desId) {
		this.desId = desId;
	}
	public int getComId() {
		return comId;
	}
	public void setComId(int comId) {
		this.comId = comId;
	}
	@Override
	public String toString() {
		return "Desire [desire=" + desire + ", desId=" + desId + ", comId="
				+ comId + "]";
	}
	

}
