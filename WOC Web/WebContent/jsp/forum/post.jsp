<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="org.gr.pm.db.*"%>
<%@page import="org.gr.woc.po.*"%>
<%@page import="org.gr.woc.biz.impl.*"%>
<%@page import="org.gr.woc.biz.*"%>
<%@page import="java.util.*"%>
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
					autoplay	:	true
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
function perinf(){
	var user="${sessionScope.user.userId}";
	if(user=="")alert("请先登录！");
	else window.location="jsp/forum/user_regif.jsp";
}
function order(Id){
	var user="${sessionScope.user.userId}";
	if(user!='')window.location="jsp/Market/Order.jsp?comid="+Id;
	else alert("请先登录！");
}
function release(){
	var user="${sessionScope.user.userId}";
	if(user=="")alert("请先登录！");
	else window.location="jsp/forum/release_post.jsp";
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
function showReply(id){
	var lst=document.getElementsByName("comment");
	for(var i=0;i<lst.length;i++){
		lst[i].style.display="none";
	}
	document.getElementById(id).style.display="block";
}
function hideReply(id){
	document.getElementById(id).style.display="none";
}
function setReply(id){
	request.setAttribute("postreplyid",id);
}
function setReplyId(id){
	var item=document.getElementById("postreplyid");
	item.value=id;
}
</script>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body
	onload="onPageLoad();dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);;">

	<%  
    if(request.getAttribute("post")==null){
        int p=Integer.parseInt(request.getParameter("postid"));
        response.sendRedirect("/WOC/ShowPostServelet?postid="+p);
    }
%>
	<c:if test="${empty requestScope.flag }">
		<script>
			window.location = "/WOC/GetBestPostsServlet";
		</script>
	</c:if>
	<div id="123"
		style="width: 220px; height: 100px; z-index: 9998; position: fixed; left: 70%; top: 10%; background-color: #F0F7F0; border: solid 2px #F0E0FE; opacity: 0.7; text-align: center; display: none">
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

				<div class="top_bar clear" style="height: 44px;">
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



	<div class="content-bg">
		<div class="wrap">
			<div class="main">
				<div class="content">
					<p>
					<h2>帖子信息</h2>
					</p>
					<div class="section group">
						<div class="cont">
							<div class="single">
								<div class="grid-img1">
									<a href="/WOC/jsp/forum/post_images/${post.resPath }"><img
										src="/WOC/jsp/forum/post_images/${post.resPath }"></a>
								</div>
								<div class="para">
									<h4>${post.postName }</h4>
									<div class="cart-b">
										<div class="btn1 right">
											<c:if test="${!empty sessionScope.user }">
												<form action="ExpressLikeServlet">
													<input type="hidden" name="para" value="1"> <input
														type="hidden" name="url" value=${post.postId }> <input
														type="hidden" name="postid" value=${post.postId }>
													<input
														style="background-color: #4D9EB3; color: white; width: 80px; height: 30px;"
														type="submit" value="点赞">
												</form>
											</c:if>
										</div>
										<div class="clear"></div>
									</div>
									<div>
										<div class="clear"></div>
										<div class="post-head-left">
											<div class="post-head-title">
												<p>类型：</p>
												<p>发帖时间：</p>
												<p>资源：</p>
											</div>
											<div class="post-head-content">
												<p>${post.propertyName }</p>
												<p>${post.postTime }</p>
												<c:forEach items="${requestScope.lstPostResource }"
													var="postRes">
													<li style="float: left; font-size: 15px; margin: 5px 0 0 0"><a
														href="/WOC/jsp/forum/post_images/${postRes.resPath }">${postRes.resPath };</a></li>
												</c:forEach>
											</div>
										</div>
										<div class="post-head-right">
											<div class="post-head-title">
												<p>评论人数：</p>
												<p>点赞数：</p>
											</div>
											<div class="post-head-content">
												<p>${post.comCount }</p>
												<p>${post.scoreCount }</p>
											</div>
										</div>
									</div>
								</div>
								<div class="clear"></div>
							</div>
						</div>
					</div>
				</div>


				<div class="sidebar">
					<div class="sidebar-list">
						<h2>相关帖子</h2>
						<ul>
							<marquee vspace="20" behavior="scroll" scrollAmount="1"
								scrollDelay="0" direction="up" width="98%" valign="top"
								onMouseOut="this.start()" onMouseOver="this.stop()">
								<c:forEach items="${requestScope.lstRelatedPosts }" var="post">
									<li style="width: 100%"><a
										href="jsp/forum/post.jsp?postid=${post.postId }">${post.postName }</a></li>
								</c:forEach>
							</marquee>
						</ul>
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
	<div class="comment-list">
		<div class="wrap">
			<p>
			<h2>评论</h2>
			</p>
			<%
        ICommentBiz cz=new CommentBizImpl();
        Post p=new Post();
        int postid=Integer.parseInt((String)request.getParameter("postid"));
        p.setPostId(postid);
        int postreplyid=0;
        List<Post_Comment> lstComment=cz.searchInfByPostId(p);
        Collections.sort(lstComment);
        for(Post_Comment pcomment:lstComment){
    %>
			<div class="comment">
				<div class="comment-inf">
					<div class="comment-inf-pic">
						<a href="jsp/forum/person.jsp?userid=<%=pcomment.getUserId() %>"><img
							src="/WOC/jsp/user_images/<%=pcomment.getUserPhoto() %>"
							alt="jsp/user_images/pic12.jpg" /></a>
					</div>
					<div class="comment-inf-content">
						<p>&nbsp;</p>
						<p>
							昵称：<%=pcomment.getReleaseUserName() %></p>
						<p>
							等级：<%=pcomment.getUserLevel() %></p>
						<p>
							评论时间：<%=pcomment.getPostComTime() %></p>
						<a
							href="javascript:showReply('comment<%=pcomment.getPostComId()%>');"
							onclick="setReplyId('<%=pcomment.getPostComId()%>')">点击回复</a>
					</div>
				</div>
				<div class="comment-content"><%=pcomment.getPostComContent() %></div>
				<%
                ICommentBiz biz=new CommentBizImpl();
    		    Post post=new Post();
    		    post.setPostId(postid);
    		    List<Post_Comment> lstInnerComment=new ArrayList<Post_Comment>();
    		    biz.infSearchByLayerId(post, pcomment.getPostComId(), lstInnerComment);
    		    Collections.sort(lstInnerComment);
    		    for(Post_Comment icomment:lstInnerComment){
    		  %>
				<div class="comment-in">
					<p style="float: left;"><%=icomment.getReleaseUserName() %>回复<%=icomment.getReplyUserName() %>:<%=icomment.getPostComContent() %></p>
					<a
						href="javascript:showReply('comment<%=pcomment.getPostComId()%>');"
						style="float: right;"
						onclick="setReplyId('<%=icomment.getPostComId()%>')">回复</a>
					<p style="float: right; margin-right: 7px;"><%=icomment.getPostComTime() %></p>
				</div>
				<%} %>
				<form action="/WOC/ReleaseCommentServelet" method="post"
					name="comment" id="comment<%=pcomment.getPostComId()%>"
					style="display: none; float: right; width: 800px; margin-right: 30px">
					<textarea style="width: 100%;" rows="10" name="commentContent"
						id="commentContent"></textarea>
					<input type="hidden" id="postid" name="postid"
						value="<%=request.getParameter("postid")%>" /> <input
						type="hidden" id="postreplyid" name="postreplyid" /> <input
						type="submit" value="提交"> <input type="button" value="放弃"
						onclick="javascript:hideReply('comment<%=pcomment.getPostComId()%>');">
				</form>
			</div>
			<%} %>
		</div>
	</div>
	<div class="release-comment">
		<div class="wrap">
			<h2>发表评论</h2>
			<form action="/WOC/ReleaseCommentServelet" method="post">
				<input type="hidden" id="postid" name="postid"
					value="<%=request.getParameter("postid")%>" /> <input type="hidden"
					id="postreplyid" name="postreplyid" value="0" />
				<textarea name="commentContent" id="commentContent"
					style="width: 99.5%; height: 100px"></textarea>
				<input style="width: 80px; height: 30px;" type="submit" value="提交">
			</form>
		</div>
	</div>

	<div class="clear"></div>
	<div class="footer1-bg">
		<div class="wrap">
			<div class="copy">
				<p>© 2014 All rights Reserved | Design by spaceship</p>
			</div>
		</div>
	</div>

</body>
</html>