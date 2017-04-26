package org.gr.woc.biz.impl;

import org.gr.woc.biz.ILoginBiz;
import org.gr.woc.dao.IUserDao;
import org.gr.woc.dao.impl.UserDaoImpl;
import org.gr.woc.po.User;

public class LoginBizImpl implements ILoginBiz {
private IUserDao userDao;

	public LoginBizImpl() {
	super();
	// TODO Auto-generated constructor stub
	this.userDao=new UserDaoImpl();
}

	@Override
	public boolean isValidate(String userName) {
		// TODO Auto-generated method stub
		User user =new User();
		user=userDao.selsectByName(userName);		
		if(user.getUserName()==null)
		{
			return false;
		}
		else {
			return true;
		}
	}

}
