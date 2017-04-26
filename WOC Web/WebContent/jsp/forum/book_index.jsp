<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="org.gr.woc.po.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<title>漫步云端/论坛首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="jsp/Market/css/styles.css" />
<link href="jsp/forum/css/style.css" rel="stylesheet" type="text/css"
	media="all" />
<!--slider-->
<script type="text/javascript"
	src="jsp/forum/js/modernizr.custom.53451.js"></script>
<script type="text/javascript" src="jsp/forum/js/jquery.min.js"></script>
<script type="text/javascript" src="jsp/forum/js/jquery.gallery.js"></script>
<script type="text/javascript">
	$(function() {
		$('#dg-container').gallery({
			autoplay : true
		});
	});
</script>
<!--CSS-->
<!--Google Webfont -->
<link href="jsp/WelcomePage/index_files/lanrenzhijia.css"
	rel="stylesheet" type="text/css" media="all">
<!--Javascript-->
<script>
	jQuery(document).ready(function($) {
		$('.theme-login').click(function() {
			$('.theme-popover-mask').fadeIn(100);
			$('.theme-popover').slideDown(200);
		});
		$('.theme-poptit .close').click(function() {
			$('.theme-popover-mask').fadeOut(100);
			$('.theme-popover').slideUp(200);
		});
	});
</script>
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


<script>
	var code;
	function perinf() {
		var user = "${sessionScope.user.userId}";
		if (user == "")
			alert("请先登录！");
		else
			window.location = "jsp/forum/user_regif.jsp";
	}
	function order(Id) {
		var user = "${sessionScope.user.userId}";
		if (user != '')
			window.location = "jsp/Market/Order.jsp?comid=" + Id;
		else
			alert("请先登录！");
	}
	function release() {
		var user = "${sessionScope.user.userId}";
		if (user == "")
			alert("请先登录！");
		else
			window.location = "jsp/forum/release_post.jsp";
	}
	function checkUserPwd() {
		if (document.getElementById('pwd').value == '') {
			document.getElementById('userPwd').innerHTML = '请输入密码';
			document.getElementById('userPwd').style.display = '';
			return false;
		}
		return true;
	}
	function PwdfocusEve() {
		document.getElementById('userPwd').innerHTML = '';
		document.getElementById('userPwd').style.display = 'none';
	}
	function VadfocusEve() {
		document.getElementById('vad').innerHTML = '';
		document.getElementById('vad').style.display = 'none';
	}
	function createCode() {
		code = "";
		var codeLen = 4;
		var checkCode = document.getElementById("valcode");
		var codeChar = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'a', 'b', 'c',
				'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
				'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
		for ( var i = 0; i < 4; i++) {
			var charNum = Math.floor(Math.random() * 36);
			code = code + codeChar[charNum];
		}
		checkCode.innerHTML = code;
	}
	function checkCode() {
		var input = document.getElementById("val").value;
		if (input != code) {
			document.getElementById("vad").style.display = "";
			document.getElementById("vad").innerHTML = "验证码错误";
			return false;
		}
		return true;
	}
	function loginEvent() {
		if (checkUserId() && checkUserPwd() && checkCode()) {
			return true;
		}
		return false;
	}
	function loginInit() {
		document.getElementById('log').value = "";
		document.getElementById('pwd').value = "";
		document.getElementById('val').value = "";
		createCode();
		PwdfocusEve();
		IdfocusEve();
		VadfocusEve();
	}
	function checkUserId() {
		if (document.getElementById('log').value == '') {
			document.getElementById('userId').innerHTML = '请输入用户名';
			document.getElementById('userId').style.display = '';
			return false;
		}
		return true;
	}
	function IdfocusEve() {
		document.getElementById('userId').innerHTML = '';
		document.getElementById('userId').style.display = 'none';
	}
