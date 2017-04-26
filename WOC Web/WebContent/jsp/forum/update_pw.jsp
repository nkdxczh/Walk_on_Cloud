<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>漫步云端/发布物品</title>
<!--CSS-->
<link rel="stylesheet" href="jsp/Market/css/styles.css" />
<!--Google Webfont -->
<link href="jsp/WelcomePage/index_files/lanrenzhijia.css"
	rel="stylesheet" type="text/css" media="all">
<!--Javascript-->
<script type="text/javascript" src="jsp/Market/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="jsp/Market/js/jquery.flexslider.js"></script>
<script type="text/javascript" src="jsp/Market/js/jquery.easing.js"></script>
<script type="text/javascript" src="jsp/Market/js/jquery.jcarousel.js"></script>
<script type="text/javascript" src="jsp/Market/js/form_elements.js"></script>
<script type="text/javascript" src="jsp/Market/js/custom.js"></script>
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
	function myalert() {
		alert("您已登录！");
	}
	function updatealert() {
		return confirm("确认修改密码？");
	}
	function checkUserPwd() {
		if (document.getElementById('pwd').value == '') {
			document.getElementById('userPwd').innerHTML = '请输入密码';
			document.getElementById('userPwd').style.display = '';
			return false;
		}
		return true;
	}
	function perinf() {
		var user = "1";
		var user1 = $
		{
			sessionScope.user.userId
		}
		;
		user = user1;
		if (user != "1")
			window.location = "jsp/forum/person.jsp?userid=" + user;
		else
			alert("请先登录！");
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
<body  onload="onPageLoad();dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);;">

	<c:if test="${empty requestScope.flag }">
		<script>
			window.location = "/WOC/EmptyServlet?key=4";
		</script>
	</c:if>
		
<div id="123" style="width:220px; height:100px; z-index:9998; position:fixed; left:70%; top:10%; background-color : #F0F7F0; border:solid 2px #F0E0FE; opacity:0.7 ; text-align:center; display: none" >
    </div>
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
			<form class="header_search" action="/WOC/SearchCommoditiesServlet">
				<div class="form-search">
					<input type="hidden" name="pageNumber" value="1" /> <input
						id="search" type="text" name="key" value="" class="input-text"
						autocomplete="off" />
					<button type="submit" title="Search"></button>
					<div style="float: right; width: 220px; margin-top: 5px">
						<table style="float: right; width: 200px;">
							<tr>
								<td><input type="radio" name="option" value="1"
									checked="checked" style="color: #FFFFFF; font-size: 16px" />&nbsp;&nbsp;关键字</td>
								<td><input type="radio" name="option" value="2"
									style="color: #FFFFFF; font-size: 16px" />&nbsp;&nbsp;地区</td>
								<td><input type="radio" name="option" value="3"
									style="color: #FFFFFF; font-size: 16px" />&nbsp;&nbsp;种类</td>
								<td><input type="radio" name="option" value="4"
									style="color: #FFFFFF; font-size: 16px" />&nbsp;&nbsp;卖家</td>
							</tr>
						</table>
					</div>
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
				<li class="active"><a href="jsp/Market/Store.jsp">商城</a> <!--SUbmenu Starts-->
					<ul class="sub_menu">
						<li>
							<ul>
								<li><a href="jsp/Market/TStore.jsp">二手商城</a></li>
								<li><a href="jsp/Market/EStore.jsp">交换平台</a></li>
								<li><a href="jsp/Market/FStore.jsp">漂流平台</a></li>
							</ul>
						</li>
					</ul> <!--SUbmenu Ends--></li>
				<li><a href="jsp/forum/forindex.jsp">论坛</a> <!--SUbmenu Starts-->
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
			<div class="minicart">
				<a href="#" class="minicart_link"> <span class="item"><b>我的订单</b>
				</span> <span class="price"><b></b></span>
				</a>
				<div class="cart_drop">
					<span class="darw"></span>
					<ul>
						<c:forEach items="${requestScope.lstUnfinishedOrdersByUser }"
							var="list">
							<li><img src="jsp/Market/images/mini_c_item1.png" /><a
								href="#">${list.comName }</a> <span class="price">￥${list.price }</span></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			</nav>
			<!--Navigation Ends-->
		</div>
		<div class="section_container" style="margin: 0 auto;">
			<!--Mid Section Starts-->
			<section>
			<div class="full_page" style="width: 40%" style="margin: 0 auto;">
				<div class="col_left_main contact_page" align="center"
					style="width: 1000px">
					<!--Contact Starts-->
					<address>
						<b>漫步云端</b><br /> 中国，天津<br /> 南开区卫津路<br /> 1-800-67-9569<br />
					</address>

					<!--Block Ends-->
					<!--Form Starts-->
					<div class="block" style="width: 100%">
						<form id="contact-us" action="/WOC/UpdatePasswordServlet"
							method="post" onsubmit="updatealert()">
							<h3>修改密码</h3>
							<ul id="contact_form">
								<li><b>旧密码：</b> <input type="password" name="oPassword"
									id="oPassword" /></li>
								<li><b>新密码：</b> <input type="password" name="nPassword"
									id="nPassword" /></li>
								<li><b>确认密码：</b> <input type="password" name="rPassword"
									id="rPassword" /></li>
								<li><b>验证码：</b> <input type="password" name="auth"
									id="auth" /></li>
								<li>
									<button name="submit" type="submit" class="subbutton brown_btn">确认</button>
								</li>
							</ul>
						</form>
						<button name="submit" type="submit" class="subbutton brown_btn" onclick="parent.location='/WOC/SendMailServlet'">获取验证码</button>
					</div>
					<!--Form Ends-->

					<!--Contact Ends-->
				</div>

			</div>
			<!--Newsletter_subscribe Starts-->
			<div class="subscribe_block">
				<div class="find_us">
					<h3>分享到</h3>
					<a class="twitter" href="#"></a> <a class="facebook" href="#"></a>
					<a class="rss" href="#"></a>
				</div>
				<div class="subscribe_nl">
					<h3>Subscribe to our Newsletter</h3>
					<small>Instant wardrobe updates, new arrivals, fashion
						news, don’t miss a beat – sign up to our newsletter now.</small>
					<form id="newsletter" method="post" action="#" />
					<input type="text" class="input-text" value="Enter your email"
						title="Enter your email" id="newsletter" name="email" />
					<button class="button" title="" type="submit"></button>
					</form>
				</div>
			</div>
			<!--Newsletter_subscribe Ends--> </section>
			<!--Mid Section Ends-->
		</div>
		<div class="footer_container">
			<!--Footer Starts-->
			<footer>
			<address>Copyright © 2014 Spaceship. All Rights Reserved.</address>
			</footer>
			<!--Footer Ends-->
		</div>
	</div>
	<!--CUSTOMIZE-->
	<style type="text/css">
.page_selector {
	position: absolute;
	right: 0px;
	top: 28%;
}

.page_selector a.page_open {
	display: inline-block;
	width: 60px;
	height: 12px;
	background: #252525;
	color: #fff;
	text-align: center;
	font-size: 14px;
	padding: 20px 0px;
	font-weight: bold;
	text-decoration: none;
	float: left;
}

.page_selector ul {
	float: left;
	background: #f0f0f0;
	padding: 10px 0px;
	border-top: solid 2px #000;
	border-bottom: solid 2px #000;
	display: none;
}

.page_selector ul li {
	padding: 8px 10px;
}

.page_selector ul li a {
	font-weight: bold;
	text-decoration: none;
	color: #000;
	display: block;
	padding: 2px 5px;
	text-transform: uppercase;
	font-size: 11px;
}

.store_selector {
	position: absolute;
	left: 0px;
	top: 28%;
}

.store_selector .store_open {
	display: inline-block;
	width: 128px;
	height: 55px;
}

.store_selector div {
	float: left;
	width: 157px;
	height: 226px;
	display: none;
}
</style>

	<script type="text/javascript">
		$(document).ready(function() {
			//SLIDE TOGGLE
			jQuery(".page_open").toggle(function() {
				$('.page_selector ul').slideDown(300);
			}, function() {
				$('.page_selector ul').slideUp(300);
			});
			//SLIDE TOGGLE
			jQuery(".store_open").toggle(function() {
				$('.store_selector div').slideDown(300);
			}, function() {
				$('.store_selector div').slideUp(300);
			});
		});
	</script>

	<div class="store_selector">
		<a href="#" class="store_open"><img src="../assets/choose.jpg" /></a>
		<div>
			<img src="../assets/shops.jpg" border="0" usemap="#Map" />
			<map name="Map">
				<area shape="rect" coords="3,171,159,222"
					href="../Lingerie_shop/leisure_index.html" />
				<area shape="rect" coords="1,115,157,166"
					href="../Watch_shop/leisure_index.html" />
				<area shape="rect" coords="0,59,156,110"
					href="../Gadget_shop/leisure_index.html" />
				<area shape="rect" coords="2,3,158,54" href="leisure_index.html" />
			</map>
		</div>
	</div>

	<div class="page_selector">
		<a href="#" class="page_open">PAGES</a>
		<ul>
			<li><a href="jsp/WelcomePage/index.jsp">首页</a></li>
			<li><a href="jsp/forum/forindex.jsp">论坛</a></li>
			<li><a href="jsp/Market/Store.jsp">商城</a></li>
			<li><a href="jsp/forum/person.jsp">个人主页</a></li>
		</ul>
	</div>
	<!--CUSTOMIZE-->
	<div style="display: none">
		<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
			language='JavaScript' charset='gb2312'></script>
	</div>



</body>
</html>
