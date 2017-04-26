package org.gr.woc.biz.impl;

import org.gr.woc.biz.IValidationBiz;
import org.gr.woc.dao.IUserDao;
import org.gr.woc.dao.impl.UserDaoImpl;
import org.gr.woc.po.User;

public class ValidationBizImpl implements IValidationBiz {
private IUserDao userDao;


public ValidationBizImpl() {
	super();
	// TODO Auto-generated constructor stub
	this.userDao=new UserDaoImpl();
}
	@Override
	public int checkUserStatus(int userId) {
		// TODO Auto-generated method stub
		User user=userDao.selectById(userId);
		int userStatus=user.getUserStatus();
		return userStatus;
		
	}

}
