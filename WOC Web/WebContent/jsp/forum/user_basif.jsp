<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
 <script type='text/javascript' src='dwr/engine.js'></script>
  <script type='text/javascript' src='dwr/util.js'></script>
  <script type="text/javascript" src="dwr/interface/MessagePush.js"></script>
  <script type="text/javascript">
  		//通过该方法与后台交互，确保推送时能找到指定用户
         function onPageLoad(){
            var userId = '${user.userId}';
            MessagePush.onPageLoad(userId);

          }
         //推送信息
		 function showMessage(autoMessage){
			 document.getElementById("123").style.display="";
			 document.getElementById("123").innerHTML=autoMessage;
			
			// alert(autoMessage[1]);
		}
  </script>

<title>漫步云端/个人信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="./css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href='http://fonts.googleapis.com/css?family=Julius+Sans+One' rel='stylesheet' type='text/css'>
</head>
<body  onload="onPageLoad();dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);;">
<div id="123" style="width:220px; height:100px; z-index:9998; position:fixed; left:70%; top:10%; background-color : #F0F7F0; border:solid 2px #F0E0FE; opacity:0.7 ; text-align:center; display: none" >
    </div>
<div class="menu-bg" style="border:none">
<div class="wrap" style="border:none">
	<div class="menu" style="border:none">
    	<ul class="nav">
           <li><a href="forindex.jsp">首页</a></li>
           <li><a href="movie_index.jsp">电影</a></li>
           <li ><a href="music_index.jsp">音乐</a></li>
           <li><a href="education_index.jsp">教学</a></li>
           <li><a href="activity_index.jsp">活动</a></li>
           <li ><a href="book_index.jsp">读书</a></li>
           <li><a href="chat_index.jsp">畅谈</a></li>
           <li><a href="chat_index.jsp">商城</a></li>
           <li><a href="user_regif.jsp">信息</a></li>
        </ul>
	</div>
     <div class="header">
	    <div class="search">
			<form id="form" method="post" action="/WOC/SearchPostsServlet">
				  <input id="content" name="content" type="text" value="帖子名称" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '帖子名称';}">
				  <input type="submit" value="搜索">
			</form>
            <a href="release_post.jsp"><input type="submit" value="发帖"  style="width:100%" style="float:left"></a>
		  </div>
    </div>
<div class="clear"></div>
</div>
</div>
<div class="content-bg">
<div class="wrap">
<div class="main">
<div class="content">
  <div class="perifbg">
<div class="wrap">
	<div class="menu">
    	<ul class="nav">
           <li><a href="user_regif.jsp">注册信息</a></li>
           <li><a href="user_basif.jsp?url=0">基本信息</a></li>
           <li ><a href="user_friif.jsp?url=1">好友信息</a></li>
           <li><a href="user_marif.jsp">商城信息</a></li>
           <li><a href="user_forif.jsp">论坛信息</a></li>
        </ul>
	</div>
<div class="clear"></div>
</div>
</div>
<p>&nbsp;</p>
<div style="text-align:center" style="overflow:hidden">
<form>
<table style="margin:0 auto" width="400" border="2">
  <tr>
    <td width="116" height="42"><p>昵称：</p>
      <p>&nbsp;</p></td>
    <td width="266">张无忌</td>
  </tr>
  <tr>
    <td height="42"><p>性别：</p>
      <p>&nbsp;</p></td>
    <td>男</td>
  </tr>
  <tr>
    <td height="32"><p>积分：</p>
      <p>&nbsp;</p></td>
    <td>0</td>
  </tr>
  <tr>
    <td height="32"><p>系别：</p>
      <p>&nbsp;</p></td>
    <td>武当</td>
  </tr>
  <tr>
    <td height="32"><p>地区：</p>
      <p>&nbsp;</p></td>
    <td>武当山</td>
  </tr>
  <tr>
    <td height="32"><p>爱好：</p>
      <p>&nbsp;</p></td>
    <td>武术</td>
  </tr>
  <tr>
    <td height="32"><p>电子邮箱：</p>
      <p>&nbsp;</p></td>
    <td>zwj</td>
  </tr>
  <tr>
    <td  class="search"  colspan="2"><p>
      <input type="submit" value="修改信息" onClick="window.open("user_basif_update.html")"/>
    </p>
    <div class="clear"></div>
      <p>&nbsp; </p></td>
    </tr>
</table>
  <p>&nbsp;</p>
</form>
</div>

<div class="clear"></div>
</div>
<div class="sidebar">
<div class="sidebar-list">
	<h2>版块公告</h2><marquee vspace="20" behavior="scroll" scrollAmount="1" scrollDelay="0" direction="up" width="98%" valign="top" onMouseOut="this.start()" onMouseOver="this.stop()">
    <ul>
      <li><a href="#">2014-8-4 进行基础界面设置以及相关业务的编写 </a></li>
      <li><a href="#">2014-8-5进行基础界面设置以及相关业务的编写 </a></li>
      <li><a href="#">2014-8-6完成业务编写与核心页面编写</a></li>
      <li><a href="#">2014-8-7里程碑：·完成项目核心静态页面设计并展示·完成项目技术框架搭建</a></li>
      <li><a href="#">2014-8-8进行全面页面编写</a></li>
      <li><a href="#">2014-8-9对页面与业务进行整合</a></li>
      <li><a href="#">2014-8-10对页面与业务进行整合</a></li>
      <li><a href="#">2014-8-11里程碑：·完成项目60%的核心业务，达到项目基本运行，并进行展示。</a></li>
      <li><a href="#">2014-8-12页面修整与功能调试</a></li>
      <li><a href="#">2014-8-13页面修整与功能拓展</a></li>
      <li><a href="#">2014-8-14里程碑：·完成项目核心业务并进行测试</a></li>
      <li><a href="#">2014-8-15拓展功能编写</a></li>
      <li><a href="#">2014-8-16测试功能与优化软件</a></li>  </ul>
  </marquee>
</div>
</div>
<div class="clear"></div>
</div>
</div>
</div>
<div class="footer-bg">
<div class="wrap">
<div class="footer">
	<div class="section group"></div>
</div>
</div>
</div>
        


<div class="footer1-bg">
	<div class="wrap">
			<div class="copy">
			<p>© 2014 All rights Reserved | Design by spaceship</p>
			</div>	
	</div>
</div>
</body>
</html>