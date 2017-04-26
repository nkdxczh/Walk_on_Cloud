<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>漫步云端/商城首页</title>
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
<script type="text/javascript">
function SetCookie(name,value)//两个参数，一个是cookie的名子，一个是值
{
    var Days = 30; //此 cookie 将被保存 30 天
    var exp  = new Date();    //new Date("December 31, 9998");
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}
function getCookie(name)//取cookies函数        
{
    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
     if(arr != null) return unescape(arr[2]); return null;

}
function delCookie(name)//删除cookie
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null) document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}
function resetTypeCount(type, params)
{
	if(type=="1"){
		var count=parseInt(params.substring(params.indexOf("n",1)+1, params.indexOf("b",1)));
		count++;
		//alert(params.replace("n"+params.substring(params.indexOf("n",1)+1, params.indexOf("b",1))+"b","n"+String(count)+"b"));
		return params.replace("n"+params.substring(params.indexOf("n",1)+1, params.indexOf("b",1))+"b","n"+String(count)+"b");
	}
	if(type=="2"){
		var count=parseInt(params.substring(params.indexOf("n",2)+1, params.indexOf("b",2)-1))
		return params.replace("n"+params.substring(params.indexOf("n",2)+1, params.indexOf("b",2))+"b","n"+String(count)+"b");
	}
	if(type=="3"){
		var count=parseInt(params.substring(params.indexOf("n",3)+1, params.indexOf("b",3)-1))
		return params.replace("n"+params.substring(params.indexOf("n",3)+1, params.indexOf("b",3))+"b","n"+String(count)+"b");
	}
}
function getParams(name){
	var reg=new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r=window.location.search.substr(1).match(reg);
	if(r!=null){
		return unescape(r[2]);
	}
	return null;
}
</script>
<script type="text/javascript">
	var username=${sessionScope.user.userName};
	var type=${type};
	var oldParams=getCookie(username);
	if(oldParams==null){
		SetCookie(username, "1n0b2n0b3n0b0");
	}else{
		var k=resetTypeCount(type, oldParams);
	//alert(k);
	SetCookie(username, resetTypeCount(type, oldParams));
	}
