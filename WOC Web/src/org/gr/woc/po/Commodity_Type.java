package org.gr.woc.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Commodity_Type implements Serializable {
private int comTypeId;
private String comTypeName;
public Commodity_Type() {
	super();
	// TODO Auto-generated constructor stub
}
public Commodity_Type(int comTypeId, String comTypeName) {
	super();
	this.comTypeId = comTypeId;
	this.comTypeName = comTypeName;
}
public int getComTypeId() {
	return comTypeId;
}
public void setComTypeId(int comTypeId) {
	this.comTypeId = comTypeId;
}
public String getComTypeName() {
	return comTypeName;
}
public void setComTypeName(String comTypeName) {
	this.comTypeName = comTypeName;
}
@Override
public String toString() {
	return "Commodity_Type [comTypeId=" + comTypeId + ", comTypeName="
			+ comTypeName + "]";
}

}
