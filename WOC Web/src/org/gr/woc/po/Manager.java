package org.gr.woc.po;

public class Manager {
	private int managerId;
	private String managerName,managerPassword;
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPassword() {
		return managerPassword;
	}
	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}
	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Manager [ManagerId=" + managerId + ", ManagerName="
				+ managerName + ", ManagerPassword=" + managerPassword + "]";
	}
	

}