</script>
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
<script>
var code;
function perinf(){
	var user="${sessionScope.user.userId}";
	if(user!=null)window.location="jsp/forum/user_regif.jsp";
	else alert("请先登录！");
}
function release(){
	var user="${sessionScope.user.userId}";
	if(user=="")alert("请先登录！");
	else window.location="jsp/Market/Release.jsp";
}
function order(Id){
	var user="${sessionScope.user.userId}";
	if(user!='')window.location="jsp/Market/Order.jsp?comid="+Id;
	else alert("请先登录！");
}
function atten(Id){
	var user="${sessionScope.user.userId}";
	if(user!='')window.location="/WOC/ApplyAttenttionSevlet?comId="+Id;
	else alert("请先登录！");
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
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body  onload="onPageLoad();dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);;">

	<%  
    if(request.getAttribute("flag")==null){
        int p=Integer.parseInt(request.getParameter("comId"));
        response.sendRedirect("/WOC/ShowCommodityInfServlet?comId="+p);
    }
%>

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
				<a href="jsp/WelcomePage/index.jsp"><img
					src="jsp/Market/images/logo.png" /></a>
			</h1>
			<!--Logo Ends--> <!--Responsive NAV-->
			<div class="responsive-nav" style="display: none;"></div>
			<!--Responsive NAV--> <!--Search Starts-->
			<form class="header_search" action="/WOC/SearchCommoditiesServlet" />
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
			<section>
			<ul class="breadcrumb">
				<li><a href="jsp/Market/Store.jsp">首页</a></li>
				<li><a href="jsp/Market/TStore.jsp">二手商城</a></li>
				<li class="active"><a href="#">商品具体信息</a></li>
			</ul>
			<!--PRODUCT DETAIL STARTS-->
			<div id="product_detail">
				<!--Product Left Starts-->
				<div class="product_leftcol">
					<img
						src="/WOC/jsp/Market/images/${requestScope.commodity.picturePath }" />
					<span class="pr_info"></span>
					<ul class="pr_gallery">
						<c:forEach items="${requestScope.lstPicture }" var="pic">
							<li><a href="#"><img
									src="/WOC/jsp/Market/images/${pic.path }" /></a></li>
						</c:forEach>
						<c:forEach items="${requestScope.lstPicture }" var="pic">
							<li><a href="#"><img
									src="/WOC/jsp/Market/images/${pic.path }" /></a></li>
						</c:forEach>
						<c:forEach items="${requestScope.lstPicture }" var="pic">
							<li><a href="#"><img
									src="/WOC/jsp/Market/images/${pic.path }" /></a></li>
						</c:forEach>
					</ul>
				</div>
				<!--Product Left Ends-->
				<!--Product Right Starts-->
				<div class="product_rightcol">
					<small class="pr_type">物品信息</small>
					<h1 style="font-size: 30px;">${requestScope.commodity.comName }</h1>
					<p class="short_dc" />
					${requestScope.commodity.describe }

					<div class="pr_price">
						<big>￥${requestScope.commodity.price }</big>
					</div>
					<div class="size_info">
						<div class="size_sel">
							<label style="font-size: 15px;">积分要求
								：${requestScope.commodity.requiredScore }</label>
						</div>
						<div class="color_sel">
							<label style="font-size: 15px;">分类
								：${requestScope.commodity.comTypeName}</label>
						</div>
					</div>
					<div class="size_info">
						<div class="size_sel">
							<label style="font-size: 15px;"><a
								style="color: rgba(86, 85, 85, 1.00)"
								href="jsp/forum/person.jsp?userid=${requestScope.commodity.ownerId }">发布者
									：${requestScope.commodity.nickName }</a></label>
						</div>
						<div class="color_sel">
							<label style="font-size: 15px;">地区
								：${requestScope.commodity.comRegion}</label>
						</div>
					</div>
					<div class="size_info">
						<div class="size_sel">
							<label style="font-size: 15px;">发布时间
								：${requestScope.commodity.releaseTime }</label>
						</div>
					</div>
					<div class="add_to_buttons">
						<button onclick="order('${requestScope.commodity.comId}');"
							class="add_cart">下订单</button>
						<span>or</span>
						<button onclick="atten('${requestScope.commodity.comId}');"
							class="add_cart">加关注</button>
					</div>
					<div class="product_overview">
						<h4>评论</h4>
						<ul>
							<c:forEach items="${requestScope.lstCComments }" var="comment">
								<li>${comment.comComment }</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<!--Product Right Ends-->
			</div>
			<!--PRODUCT DETAIL ENDS--> <!--Product List Starts-->
			<div class="products_list products_slider">
				<h2 class="sub_title">你可能喜欢...</h2>
				<ul id="first-carousel"
					class="first-and-second-carousel jcarousel-skin-tango">
					<c:forEach items="${requestScope.lstLike }" var="com">
						<li><a class="product_image"><img
								src="/WOC/jsp/Market/images/${com.picturePath }" /></a>
							<div class="product_info">
								<h3>
									<a href="jsp/Market/Detail.jsp?comId=${com.comId }">${com.comName}</a>
								</h3>
								<div>
									<small>关注度：${com.focusNumber }</small>
								</div>
								<div>
									<small>${com.describe }</small>
								</div>
							</div>
							<div class="price_info">
								<a href="#">+ 加关注</a>
								<button class="price_add" title="" type="button"
									onclick="order('${com.comId}');">
									<span class="pr_price">${com.price }</span><span class="pr_add">下订单</span>
								</button>
							</div></li>
					</c:forEach>
				</ul>
			</div>
			<!--Product List Ends--> <!--Newsletter_subscribe Starts-->
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