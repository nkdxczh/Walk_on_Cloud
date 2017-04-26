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
<title>漫步云端/用户商城信息</title>
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
var code;
function perinf(){
	var user="${sessionScope.user.userId}";
	if(user=="")alert("请先登录！");
	else window.location="jsp/forum/user_regif.jsp";
}
function myalert(){
	return confirm("确认删除信息？");
}
function addfriend(fid){
	window.location="/WOC/AddFriendServlet?friendId="+fid;
}
function selectAll(name){
	var lst=document.getElementsByName(name);
	for(var i=0;i<lst.length;i++){
		lst[i].checked="checked";
	}
}
function reSelectAll(name){
	var lst=document.getElementsByName(name);
	for(var i=0;i<lst.length;i++){
		if(lst[i].checked==true)lst[i].checked=false;
		else lst[i].checked=true;
	}
}
function deletefriend(fname){
	if(confirm("确认删除该好友？")){
		window.location="/WOC/CancelFriendServlet?friendName="+fname;
	}
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

	<c:if test="${!empty requestScope.tip }">
		<c:if test="${requestScope.tip=='1' }">
			<script>window.alert("修改成功！");</script>
		</c:if>
		<c:if test="${requestScope.tip=='2' }">
			<script>window.alert("修改失败！");</script>
		</c:if>
	</c:if>
	<c:if test="${empty requestScope.flag }">
		<script>window.location="/WOC/ShowMarketInfServlet"; </script>
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
			<section>
			<ul class="breadcrumb">
				<li><a href="jsp/WelcomePage/index.jsp">首页</a></li>
				<li><a href="jsp/forum/user_regif.jsp">用户信息</a></li>
				<li class="active"><a href="#">商城信息</a></li>
			</ul>
			<!--PRODUCT DETAIL STARTS-->
			<form action="/WOC/BatchCancelOrdersServlet"
				onsubmit="return myalert()">
				<h3 style="text-align: center; width: 100%; font-size: 25px">订单</h3>
				<table style="margin: 0 auto; font-size: 20px" width="720"
					border="2">
					<tr bgcolor="#11979E" style="color: white;">
						<td width="16">&nbsp;</td>
						<td width="80">订单编号</td>
						<td width="110">商品名称</td>
						<td width="90">卖家账号</td>
						<td width="90">商品价格</td>
						<td width="110">提交时间</td>
					</tr>
					<c:forEach items="${requestScope.lstUnfinishedOrdersByUser }"
						var="inf">
						<tr bgcolor="#FFF8BF">
							<td><input style="vertical-align: middle;" type="checkbox"
								name="UnfinishedLst" id="UnfinishedLst" value=${inf.orderId }></td>
							<td>${inf.orderId }</td>
							<td><a href="jsp/Market/Detail.jsp?comId=${inf.comId }">${inf.comName }</a></td>
							<td>${inf.sellerUserName }</td>
							<td>${inf.price }</td>
							<td>${inf.ordSucTime }</td>
						</tr>
					</c:forEach>
					<tr bgcolor="#11979E">
						<td colspan="7"><input type="hidden" name="type" value="1">
							<input style="margin-bottom: 5px; margin-left: 10px"
							type="button" name="button" id="button" value="全选"
							onclick="selectAll('UnfinishedLst')"> <input
							type="button" name="button2" id="button2" value="反选"
							onclick="reSelectAll('UnfinishedLst')"> <input
							type="submit" name="button4" id="button4" value="退订"></td>
					</tr>
				</table>
				<p>&nbsp;</p>
			</form>

			<form action="/WOC/BatchCancelCommoditiesServlet"
				onsubmit="return myalert()">
				<table style="margin: 0 auto; font-size: 20px" width="720"
					border="2">
					<h3 style="text-align: center; width: 100%; font-size: 25px">我的出售商品</h3>
					<tr bgcolor="#11979E" style="color: white;">
						<td width="16">&nbsp;</td>
						<td width="80">商品编号</td>
						<td width="110">商品名称</td>
						<td width="90">商品价格</td>
						<td width="90">积分要求</td>
						<td width="110">上架时间</td>
					</tr>
					<c:forEach items="${requestScope.lstSCommodities }" var="sc">
						<tr bgcolor="#FFF8BF">
							<td><input style="vertical-align: middle;" type="checkbox"
								name="sc" id="sc" value=${sc.comId }></td>
							<td><a href="jsp/Market/Detail.jsp?comId=${sc.comId }">${sc.comId }</a></td>
							<td><a href="jsp/Market/Detail.jsp?comId=${sc.comId }">${sc.comName }</a></td>
							<td><a href="jsp/Market/Detail.jsp?comId=${sc.comId }">${sc.price }</a></td>
							<td><a href="jsp/Market/Detail.jsp?comId=${sc.comId }">${sc.requiredScore }</a></td>
							<td><a href="jsp/Market/Detail.jsp?comId=${sc.comId }">${sc.releaseTime }</a></td>
						</tr>
					</c:forEach>
					<tr bgcolor="#11979E">
						<td colspan="8"><input type="hidden" name="type" value="1">
							<input style="margin-bottom: 5px; margin-left: 10px"
							type="button" name="button" id="button" value="全选"
							onclick="selectAll('sc')"> <input type="button"
							name="button2" id="button2" value="反选"
							onclick="reSelectAll('sc')"> <input type="submit"
							name="button4" id="button4" value="下架"></td>
					</tr>
				</table>
				<p>&nbsp;</p>
			</form>

			<form action="/WOC/BatchCancelCommoditiesServlet"
				onsubmit="return myalert()">
				<table style="margin: 0 auto; font-size: 20px" width="720"
					border="2">
					<h3 style="text-align: center; width: 100%; font-size: 25px">我的交换物品</h3>
					<tr bgcolor="#11979E" style="color: white;">
						<td width="16">&nbsp;</td>
						<td width="80">商品编号</td>
						<td width="110">商品名称</td>
						<td width="90">商品价格</td>
						<td width="90">积分要求</td>
						<td width="110">上架时间</td>
					</tr>
					<c:forEach items="${requestScope.lstECommodities }" var="ec">
						<tr bgcolor="#FFF8BF">
							<td><input style="vertical-align: middle;" type="checkbox"
								name="ec" id="ec" value=${ec.comId }></td>
							<td><a href="jsp/Market/Detail.jsp?comId=${ec.comId }">${ec.comId }</a></td>
							<td><a href="jsp/Market/Detail.jsp?comId=${ec.comId }">${ec.comName }</a></td>
							<td><a href="jsp/Market/Detail.jsp?comId=${ec.comId }">${ec.price }</a></td>
							<td><a href="jsp/Market/Detail.jsp?comId=${ec.comId }">${ec.requiredScore }</a></td>
							<td><a href="jsp/Market/Detail.jsp?comId=${ec.comId }">${ec.releaseTime }</a></td>
						</tr>
					</c:forEach>
					<tr bgcolor="#11979E">
						<td colspan="8"><input type="hidden" name="type" value="2">
							<input style="margin-bottom: 5px; margin-left: 10px"
							type="button" name="button" id="button" value="全选"
							onclick="selectAll('ec')"> <input type="button"
							name="button2" id="button2" value="反选"
							onclick="reSelectAll('ec')"> <input type="submit"
							name="button4" id="button4" value="下架"></td>
					</tr>
				</table>
				<p>&nbsp;</p>
			</form>

			<form action="/WOC/BatchCancelCommoditiesServlet"
				onsubmit="return myalert()">
				<table style="margin: 0 auto; font-size: 20px" width="720"
					border="2">
					<h3 style="text-align: center; width: 100%; font-size: 25px">我的漂流物品</h3>
					<tr bgcolor="#11979E" style="color: white;">
						<td width="16">&nbsp;</td>
						<td width="80">商品编号</td>
						<td width="110">商品名称</td>
						<td width="90">商品价格</td>
						<td width="90">积分要求</td>
						<td width="110">上架时间</td>
					</tr>
					<c:forEach items="${requestScope.lstFCommodities }" var="fc">
						<tr bgcolor="#FFF8BF">
							<td><input style="vertical-align: middle;" type="checkbox"
								name="fc" id="fc" value=${fc.comId }></td>
							<td><a href="jsp/Market/Detail.jsp?comId=${fc.comId }">${fc.comId }</a></td>
							<td><a href="jsp/Market/Detail.jsp?comId=${fc.comId }">${fc.comName }</a></td>
							<td><a href="jsp/Market/Detail.jsp?comId=${fc.comId }">${fc.price }</a></td>
							<td><a href="jsp/Market/Detail.jsp?comId=${fc.comId }">${fc.requiredScore }</a></td>
							<td><a href="jsp/Market/Detail.jsp?comId=${fc.comId }">${fc.releaseTime }</a></td>
						</tr>
					</c:forEach>
					<tr bgcolor="#11979E">
						<td colspan="8"><input type="hidden" name="type" value="3">
							<input style="margin-bottom: 5px; margin-left: 10px"
							type="button" name="button" id="button" value="全选"
							onclick="selectAll('fc')"> <input type="button"
							name="button2" id="button2" value="反选"
							onclick="reSelectAll('fc')"> <input type="submit"
							name="button4" id="button4" value="下架"></td>
					</tr>
				</table>
				<p>&nbsp;</p>
			</form>

			<form action="/WOC/BatchCancelOrdersServlet"
				onsubmit="return myalert()">
				<table style="margin: 0 auto; font-size: 20px" width="720"
					border="2">
					<h3 style="text-align: center; width: 100%; font-size: 25px">交易记录</h3>
					<tr bgcolor="#11979E" style="color: white;">
						<td width="16">&nbsp;</td>
						<td width="80">订单编号</td>
						<td width="110">商品名称</td>
						<td width="90">卖家登录名</td>
						<td width="90">商品价格</td>
						<td width="110">成功时间</td>
					</tr>
					<tr bgcolor="#FFF8BF">
						<c:forEach items="${requestScope.lstFinishedOrdersByUser }"
							var="finf">
							<td><input style="vertical-align: middle;" type="checkbox"
								name="FinishedLst" id="FinishedLst" value=${finf.orderId }></td>
							<td><a href="jsp/Market/Detail.jsp?comId=${inf.comId }">${finf.orderId }</a></td>
							<td><a href="jsp/Market/Detail.jsp?comId=${inf.comId }">${finf.comName }</a></td>
							<td><a href="jsp/Market/Detail.jsp?comId=${inf.comId }">${finf.sellerUserName }</a></td>
							<td><a href="jsp/Market/Detail.jsp?comId=${inf.comId }">${finf.price }</a></td>
							<td><a href="jsp/Market/Detail.jsp?comId=${inf.comId }">${finf.ordSucTime }</a></td>
						</c:forEach>
					</tr>
					<tr bgcolor="#11979E">
						<td colspan="7"><input type="hidden" name="type" value="2">
							<input style="margin-bottom: 5px; margin-left: 10px"
							type="button" name="button" id="button" value="全选"
							onclick="selectAll('FinishedLst')"> <input type="button"
							name="button2" id="button2" value="反选"
							onclick="reSelectAll('FinishedLst')"> <input
							type="submit" name="button4" id="button4" value="删除"></td>
					</tr>
				</table>
				<p>&nbsp;</p>
			</form>

			<form action="/WOC/BatchCancelAttentionServlet"
				onsubmit="return myalert()">
				<table style="margin: 0 auto; font-size: 20px" width="720"
					border="2">
					<h3 style="text-align: center; width: 100%; font-size: 25px">关注商品</h3>
					<tr bgcolor="#11979E" style="color: white;">
						<td width="16">&nbsp;</td>
						<td width="80">商品编号</td>
						<td width="110">商品名称</td>
						<td width="90">商品状态</td>
						<td width="90">商品价格</td>
						<td width="90">积分要求</td>
					</tr>
					<c:forEach items="${requestScope.lstACommodities }" var="ac">
						<tr bgcolor="#FFF8BF">
							<td><input style="vertical-align: middle;" type="checkbox"
								name="ac" id="ac" value=${ac.comId }></td>
							<td>${ac.comId }</td>
							<td>${ac.comName }</td>
							<td>${ac.status }</td>
							<td>${ac.price }</td>
							<td>${ac.requiredScore }</td>
						</tr>
					</c:forEach>
					<tr bgcolor="#11979E">
						<td colspan="8"><input
							style="margin-bottom: 5px; margin-left: 10px" type="button"
							name="button" id="button" value="全选" onclick="selectAll('ac')">
							<input type="button" name="button2" id="button2" value="反选"
							onclick="reSelectAll('ac')"> <input type="submit"
							name="button4" id="button4" value="取消关注"></td>
					</tr>
				</table>
				<p>&nbsp;</p>
			</form>
			<!--PRODUCT DETAIL ENDS--> <!--Product List Starts-->
			<div class="products_list products_slider">
				<h2 class="sub_title">好友推荐</h2>
				<ul id="first-carousel"
					class="first-and-second-carousel jcarousel-skin-tango">
					<c:forEach items="${requestScope.lstFriends }" var="friend">
						<li><a class="product_image"><img
								src="/WOC/jsp/user_images/${friend.userPhoto }" /></a>
							<div class="product_info">
								<h3>
									<a href="jsp/forum/person.jsp?userid=${friend.userId }">${friend.nickName}</a>
								</h3>
								<div>
									<small>积分：${friend.score }</small>
								</div>
							</div>
							<div class="price_info">
								<button class="price_add" title="" type="button"
									onclick="addfriend('${friend.nickName}')">
									<span class="pr_price">加为好友</span><span class="pr_add">加为好友</span>
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