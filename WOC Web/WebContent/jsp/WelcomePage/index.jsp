<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">

<title>漫步云端， 你值得拥有</title>
<link href="jsp/WelcomePage/index_files/index.css" rel="stylesheet"
	type="text/css" media="all">
<link href="jsp/WelcomePage/index_files/top_nav.css" rel="stylesheet"
	type="text/css" media="all">
<!--[if IE]>
<script src="http://misc.web.xunlei.com/www/v8/js/htmlfive.js" async></script>
<![endif]-->
<script src="jsp/WelcomePage/index_files/require.js"></script>
<script src="jsp/WelcomePage/index_files/jquery-1.8.2.min.js"
	charset="utf-8" type="text/javascript"></script>
<script src="jsp/WelcomePage/index_files/underscore-min.js"
	charset="utf-8" type="text/javascript"></script>

<script src="jsp/WelcomePage/index_files/index_v8_init_build.js"
	async=""></script>
<script src="jsp/WelcomePage/index_files/jquery-1.8.3.min.js"></script>
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
<div class="warning" id="t33"></div>
<script>
	$("#t33_lnk").click(function() {
		$("#t33").hide();
		$("#globalheader_outer").removeClass("t33");
		$("#sec-nav").css("top", "61px");
	})
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




<!--<base target="_blank">-->
<!--<base href="." target="_blank">-->
<base href="." target="_blank">
<!-- Begin comScore Tag -->

<!-- End comScore Tag -->
</head>
<body <%=path%> onload="onPageLoad();dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);;">
	<c:if test="${empty requestScope.flag }">
		<script>
			window.location = "/WOC/EmptyServlet?key=5";
		</script>
	</c:if>

