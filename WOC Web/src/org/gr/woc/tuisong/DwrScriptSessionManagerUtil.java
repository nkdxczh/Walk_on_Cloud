package org.gr.woc.tuisong;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.directwebremoting.Container;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;
import org.directwebremoting.extend.ScriptSessionManager;
import org.directwebremoting.servlet.DwrServlet;
import org.gr.woc.po.User;

public class DwrScriptSessionManagerUtil extends DwrServlet{

    private static final long serialVersionUID = -7504612622407420071L;

    public void init()throws ServletException {

           Container container = ServerContextFactory.get().getContainer();
           ScriptSessionManager manager = container.getBean(ScriptSessionManager.class);
           ScriptSessionListener listener = new ScriptSessionListener() {
                  public void sessionCreated(ScriptSessionEvent ev) {
                         HttpSession session = WebContextFactory.get().getSession();
                         if(session.getAttribute("user")!=null)
                         {
                        	// System.out.println(session.getAttribute("user"));
                         String userId =((User) session.getAttribute("user")).getUserId()+"";
                       //  System.out.println("a ScriptSession is created!");
                         ev.getSession().setAttribute("userId", userId);
                        // System.out.println(userId);
                         }
                  }
                  public void sessionDestroyed(ScriptSessionEvent ev) {
                        // System.out.println("a ScriptSession is distroyed");
                  }
           };
           manager.addScriptSessionListener(listener);
    }
}


