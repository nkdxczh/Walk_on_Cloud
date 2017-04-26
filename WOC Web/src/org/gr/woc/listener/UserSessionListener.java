package org.gr.woc.listener;

import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class UserSessionListener implements HttpSessionListener {

	@SuppressWarnings("rawtypes")
	public static HashMap sessionMap=new HashMap();
	@SuppressWarnings("unchecked")
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub

		HttpSession session=arg0.getSession();
		sessionMap.put(session.getId(), session);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub

		HttpSession session=arg0.getSession();
		if(session.getAttribute("user")!=null&&session.getAttribute("inf")!=null){
			sessionMap.remove(session.getAttribute("user").toString());
			session.invalidate();
		}
	}

}