<div id="123" style="width:220px; height:100px; z-index:9998; position:fixed; left:70%; top:10%; background-color : #F0F7F0; border:solid 2px #F0E0FE; opacity:0.7 ; text-align:center; display: none" >
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
				onsubmit="return startRequest();">
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
					<li>
						<div style="float:left">
							<input
								class="btn btn-primary" type="submit" name="submit"
								value=" 登 录 " onClick="javascript:loginEvent()" style="margin-left:0; " />
						</div>
						<div
							style="margin-left:100px; float:left; background-color: rgba(140, 235, 255, 1.00); width: 100px; height: 35px; text-align: center;"
							class="w-txt" id="valcode"></div>
					</li>

				</ol>
			</form>
		</div>
	</div>
	<div class="theme-popover-mask"></div>

	<div
		style="display: block; position: absolute; right: 0; bottom: 0; border: none;"></div>

	<div class="globalheader_outer t33" id="globalheader_outer">
		<div class="globalheader">
			<div class="logoarea">
				<div class="logo"></div>
			</div>
			<div class="dropbox dropbox_user" id="nav_user_box">
				<div class="dropbox_tigger dropbox_tigger_user" id="no-login">
					<b class="i i_user"></b>
					<c:if test="${!empty sessionScope.user }">
						<a style="font-size: 18; color: #FFFFFF;" href="">${sessionScope.inf.nickName }</a>
						<span style="font-size: 18; color: #FFFFFF;">等级:${sessionScope.user.userLevel }</span>
						<br />
						<span style="font-size: 18; color: #FFFFFF;">积分:${sessionScope.inf.score }</span>
					</c:if>
					<c:if test="${empty sessionScope.user }">
						<a class="btn btn-primary btn-large theme-login"
							href="javascript:;" onClick="javascript:loginInit();">登录</a>
						<span>|</span>
						<a href="jsp/WelcomePage/regist.jsp" title="注册" target="_blank"
							class="btn btn-primary btn-large ">注册</a>
					</c:if>
				</div>

				<div class="dropbox_tigger_outer" id="dropbox_user_tigger"
					style="display: none;">
					<div class="dropbox_tigger" id="user-name" style="display:"></div>
					<div class="dropcon dropcon_user" id="user-msg-layer">
						<b class="i i_arw"></b>
						<div class="userinfo" id="user-msg"></div>
					</div>
					<iframe scrolling="no" frameborder="no"
						class="dropcon_iframe userinfo_iframe"
						id="dropbox_user_tigger_ifm" style="display: none;"></iframe>
				</div>
			</div>
		</div>
	</div>

	<div class="sec-nav" style="top: 89px;" id="sec-nav">
		<div class="sec-nav-box" align="center">
			<a href="jsp/Market/Store.jsp" class="s-n" target="_self">交易商城</a> <a
				href="jsp/forum/forindex.jsp" class="s-n" target="_self">趣味论坛</a>
			<div class="sec-nav-userarea">
				<iframe scrolling="no" frameborder="no"
					class="dropcon_iframe dl_iframe" style="display: none"
					id="dropcon_iframe_dl"></iframe>
			</div>
		</div>
	</div>

	<p>&nbsp;</p>
	<p>&nbsp;</p>
	<section class="focus" id="section_1"
		style="margin:0 0 0 0;padding:0 0 0 0">
	<div class="focus-ad" id="focus-ad">
		<div class="fa-cont" id="cm2500"></div>
	</div>
	<ul class="focus-bg">
		<li
			style="display: none; background-image: url('jsp/WelcomePage/index_files/1.jpg'); background-position: 50% 0px; background-repeat: no-repeat; background-color: rgb(163, 90, 124);"
			id="focus_bg_0"></li>
		<li
			style="display: none; background: url(jsp/WelcomePage/index_files/2.jpg) 50% 0px no-repeat rgb(189, 131, 92);"
			id="focus_bg_1"></li>
		<li
			style="display: none; background: url(jsp/WelcomePage/index_files/3.jpg) 50% 0px no-repeat rgb(248, 192, 70);"
			id="focus_bg_2"></li>
		<li
			style="display: list-item; background: url(jsp/WelcomePage/index_files/4.jpg) 50% 0px no-repeat rgb(149, 220, 231);"
			id="focus_bg_3"></li>
		<li
			style="display: none; background: url(jsp/WelcomePage/index_files/5.jpg) 50% 0px no-repeat rgb(0, 0, 0);"
			id="focus_bg_4"></li>
		<li
			style="display: none; background: url(jsp/WelcomePage/index_files/6.jpg) 50% 0px no-repeat rgb(0, 0, 0);"
			id="focus_bg_5"></li>
		<li
			style="display: none; background: url(jsp/WelcomePage/index_files/7.jpg) 50% 0px no-repeat rgb(0, 0, 0);"
			id="focus_bg_6"></li>
		<li
			style="display: none; background: url(jsp/WelcomePage/index_files/8.jpg) 50% 0px no-repeat rgb(0, 0, 0);"
			id="focus_bg_7"></li>
		<li
			style="display: none; background: url(jsp/WelcomePage/index_files/9.jpg) 50% 0px no-repeat rgb(15, 17, 28);"
			id="focus_bg_8"></li>
		<li
			style="display: none; background: url(jsp/WelcomePage/index_files/10.jpg) 50% 0px no-repeat rgb(7, 8, 21);"
			id="focus_bg_9"></li>
		<li
			style="display: none; background: url(jsp/WelcomePage/index_files/11.jpg) 50% 0px no-repeat rgb(0, 18, 37);"
			id="focus_bg_10"></li>
		<li
			style="display: none; background: url(jsp/WelcomePage/index_files/12.jpg) 50% 0px no-repeat rgb(70, 149, 217);"
			id="focus_bg_11"></li>
		<li
			style="display: none; background: url(jsp/WelcomePage/index_files/13.jpg) 50% 0px no-repeat rgb(4, 5, 7);"
			id="focus_bg_12"></li>
	</ul>
	<div class="wrapper">
		<ul class="focus-tit">
			<li id="focus_title_0" style="display: none;"><a
				title="漫步云端小组，致力于出产精致网页" class="focuslink" blockid="8095"></a>
				<h3 class="focustxt">
					<a href="#" blockid="8095">漫步云端小组，致力于出产精致网页</a>
				</h3></li>
			<li id="focus_title_1" style="display: none;"><a
				href="#"
				title="漫步云端小组，致力于出产精致网页" class="focuslink" blockid="8095"></a>
				<h3 class="focustxt">
					<a href="#" blockid="8095">漫步云端小组，致力于出产精致网页</a>
				</h3></li>
			<li id="focus_title_2" style="display: none;"><a
				href="#"
				title="漫步云端小组，致力于出产精致网页" class="focuslink" blockid="8095"></a>
				<h3 class="focustxt">
					<a href="#" blockid="8095">漫步云端小组，致力于出产精致网页</a>
				</h3></li>
			<li id="focus_title_3"><a
				href="#"
				title="漫步云端小组，致力于出产精致网页" class="focuslink" blockid="8095"></a>
				<h3 class="focustxt">
					<a href="#" blockid="8095">漫步云端小组，致力于出产精致网页</a>
				</h3></li>
			<li id="focus_title_4" style="display: none;"><a
				href="#"
				title="漫步云端小组，致力于出产精致网页" class="focuslink" blockid="8095"></a>
				<h3 class="focustxt">
					<a href="#" blockid="8095">漫步云端小组，致力于出产精致网页</a>
				</h3></li>
			<li id="focus_title_5" style="display: none;"><a
				href="#"
				title="漫步云端小组，致力于出产精致网页" class="focuslink" blockid="8095"></a>
				<h3 class="focustxt">
					<a href="#"
						blockid="8095">漫步云端小组，致力于出产精致网页</a>
				</h3></li>
			<li id="focus_title_6" style="display: none;"><a
				href="#"
				title="漫步云端小组，致力于出产精致网页" class="focuslink" blockid="8095"></a>
				<h3 class="focustxt">
					<a href="#"
						blockid="8095">漫步云端小组，致力于出产精致网页</a>
				</h3></li>
			<li id="focus_title_7" style="display: none;"><a
				href="#"
				title="漫步云端小组，致力于出产精致网页" class="focuslink" blockid="8095"></a>
				<h3 class="focustxt">
					<a href="#"
						blockid="8095">漫步云端小组，致力于出产精致网页</a>
				</h3></li>
			<li id="focus_title_8" style="display: none;"><a
				href="#" title="漫步云端小组，致力于出产精致网页"
				class="focuslink" blockid="8095"></a>
				<h3 class="focustxt">
					<a href="#" blockid="8095">漫步云端小组，致力于出产精致网页</a>
				</h3></li>
			<li id="focus_title_9" style="display: none;"><a
				href="#"
				title="漫步云端小组，致力于出产精致网页" class="focuslink" blockid="8095"></a>
				<h3 class="focustxt">
					<a href="#" blockid="8095">漫步云端小组，致力于出产精致网页</a>
				</h3></li>
			<li id="focus_title_10" style="display: none;"><a
				href="#"
				title="漫步云端小组，致力于出产精致网页" class="focuslink" blockid="8095"></a>
				<h3 class="focustxt">
					<a href="#" blockid="8095">漫步云端小组，致力于出产精致网页</a>
				</h3></li>
			<li id="focus_title_11" style="display: none;"><a
				href="#"
				title="漫步云端小组，致力于出产精致网页" class="focuslink" blockid="8095"></a>
				<h3 class="focustxt">
					<a href="#" blockid="8095">漫步云端小组，致力于出产精致网页</a>
				</h3></li>
			<li id="focus_title_12" style="display: none;"><a
				href="#"
				title="漫步云端小组，致力于出产精致网页" class="focuslink" blockid="8095"></a>
				<h3 class="focustxt">
					<a href="#" blockid="8095">漫步云端小组，致力于出产精致网页</a>
				</h3></li>
		</ul>
		<ul class="focusbox-tigger">
			<li id="focus_tigger_0" class=""><a title="漫步云端小组，致力于出产精致网页"
				blockid="8095"><img src="jsp/WelcomePage/index_files/1.jpg"
					width="89" height="45"><span></span></a></li>
			<li id="focus_tigger_1" class=""><a title="漫步云端小组，致力于出产精致网页"
				blockid="8095"><img src="jsp/WelcomePage/index_files/2.jpg"
					width="89" height="45"><span></span></a></li>
			<li id="focus_tigger_2" class=""><a title="漫步云端小组，致力于出产精致网页"
				blockid="8095"><img src="jsp/WelcomePage/index_files/3.jpg"
					width="89" height="45"><span></span></a></li>
			<li id="focus_tigger_3" class=" on"><a title="漫步云端小组，致力于出产精致网页 "
				blockid="8095"><img src="jsp/WelcomePage/index_files/4.jpg"
					width="89" height="45"><span></span></a></li>
			<li id="focus_tigger_4"><a
				href="http://vod.kankan.com/v/39/39399.shtml"
				title="漫步云端小组，致力于出产精致网页" blockid="8095"><img
					src="jsp/WelcomePage/index_files/5.jpg" width="89" height="45"><span></span></a></li>
			<li id="focus_tigger_5"><a
				href="http://g.ui37.net/s/1/1725/21543.html?uid=120073"
				title="漫步云端小组，致力于出产精致网页" blockid="8095"><img
					src="jsp/WelcomePage/index_files/6.jpg" width="89" height="45"><span></span></a></li>
			<li id="focus_tigger_6"><a
				href="http://g.ui37.net/s/1/1725/21542.html?uid=120078"
				title="漫步云端小组，致力于出产精致网页" blockid="8095"><img
					src="jsp/WelcomePage/index_files/7.jpg" width="89" height="45"><span></span></a></li>
			<li id="focus_tigger_7"><a
				href="http://xnjdshazg.r.thetianfu.com/xnjdshazg.htm"
				title="漫步云端小组，致力于出产精致网页" blockid="8095"><img
					src="jsp/WelcomePage/index_files/8.jpg" width="89" height="45"><span></span></a></li>
			<li id="focus_tigger_8"><a
				href="http://act1.cm.kankan.com/volvo/" title="漫步云端小组，致力于出产精致网页"
				blockid="8095"><img src="jsp/WelcomePage/index_files/9.jpg"
					width="89" height="45"><span></span></a></li>
			<li id="focus_tigger_9"><a
				href="http://vod.kankan.com/v/85/85214.shtml"
				title="漫步云端小组，致力于出产精致网页" blockid="8095"><img
					src="jsp/WelcomePage/index_files/10.jpg" width="89" height="45"><span></span></a></li>
			<li id="focus_tigger_10" class="special"><a
				href="http://topics.kankan.com/201408mxglj/"
				title="漫步云端小组，致力于出产精致网页" blockid="8095"><img
					src="jsp/WelcomePage/index_files/11.jpg" width="89" height="45"><span></span></a></li>
			<li id="focus_tigger_11" class="screen_more"><a
				href="http://vod.kankan.com/v/85/85427.shtml"
				title="漫步云端小组，致力于出产精致网页" blockid="8095"><img
					src="jsp/WelcomePage/index_files/12.jpg" width="89" height="45"><span></span></a></li>
			<li id="focus_tigger_12" class="screen_more"><a
				href="http://vod.kankan.com/v/85/85117.shtml"
				title="漫步云端小组，致力于出产精致网页" blockid="8095"><img
					src="jsp/WelcomePage/index_files/13.jpg" width="89" height="45"><span></span></a></li>
		</ul>

	</div>
	</section>

	<script src="jsp/WelcomePage/index_files/index_v8_build.js"></script>

	<div id="grzx_list_2" style="display: none;">
		<div class="scorll" id="grzx_list_2_unlogin">
			<div class="scrollcontain">
				<div class="c-s-tips" id="grzx_list_2_unlogin_text">
					<span class="c-login" onclick="Login.Show();return false;">登
						录</span>
				</div>
			</div>
		</div>
		<div class="scorll" id="grzx_list_2_login" style="display: none;">
			<div class="scrollcontain">
				<ul class="imglist imglist150x210" style="left: 0;"
					id="grzx_fav_list"></ul>
			</div>
			<a target="_self" href="javascript:void(0)"
				class="scrolltigger scrolltigger_L scrolltigger_L_none" title="向左"
				id="grzx_fav_list_prev"></a> <a target="_self"
				href="javascript:void(0)" class="scrolltigger scrolltigger_R"
				title="向右" id="grzx_fav_list_next"></a>
		</div>
	</div>
	</section>

	<style>
