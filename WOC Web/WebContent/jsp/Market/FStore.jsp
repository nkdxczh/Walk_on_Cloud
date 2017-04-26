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

<title>漫步云端/漂流平台</title>
<!--CSS-->
<link rel="stylesheet" href="jsp/Market/css/styles.css" />
<!--Google Webfont -->
<link href="jsp/WelcomePage/index_files/lanrenzhijia.css"
	rel="stylesheet" type="text/css" media="all">
<!--Javascript-->
<script type="text/javascript" src="jsp/Market/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="jsp/Market/j0s/jquery.flexslider.js"></script>
<script type="text/javascript" src="jsp/Market/js/jquery.easing.js"></script>
<script type="text/javascript" src="jsp/Market/js/jquery.jcarousel.js"></script>
<script type="text/javascript" src="jsp/Market/js/form_elements.js"></script>
<!--[if lt IE 9]>
    <script src="js/html5.js"></script>
<![endif]-->
<!-- mobile setting -->

<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
function sort(type){
	var key="${requestScope.key}";
	window.location="/WOC/SearchCommoditiesListServlet?key=${requestScope.key}&pageNumber=1&jsp=4&sort="+type;
}
function order(Id){
	var user="${sessionScope.user.userId}";
	if(user!='')window.location="jsp/Market/Order.jsp?comid="+Id;
	else alert("请先登录！");
}
function perinf(){
	var user="${sessionScope.user.userId}";
	if(user=="")alert("请先登录！");
	else window.location="jsp/forum/user_regif.jsp";
}
function release(){
	var user="${sessionScope.user.userId}";
	if(user=="")alert("请先登录！");
	else window.location="jsp/Market/Release.jsp";
}
function checkUserId(){
	if(document.getElementById('log').value==''){
		document.getElementById('userId').innerHTML='请输入用户名';
		document.getElementById('userId').style.display='';
		return false;
	}
	return true;
}
function IdfocusEve(){
	document.getElementById('userId').innerHTML='';
	document.getElementById('userId').style.display='none';
}
function checkUserPwd(){
	if(document.getElementById('pwd').value==''){
		document.getElementById('userPwd').innerHTML='请输入密码';
		document.getElementById('userPwd').style.display='';
		return false;
	}
	return true;
}
function PwdfocusEve(){
	document.getElementById('userPwd').innerHTML='';
	document.getElementById('userPwd').style.display='none';
}
function VadfocusEve(){
	document.getElementById('vad').innerHTML='';
	document.getElementById('vad').style.display='none';
}
function createCode(){
	code="";
	var codeLen=4;
	var checkCode=document.getElementById("valcode");
	var codeChar=new Array(0,1,2,3,4,5,6,7,8,9,'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t'
	,'u','v','w','x','y','z');
	for(var i=0;i<4;i++){
		var charNum=Math.floor(Math.random()*36);
		code=code+codeChar[charNum];
	}
	checkCode.innerHTML=code;
}
function checkCode(){
	var input=document.getElementById("val").value;
	if(input!=code){
		document.getElementById("vad").style.display="";
		document.getElementById("vad").innerHTML="验证码错误";
		return false;
	}
	return true;
}
function loginEvent(){
	if(checkUserId()&&checkUserPwd()&&checkCode()){
		return true;
	}
	return false;
}
function loginInit(){
	document.getElementById('log').value="";
	document.getElementById('pwd').value="";
	document.getElementById('val').value="";
	createCode();
	PwdfocusEve();
	IdfocusEve();
	VadfocusEve();
}
</script>
</head>
<body  onload="onPageLoad();dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);;">

	<c:if test="${empty requestScope.flag }">
		<script>window.location="/WOC/SearchCommoditiesListServlet?key=12&pageNumber=1&sort=1&jsp=4"; </script>
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
		<div class="header_container">
			<!--Header Starts-->
			<header>

			<div class="top_bar clear">
				<!--Top Links Starts-->
				<ul class="top_links">
					<li><a href="jsp/WelcomePage/login.jsp">注册</a></li>
					<c:if test="${!empty sessionScope.user }">
						<li><a onclick="perinf();">个人主页</a></li>
						<li><a onclick="release();">发布物品</a></li>
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
				<a href="jsp/WelcomPage/index.jsp"><img
					src="jsp/Market/images/logo.png" /></a>
			</h1>
			<!--Logo Ends--> <!--Responsive NAV-->
			<div class="responsive-nav" style="display: none;"></div>
			<!--Responsive NAV--> <!--Search Starts-->
			<form class="header_search" action="/WOC/SearchCommoditiesServlet" />
			<div class="form-search">
				<input type="hidden" name="pageNumber" value="1" /> <input
					type="hidden" name="sort" value="1" /> <input id="search"
					type="text" name="key" value="" class="input-text"
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
				<li><a href="jsp/WelcomePage/index.jsp">首页</a></li>
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
		<div class="section_container">
			<!--Mid Section Starts-->
			<section> <!--SIDE NAV STARTS-->
			<div id="side_nav">
				<div class="sideNavCategories">
					<h1 style="font-family: 宋体; font-size: 20px;">漂流商品</h1>
					<ul class="category departments">
						<li class="header" style="font-family: 宋体; font-size: 16px;">分类</li>
						<li><a
							href="/WOC/SearchCommoditiesListServlet?key=7&pageNumber=1&sort=1&jsp=4">书籍</a></li>
						<li><a
							href="/WOC/SearchCommoditiesListServlet?key=8&pageNumber=1&sort=1&jsp=4">日用品</a></li>
						<li><a
							href="/WOC/SearchCommoditiesListServlet?key=9&pageNumber=1&sort=1&jsp=4">其他</a></li>
					</ul>
					<ul class="category departments">
						<li class="header" style="font-family: 宋体; font-size: 16px;">关注度</li>
						<li><a
							href="/WOC/SearchCommoditiesListServlet?key=19&pageNumber=1&sort=1&jsp=4">20以下</a></li>
						<li><a
							href="/WOC/SearchCommoditiesListServlet?key=20&pageNumber=1&sort=1&jsp=4">20到100</a></li>
						<li><a
							href="/WOC/SearchCommoditiesListServlet?key=21&pageNumber=1&sort=1&jsp=4">100以上</a></li>
					</ul>
					<ul class="category departments">
						<li class="header" style="font-family: 宋体; font-size: 16px;">积分要求</li>
						<li><a
							href="/WOC/SearchCommoditiesListServlet?key=34&pageNumber=1&sort=1&jsp=4">50元以下</a></li>
						<li><a
							href="/WOC/SearchCommoditiesListServlet?key=35&pageNumber=1&sort=1&jsp=4">50到200元</a></li>
						<li><a
							href="/WOC/SearchCommoditiesListServlet?key=36&pageNumber=1&sort=1&jsp=4">200元以上</a></li>
					</ul>
					<ul class="category departments">
						<li class="header" style="font-family: 宋体; font-size: 16px;">发布时间</li>
						<li><a
							href="/WOC/SearchCommoditiesListServlet?key=28&pageNumber=1&sort=1&jsp=4">一周内</a></li>
						<li><a
							href="/WOC/SearchCommoditiesListServlet?key=29&pageNumber=1&sort=1&jsp=4">一个月内</a></li>
						<li><a
							href="/WOC/SearchCommoditiesListServlet?key=30&pageNumber=1&sort=1&jsp=4">一个月以上</a></li>
					</ul>
				</div>
			</div>
			<!--SIDE NAV ENDS--> <!--MAIN CONTENT STARTS-->
			<div id="main_content">
				<div class="category_banner">
					<img src="jsp/Market/images/promo_cat_banner.jpg" />
				</div>
				<ul class="breadcrumb">
					<li><a href="jsp/WelcomePage/index.jsp">首页</a></li>
					<li><a href="jsp/Market/Store.jsp">商城</a></li>
					<li class="active"><a href="jsp/Market/FStore.jsp">漂流平台</a></li>
				</ul>
				<!--Toolbar-->
				<div class="toolbar">
					<div class="sortby">
						<label>排序:</label> <select onchange="sort(this.value)">
							<option value="1" />时间
							<option value="2" />价格
							<option value="3" />积分
							<option value="4" />关注度
						</select>
					</div>
					<div class="viewby">
						<label>View as :</label> <a class="list" href="#"></a> <a
							class="grid" href="#"></a>
					</div>
					<div class="show_no">
						<label>Show :</label> <select>
							<option />12 ITEMS
							<option />24 ITEMS
						</select>
					</div>
				</div>
				<!--Toolbar-->
				<!--Product List Starts-->
				<div class="products_list products_slider">
					<ul>
						<c:forEach items="${requestScope.lstCommodities }" var="com">
							<li><a class="product_image"><img
									src="/WOC/jsp/Market/images/${com.picturePath }" /></a>
								<div class="product_info">
									<h3>
										<a href="jsp/Market/Detail.jsp?comId=${com.comId }">${com.comName}</a>
									</h3>
									<div>
										<small>关注度：${com.focusNumber }</small>
									</div>
								</div>
								<div class="price_info">
									<a href="/WOC/ApplyAttenttionSevlet?comId=${com.comId }&jsp=4">+
										加关注</a>
									<button class="price_add" title="" type="button"
										onclick="order('${com.comId}');">
										<span class="pr_price">${com.price }</span><span
											class="pr_add">Add to Cart</span>
									</button>
								</div></li>
						</c:forEach>
					</ul>
				</div>
				<!--Product List Ends-->
				<!--Toolbar-->
				<div class="page" style="margin-bottom: 10px; margin-left: 250px;">
					<a
						href="/WOC/SearchCommoditiesListServlet?key=${requestScope.key }&pageNumber=${requestScope.pageNumber-1 }&sort=${requestScope.sort }&jsp=4">上一页</a>
					<c:forEach var="i" begin="1" end="${requestScope.pageCount }">
						<c:if test="${requestScope.pageNumber==i }">
							<a style="font-size: 20px;"
								href="/WOC/SearchCommoditiesListServlet?key=${requestScope.key }&pageNumber=${i }&sort=${requestScope.sort }&jsp=4">${i }</a>
						</c:if>
						<c:if test="${requestScope.pageNumber!=i }">
							<a style="width: 10px"
								href="/WOC/SearchCommoditiesListServlet?key=${requestScope.key }&pageNumber=${i }&sort=${requestScope.sort }&jsp=4">${i }</a>
						</c:if>
					</c:forEach>
					<a
						href="/WOC/SearchCommoditiesListServlet?key=${requestScope.key }&pageNumber=${requestScope.pageNumber+1 }&sort=${requestScope.sort }&jsp=4">下一页</a>
				</div>
				<div class="toolbar">
					<div class="sortby">
						<label>排序:</label> <select onchange="sort(this.value)">
							<option value="1" />时间
							<option value="2" />价格
							<option value="3" />积分
							<option value="4" />关注度
						</select>
					</div>
					<div class="viewby">
						<label>View as :</label> <a class="list" href="#"></a> <a
							class="grid" href="#"></a>
					</div>
					<div class="show_no">
						<label>Show :</label> <select>
							<option />12 ITEMS
							<option />24 ITEMS
						</select>
					</div>
				</div>
				<!--Toolbar-->
			</div>
			<!--MAIN CONTENT ENDS--> <!--Newsletter_subscribe Starts-->
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
$(document).ready(function(){
	//SLIDE TOGGLE
	jQuery(".page_open").toggle(function() {
		 $('.page_selector ul').slideDown(300);	
		 }, function(){
		 $('.page_selector ul').slideUp(300);		 
	});	
	//SLIDE TOGGLE
	jQuery(".store_open").toggle(function() {
		 $('.store_selector div').slideDown(300);	
		 }, function(){
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
