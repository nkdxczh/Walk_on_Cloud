
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="javax.servlet.ServletContext"%>
<link rel="stylesheet" href="jsp/Market/css/styles.css" />
<!--Google Webfont -->
<link href="jsp/WelcomePage/index_files/lanrenzhijia.css"
	rel="stylesheet" type="text/css" media="all">
<html>

<body>
<div class="wrapper">
		<div class="header_container">
			<!--Header Starts-->
			<header>

			<div class="top_bar clear">
				<!--Top Links Starts-->
				<ul class="top_links" style="height: 44px">
					<li><a href="jsp/WelcomePage/login.jsp">注册</a></li>
					<c:if test="${!empty sessionScope.user }">
						<li><a onclick="perinf();">个人主页</a></li>
						<li><a onclick="release();">发帖</a></li>
						<li id="logout" class="theme-login"><a
							href="/WOC/LogoutServlet">注销</a></li>
					</c:if>
					<c:if test="${empty sessionScope.user }">
						<li id="login" class="theme-login"><a onclick="loginInit();">登录</a></li>
					</c:if>
				</ul>
				<!--Top Links Ends-->
			</div>
			<!--Logo Starts-->
			<h1 class="logo">
				<a href="jsp/WelcomePage/index.jsp"><img
					src="jsp/Market/images/logo.png" /></a>
			</h1>
			<!--Logo Ends--> <!--Responsive NAV-->
			<div class="responsive-nav" style="display: none;"></div>
			<!--Responsive NAV--> <!--Search Starts-->
			<form class="header_search" action="/WOC/SearchPostsServlet">
				<div class="form-search">
					<input id="content" type="text" name="content" class="input-text"
						autocomplete="off" />
					<button type="submit" title="Search"></button>
				</div>
			</form>
			<!--Search Ends--> </header>
			<!--Header Ends-->
		</div>
		<div class="navigation_container">
			<!--Navigation Starts-->
			<nav>
			<ul class="primary_nav">
				<li><a href="jsp/WelcomPage/index.jsp">首页</a></li>
				<li><a href="jsp/Market/Store.jsp">商城</a> <!--SUbmenu Starts-->
					<ul class="sub_menu">
						<li>
							<ul>
								<li><a href="jsp/Market/TStore.jsp">二手商城</a></li>
								<li><a href="jsp/Market/EStore.jsp">交换平台</a></li>
								<li><a href="jsp/Market/FStore.jsp">漂流平台</a></li>
							</ul>
						</li>
					</ul> <!--SUbmenu Ends--></li>
				<li class="active"><a href="jsp/forum/forindex.jsp">论坛</a> <!--SUbmenu Starts-->
					<ul class="sub_menu">
						<li>
							<ul>
								<li><a href="jsp/forum/movie_index.jsp">电影板块</a></li>
								<li><a href="jsp/forum/music_index.jsp">音乐版块</a></li>
								<li><a href="jsp/forum/education_index.jsp">教学版块</a></li>
								<li><a href="jsp/forum/activity_index.jsp">活动版块</a></li>
								<li><a href="jsp/forum/book_index.jsp">读书版块</a></li>
								<li><a href="jsp/forum/chat_index.jsp">畅谈版块</a></li>
							</ul>
						</li>
					</ul> <!--SUbmenu Ends--></li>
			</ul>
			</nav>
			<!--Navigation Ends-->
		</div>
	<p></p>
	<table width="70%" height="173" style="border: none;margin: 0 auto; margin-top:20px;box-shadoe:3px 3px 8px #f00;">
		<tr>
			<td colspan="2" align="center" bgcolor="#FFFFCC" style="text-align: center;font-size: 25px">您正在和${sessionScope.younickname}聊天</td>
		</tr>
		<tr>
			<td align="left" bgcolor="#FFFFCC">
			 <%
			 String myuserid=String.valueOf((Integer)session.getAttribute("myuserid"));
			 String youuserid=String.valueOf((Integer)session.getAttribute("youuserid"));
			// out.println(myuserid);
			 //out.println(youuserid);
			 String myword="words"+myuserid+youuserid;
			 String youword="words"+youuserid+myuserid;
boolean ifrefresh=(Boolean)application.getAttribute("ifrefresh");

if(ifrefresh)
{
	//response.setHeader("refresh","1;url=showtext.jsp");
	if(application.getAttribute(youword)!=null)
	{
		out.println(application.getAttribute(youword));
		if(!(Boolean)session.getAttribute("bool"))
		{
		if(application.getAttribute(myword)==null)
		{
			String o="";
			application.setAttribute(myword, (Object)o);
		}
		String t=(String)application.getAttribute(myword);
		t=t+" <br/>";
		application.setAttribute(myword, (Object)t);
		}
		//ifrefresh=false;
		//application.setAttribute("ifrefresh", (Object)ifrefresh);
	}
	//if(application.getAttribute(youword)!=null)
		//out.println(application.getAttribute(youword));
	response.setHeader("refresh","3;url=showtext.jsp");
}
else
{
	if(application.getAttribute(youword)!=null)
		out.println(application.getAttribute(youword));
	//if(application.getAttribute(youword)!=null)
		//out.println(application.getAttribute(youword));
	response.setHeader("refresh","3;url=showtext.jsp");
	
}
%>
			</td>
			<td align="right" bgcolor="#FFFFCC">
			<% 
if(ifrefresh)
{
	//response.setHeader("refresh","1;url=showtext.jsp");
	//if(application.getAttribute(myword)!=null)
		//out.println(application.getAttribute(myword));
	if(application.getAttribute(myword)!=null)
	{
		out.println(application.getAttribute(myword));
		if((Boolean)session.getAttribute("bool"))
		{
		if(application.getAttribute(youword)==null)
		{
			String o="";
			application.setAttribute(youword, (Object)o);
		}
		String t=(String)application.getAttribute(youword);
		t=t+" <br/>";
		
		application.setAttribute(youword, (Object)t);
		}
		
	}
	
	response.setHeader("refresh","3;url=showtext.jsp");
}
else
{
	//if(application.getAttribute(myword)!=null)
		//out.println(application.getAttribute(myword));
	if(application.getAttribute(myword)!=null)
		out.println(application.getAttribute(myword));
	response.setHeader("refresh","3;url=showtext.jsp");
	
}
%>
			
			
			</td>
		</tr>
	</table>
	</div>
	<p>&nbsp;</p>
	<%ifrefresh=false;
	application.setAttribute("ifrefresh", (Object)ifrefresh);
	session.setAttribute("bool", (Object)ifrefresh);
	%>
</body>
</html>