</script>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body onload="onPageLoad();dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);;">
	<c:if test="${empty requestScope.flag }">
		<script>
			window.location = "/WOC/GetLatestPostsByLayoutServlet?posttype=400";
		</script>
	</c:if>
	<div id="123" style="width:220px; height:100px; z-index:9998; position:fixed; left:70%; top:10%; background-color : #F0F7F0; border:solid 2px #F0E0FE; opacity:0.7 ; text-align:center;display: none " >
    </div>
	<div class="theme-popover"
		style="background: url(jsp/WelcomePage/index_files/loginbody.jpg) no-repeat; width: 600px; height: 330px">
		<div class="theme-poptit">
			<a href="javascript:;" title="关闭" class="close">×</a>
			<h3>登录窗口</h3>
		</div>
		<div class="theme-popbod dform">
			<form class="theme-signin" name="loginform"
				action="/WOC/LoginServlet" method="post" style="overflow: hidden;"
				onsubmit="return loginEvent();">
				<ol>
					<li><h4
							style="color: rgba(83, 83, 83, 1.00); font-family: '微软雅黑'">请输入用户名和密码</h4></li>
					<li><strong>用户名：</strong><input id="log" class="ipt"
						type="text" name="log" size="20" onBlur="javascript:checkUserId()"
						onFocus="javascript:IdfocusEve()" /><em id="userId"
						style="display: none"></em></li>
					<li><strong>密码：</strong><input id="pwd" class="ipt"
						type="password" name="pwd" size="20"
						onBlur="javascript:checkUserPwd()"
						onFocus="javascript:PwdfocusEve()" /><em id="userPwd"
						style="display: none"></em></li>
					<li><strong>验证码：</strong><input id="val" class="ipt"
						type="text" name="vad" size="20" onBlur="javascript:checkCode()"
						onFocus="javascript:VadfocusEve()" /><em id="vad"
						style="display: none"></em></li>
					<div style="float: left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
							class="btn btn-primary" type="submit" name="submit" value=" 登 录 "
							onClick="javascript:loginEvent()" />
					</div>
					<div
						style="float: left; margin-left: 10px;; background-color: rgba(140, 235, 255, 1.00); width: 100px; height: 23px; text-align: center; padding-top: 7px"
						class="w-txt" id="valcode"></div>
					</li>

				</ol>
			</form>
		</div>
	</div>
	<div class="theme-popover-mask"></div>




	<div class="wrapper">
		<div class="header_container" style="height: 44px;">
			<!--Header Starts-->
			<header style="height: 200px;">

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
					<a href="jsp/WelcomePage/index.jsp"> <img
						src="jsp/Market/images/logo.png" />
					</a>
				</h1>
				<!--Logo Ends-->

				<div class="responsive-nav" style="display: none;"></div>
				<!--Search Starts-->
				<form class="header_search" action="/WOC/SearchPostsServlet">
					<div class="form-search">
						<input id="content" type="text" name="content" value="帖子标题"
							class="input-text" autocomplete="off" />
						<button type="submit" title="Search"></button>
					</div>
				</form>
				<!--Search Ends-->
			</header>
			<!--Header Ends-->
		</div>
		<div class="navigation_container" style="height: 52px">
			<!--Navigation Starts-->
			<nav style="width: 980px; margin-left: 184.5px">
				<ul class="primary_nav" style="float: left; height: 40px">
					<li><a href="jsp/WelcomePage/index.jsp">首页</a></li>
					<li><a href="jsp/Market/Store.jsp">商城</a> <!--SUbmenu Starts-->
						<ul class="sub_menu" style="height: 100px">
							<li>
								<ul>
									<li><a href="jsp/Market/TStore.jsp">二手商城</a></li>
									<li><a href="jsp/Market/EStore.jsp">交换平台</a></li>
									<li><a href="jsp/Market/FStore.jsp">漂流平台</a></li>
								</ul>
							</li>
						</ul> <!--SUbmenu Ends--></li>
					<li class="active"><a href="jsp/forum/forindex.jsp">论坛</a> <!--SUbmenu Starts-->
						<ul class="sub_menu" style="height: 250px">
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
	</div>
	<div>
		<img src="jsp/forum/images/book_bg.jpg" alt="" width="1348"
			height="484" />
	</div>
	<div class="content-bg">
		<div class="wrap">
			<div class="main">
				<div class="content">
					<h2>最新帖子</h2>
					<c:forEach items="${requestScope.lstLatestPostByLayout }"
						var="post">
						<div class="image group">
							<div class="grid images_3_of_1">
								<a href="jsp/forum/post.jsp?postid=${post.postId }"><img
									src="${post.resPath }"></a>
							</div>
							<div class="grid span_2_of_3">
								<a href="jsp/forum/post.jsp?postid=${post.postId }"><h3>${post.postName }</h3></a>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
									sed do eiusmod tempor incididunt ut labore et dolore magna
									aliqua. Ut enim ad minim veniam, quis nostrud exercitation
									Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed
									do eiusmod tempor incididunt</p>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="sidebar">
					<div class="sidebar-list">
						<h2>版块公告</h2>
						<marquee vspace="20" behavior="scroll" scrollAmount="1"
							scrollDelay="0" direction="up" width="98%" valign="top"
							onMouseOut="this.start()" onMouseOver="this.stop()">
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
								<li><a href="#">2014-8-16测试功能与优化软件</a></li>
							</ul>
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