.footer {
	height: auto;
	text-align: center;
	padding: 36px 0 20px;
}

.footer,.footer a {
	color: #888888;
}

.f-nav a {
	color: #333333;
	margin: 0 3px;
}

.footer .copyright {
	height: 78px;
}
</style>
	<link href="jsp/WelcomePage/index_files/lanrenzhijia.css"
		rel="stylesheet" type="text/css" media="all">
	<div id="fav_tips" class="floattip floattip_fav" style="display: none;">
		<div class="floattip_tt">
			<strong>提示</strong><a class="close" title="关闭"
				onclick="G_PAGE_PATCH.tipHide();" href="javascript:void(0);">关闭</a>
		</div>
	</div>

	<script>
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
	<script>
		var code;
		var xmlhttp;
		var username;
		function createXMLHttpRequest() {
			if (window.ActiveXObject) {
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			} else {
				xmlhttp = new XMLHttpRequest();
			}
		}
		var c = 0;
		function startRequest() {
			name = document.getElementById("log").value;
			var password = document.getElementById("pwd").value;
			var valcode = document.getElementById("val").value;
			if (document.getElementById('log').value == '') {
				document.getElementById('userId').innerHTML = '请输入用户名';
				document.getElementById('userId').style.display = '';
				return false;
			}
			if (document.getElementById('pwd').value == '') {
				document.getElementById('userPwd').innerHTML = '请输入密码';
				document.getElementById('userPwd').style.display = '';
				return false;
			}
			if (document.getElementById('val').value != code) {
				document.getElementById("vad").style.display = "";
				document.getElementById("vad").innerHTML = "验证码错误";
				return false;
			}
			var url = "/WOC/LoginServlet?log=" + name + "&pwd=" + password;
			createXMLHttpRequest();
			xmlhttp.onreadystatechange = stateChange;
			xmlhttp.open("POST", url, true);
			xmlhttp.send(null);
			return false;
		}
		function stateChange() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				if (xmlhttp.responseText == "usernameError") {
					document.getElementById('userId').innerHTML = '用户名不存在';
					document.getElementById('userId').style.display = ''
				} else if (xmlhttp.responseText == "passwordError") {
					document.getElementById('userPwd').innerHTML = '密码错误';
					document.getElementById('userPwd').style.display = '';
				} else
					window.open(location, "_self");
			}
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
			var codeChar = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'a', 'b',
					'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
					'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
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
	</script>
	<div
		style="background-color: rgb(51, 51, 51); width: 100%; height: 44px; overflow: hidden;">
		<p style="color: white; text-align: center; vertical-align: middle;">©
			2014 All rights Reserved | Design by Spaceship</p>
	</div>
</body>
</html>