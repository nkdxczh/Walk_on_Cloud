package org.gr.woc.tuisong;

import java.util.Collection;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;

public class Test{
    public void sendMessageAuto(String userid, String message){
        
        final String userId = userid;
        final String autoMessage = message;
        Browser.withAllSessionsFiltered(new ScriptSessionFilter() {
            public boolean match(ScriptSession session){
                if (session.getAttribute("userId") == null)
                    return false;
                else
                    return (session.getAttribute("userId")).equals(userId);
            }
        }, new Runnable(){
            
            private ScriptBuffer script = new ScriptBuffer();
            
            public void run(){
            	
                
                script.appendCall("showMessage", autoMessage);
              //  System.out.println("这里是推送：test");
              //  System.out.println(autoMessage);
                
                Collection<ScriptSession> sessions = Browser

                .getTargetSessions();
                
                for (ScriptSession scriptSession : sessions){
                    scriptSession.addScript(script);
                }
            }
        });
    }
}
