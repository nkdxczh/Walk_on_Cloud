<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html;charset=GB2312"%> 
<html>
<body>

<%   
try{
request.setCharacterEncoding("GB2312"); 
String mywords=request.getParameter("message");
String nickname=(String)session.getAttribute("nickname");
String t="";
boolean ifrefresh=false;
String myuserid=String.valueOf((Integer)session.getAttribute("myuserid"));
String youuserid=String.valueOf((Integer)session.getAttribute("youuserid"));
//out.println(myuserid);
String word="words"+myuserid+youuserid;
//String count="wordcount"+String.valueOf((Integer)session.getAttribute("myuserid")*(Integer)session.getAttribute("youuserid"));
application.setAttribute("ifrefresh", (Object)ifrefresh);
if(mywords!=null)
{
	if(application.getAttribute(word)==null ){

	//session.setAttribute("mycount", (Object)application.getAttribute(count));//数字
	//int lastcount=(Integer)application.getAttribute(count);
	//for(int i=0;i<lastcount;i++)
		//t+="<br/>";
	t= nickname + ":" + mywords + "<br/>";
	//lastcount++;
	application.setAttribute(word,(Object)t);
	ifrefresh=true;
	application.setAttribute("ifrefresh", (Object)ifrefresh);
	session.setAttribute("bool",ifrefresh);
	}
	else 
	{
	t=(String)application.getAttribute(word);
	t += nickname + ":" + mywords + "<br/>";
	ifrefresh=true;
	application.setAttribute(word,(Object)t);
	application.setAttribute("ifrefresh", (Object)ifrefresh);
	session.setAttribute("bool",ifrefresh);
	}
}
}
catch(Exception e){
}
%>

<form method="post" action="send.jsp" style="margin-left: 450px;"> 
<input name="message" type="text" size=50 >
<input type="submit" value="send" >
</form> 
 
</body>
</html>