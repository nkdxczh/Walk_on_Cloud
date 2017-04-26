package org.gr.woc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.gr.woc.db.ConnectionManager;
import org.gr.woc.db.TransactionManager;

public class WocProxy implements InvocationHandler {

	private Object target;
	private ConnectionManager connectionManager = new ConnectionManager();
	

	public Object bind(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(this.target.getClass().getClassLoader(),
				this.target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		Object result = null;
		
		// 前置动作：打开数据库的连接
		TransactionManager.connection = connectionManager.openConnection();		
		// 前置动作：事务的开启操作
		TransactionManager.beginTransaction();
		
		// 执行委托类的方法
		result = method.invoke(this.target, args);
		
		// 后置动作：根据判断条件进行事务处理
		boolean flag = (boolean)result;
		if(flag){
			// 提交事务,自动关闭数据库连接
			TransactionManager.commit();
			
		}else{
			// 回滚事务,自动关闭数据库连接
			TransactionManager.rollback();
		}
		return result;
	}

}
