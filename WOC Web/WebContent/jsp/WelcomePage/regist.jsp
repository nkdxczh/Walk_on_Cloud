<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0039)http://zc.qq.com/chs/index.html -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>注册</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<SCRIPT type=text/javascript>	var isd_t=[];isd_t.push(new Date()-0);</SCRIPT>
<LINK rel=stylesheet type=text/css href="regist_files/index.css">
<LINK rel=stylesheet type=text/css href="regist_files/g.css">
<style type="text/css">
.theme {
	z-index: 9999;
	position: fixed;
	top: 50%;
	left: 50%;
	width: 660px;
	height: 360px;
	margin: -180px 0 0 -330px;
	border-radius: 5px;
	border: solid 2px #666;
	background-color: #fff;
	display: none;
	box-shadow: 0 0 10px #666;
}
</style>
<META name=GENERATOR content="MSHTML 8.00.6001.18702">
</HEAD>
<BODY <%=basePath%>>
	<DIV id=web_notice class=web_notice>
		<DIV id=web_notice_text class=web_notice_text></DIV>
	</DIV>
	<DIV id=cover class=cover></DIV>
	<DIV class=box_>
		<DIV id=chk_email_code_box class=chk_email_code>
			<A class=close onclick=window.index&amp;&amp;index.hideEmailCode()></A>
			<H2>操作提示</H2>
			<DIV class=div_1>
				<SPAN class=chkError></SPAN>您短时间内尝试次数过多，请填写验证码或稍后再试
			</DIV>
			<DIV class=div_2>
				<SPAN>验证码:</SPAN><INPUT id=email_code_ipt maxLength=8 type=text
					name=email_code autocomplete="off"><SPAN
					id=email_code_ipt_err>验证码错误</SPAN>
			</DIV>
			<DIV class=div_3>
				按下图字符填写，不区分大小写。<BR>
				<IMG id=email_code_img title=请输入验证码
					onclick=window.index&amp;&amp;index.changeEmailCode() alt=验证码
					src="regist_files/chs.htm"><BR>
				<A onclick=window.index&amp;&amp;index.changeEmailCode()>看不清楚？换一个</A><BR>
				<INPUT onclick=window.index&amp;&amp;index.ajaxChkEmail()
					type=button>
			</DIV>
		</DIV>
	</DIV>
	<DIV class=bg>
		<DIV class=container>
			<DIV class=header>
				<DIV id=lang_box></DIV>
			</DIV>
			<DIV class=content>
				<DIV class="left">
					<div id=nav_1 class="hide"></div>
					<div id=nav_3 class="hide"></div>
					<div id=nav_2 class="hide"></div>
					<DIV class=search>
						<DIV class=ipt_box>
							<DIV class=clear></DIV>
						</DIV>
					</DIV>
				</DIV>
				<DIV class=right>
					<DIV id=phone_quick class=phone>
						<DIV id=quickReg class=quickReg></DIV>
					</DIV>
					<DIV>
						<FORM onsubmit="return startRequest();" action="">
							<DIV id=email_box class=hide>
								<DIV style="DISPLAY: none" id=email_tips class="box box_1">
									<DIV class=item></DIV>
									<DIV class="ipt_box method_box nobg">
										<A id=email_2
											onmouseover="this.setAttribute('_hover','over');" tabIndex=2
											onmouseout="this.removeAttribute('_hover');"
											onclick=index.needShowError[2]=0;index.changeMethod(2);
											name=new_other_mail><SPAN class=tahoma>QQ/Foxmail</SPAN>
										</A><A id=email_1 class=checked
											onmouseover="this.setAttribute('_hover','over');" tabIndex=1
											onmouseout="this.removeAttribute('_hover');"
											onclick=index.needShowError[1]=0;index.changeMethod(1);
											name=new_self_mail><SPAN class=tahoma>QQ/Foxmail</SPAN> </A>
									</DIV>
									<DIV class=info>
										<TABLE>
											<TBODY>
												<TR>
													<TD class=hidden></TD>
												</TR>
											</TBODY>
										</TABLE>
									</DIV>
									<DIV class=clear></DIV>
								</DIV>
								<DIV class="box box_2">
									<LABEL class=item for=other_email></LABEL>
									<DIV style="Z-INDEX: 99" id=mail_box class="ipt_box nobg self">
										<DIV class=ipt_other>
											<DIV id=other_email_bg class=bg_txt>
												<INPUT id=other_email class=new_txt tabIndex=3 maxLength=38
													type=text name=other_email autocomplete="off">
											</DIV>
											<UL id=other_email_ul class="other_email_ul hide"></UL>
										</DIV>
										<DIV class=ipt_self>
											<INPUT id=self_email tabIndex=4 maxLength=18 type=text
												name=self_email autocomplete="off">
											<DIV class=outer>
												<DIV id=selfType tabIndex=5>@qq.com</DIV>
												<UL id=selfTypeBox class=hide>
													<LI id=selfType0>@qq.com</LI>
													<LI id=selfType1>@foxmail.com</LI>
												</UL>
											</DIV>
										</DIV>
									</DIV>
									<DIV class=info>
										<TABLE>
											<TBODY>
												<TR>
													<TD id=email_info class=hidden></TD>
												</TR>
											</TBODY>
										</TABLE>
									</DIV>
								</DIV>
							</DIV>
							<DIV id=phone_box class=hide>
								<DIV id=sea_phone class="box box_2 sea_phone">
									<LABEL class=item for=sea_country_input></LABEL>
									<DIV id=sea_country_box class=sea_country_box>
										<DIV class=sea_area_value_bg>
											<INPUT id=sea_country_input class=sea_country_input
												tabIndex=2 autocomplete="off">
										</DIV>
									</DIV>
								</DIV>
								<DIV id=land_phone class="box box_2 land_phone">
									<LABEL class=item for=phone_num></LABEL>
									<DIV class=ipt_box>
										<DIV class=ipt_other>
											<DIV id=phone_num_bg class=bg_txt>
												<INPUT id=phone_num class=new_txt tabIndex=4 maxLength=11
													type=text name=phone_num autocomplete="off">
											</DIV>
										</DIV>
									</DIV>
									<DIV class=info>
										<TABLE>
											<TBODY>
												<TR>
													<TD id=phone_info class=hidden></TD>
												</TR>
											</TBODY>
										</TABLE>
									</DIV>
								</DIV>
								<DIV id=phone_num_tips class="paopao phone_tips">
									<SPAN class=p_r></SPAN>
								</DIV>
							</DIV>
							<DIV style="Z-INDEX: 0" class="box box_3">
								<LABEL class=item for=nick>用户名</LABEL>
								<DIV class="ipt_box nick_ipt_box">
									<DIV id=nick_bg class=bg_txt>
										<INPUT style="Z-INDEX: 0" id=nick class=new_txt tabIndex=6
											maxLength=24 type=text name=nick autocomplete="off">
									</DIV>
								</DIV>
								<DIV style="Z-INDEX: 0" class="box box_3">
									<LABEL class=item for=nickname>昵称</LABEL>
									<DIV class="ipt_box nick_ipt_box">
										<DIV id=nickna class=bg_txt>
											<INPUT style="Z-INDEX: 0" id=nickname class=new_txt
												tabIndex=6 maxLength=24 type=text name=nickname
												autocomplete="off">
										</DIV>
									</DIV>
									<DIV class=info>
										<TABLE>
											<TBODY>
												<TR>
													<TD id=nick_info class=hidden></TD>
												</TR>
											</TBODY>
										</TABLE>
									</DIV>
								</DIV>
								<DIV class="box box_4">
									<LABEL class=item for=password>密码</LABEL>
									<DIV class=ipt_box>
										<DIV id=password_bg class=bg_txt>
											<INPUT id=password class=new_txt tabIndex=7 maxLength=16
												type=password name=password>
										</DIV>
									</DIV>
									<DIV class="info pwd_info">
										<DIV id=pwd_tips class="pwd_tips hide">
											<DIV id=pwd_tip1 class=default>长度为6-16个字符</DIV>
											<DIV id=pwd_tip3 class=default>不能包含空格</DIV>
											<DIV id=pwd_tip2 class=default>不能是9位以下纯数字</DIV>
										</DIV>
										<TABLE id=pwd_result class=hide>
											<TBODY>
												<TR>
													<TD id=password_pic>弱</TD>
												</TR>
												<TR>
													<TD id=password_info class=password_info></TD>
												</TR>
											</TBODY>
										</TABLE>
									</DIV>
								</DIV>
								<DIV class="box box_5">
									<LABEL class=item for=password_again>确认密码</LABEL>
									<DIV class=ipt_box>
										<DIV id=password_again_bg class=bg_txt>
											<INPUT id=password_again class=new_txt tabIndex=8
												maxLength=16 type=password name=pass_again>
										</DIV>
									</DIV>
									<DIV class=info>
										<TABLE>
											<TBODY>
												<TR>
													<TD id=password_again_info class=hidden></TD>
												</TR>
											</TBODY>
										</TABLE>
									</DIV>
								</DIV>
								<DIV class="box box_6">
									<DIV class=item>性别</DIV>
									<DIV class="ipt_box nobg sex_box">
										<input type="hidden" name="sexes" id="sexes" value=""><A
											id=sex_1 class=unchecked tabIndex=9 name=male
											onClick="javascript:selectMale()">男</A><A id=sex_2
											class=unchecked tabIndex=10 name=femail
											onClick="javascript:selectFemale()">女</A>
									</DIV>
									<DIV class=info>
										<TABLE>
											<TBODY>
												<TR>
													<TD id=sex_info class=hidden></TD>
												</TR>
											</TBODY>
										</TABLE>
									</DIV>
								</DIV>

								<DIV class=info>
									<TABLE>
										<TBODY>
											<TR>
												<TD id=birthday_info class=hidden></TD>
											</TR>
										</TBODY>
									</TABLE>
								</DIV>
								<DIV class="box box_8">
									<DIV class=item>所在地</DIV>
									<DIV class="ipt_box nopadding nobg">
										<DIV id=country_box>
											<DIV id=country_value_bg class=area_value_bg>
												<INPUT id="country_value" name="province"
													class="country_value" tabIndex=15 maxLength=20 type="text"
													autocomplete="off">
											</DIV>
											<UL id=country_ul class=hide></UL>
										</DIV>
										<DIV id=province_box>
											<DIV id=province_value_bg class=area_value_bg>
												<INPUT id=province_value name="city" class=province_value
													tabIndex=16 maxLength=20 type=text autocomplete="off">
											</DIV>
											<UL id=province_ul class=hide></UL>
										</DIV>
										<DIV id=city_box>
											<DIV id=city_value_bg class=area_value_bg>
												<INPUT id=city_value class=city_value name="area"
													tabIndex=17 maxLength=20 type=text autocomplete="off">
											</DIV>
											<UL id=city_ul class=hide></UL>
										</DIV>
									</DIV>
									<DIV class=info>
										<TABLE>
											<TBODY>
												<TR>
													<TD id=area_info class=hidden></TD>
												</TR>
											</TBODY>
										</TABLE>
									</DIV>
								</DIV>
								<DIV id=code_box class="box box_9">
									<LABEL class=item for=code>验证码</LABEL>
									<DIV class="ipt_box code_bg">
										<INPUT id=code class=code_ipt tabIndex=18 maxLength=8
											type=text name=code autocomplete="off"
											onFocus="javascript:createCode()">
										<div id="code_img"
											style="FLOAT: left; MARGIN-LEFT: 5px; background-image: url(textback.jpg); font-size: 36px; text-align: center; height: 37px; width: 130px; padding-top: 15px"></div>
										<!-- <IMG 
style="FLOAT: left; MARGIN-LEFT: 5px; CURSOR: pointer" id=code_img  src="textback.jpg" 
width=130 height=53>-->
									</DIV>
									<DIV class=info>
										<TABLE>
											<TBODY>
												<TR>
													<TD id=code_info><SPAN id=code_info_err class=hidden>验证码错误</SPAN></TD>
												</TR>
												<TR>
													<TD></TD>
												</TR>
											</TBODY>
										</TABLE>
									</DIV>
								</DIV>
								<DIV id=qzone_box class="box box_10">
									<DIV class=item></DIV>
								</DIV>
								<DIV class="box box_11">
									<DIV class=item></DIV>
									<DIV class=ipt_box>
										<DIV class=submit>
											<INPUT title=立即注册 value=立即注册 type=submit>
										</DIV>
									</DIV>
									<DIV class=info></DIV>
								</DIV>
						</FORM>
					</DIV>
				</DIV>
				<DIV class=clear></DIV>
			</DIV>
		</DIV>
	</DIV>
	<div id="warning"
		style="z-index: 9999; position: fixed; top: 50%; left: 50%; width: 660px; height: 360px; margin: -180px 0 0 -330px; border-radius: 5px; border: solid 2px #666; background-color: #dff; display: none; box-shadow: 0 0 10px #666;">
		<ul>
			<li
				style="color: #0F2E3A; font-size: 48px; text-align: center; width: auto; margin-top: 10%;">用户名已经被注册啦，换一个</li>
			<li
				style="color: #0F2E3A; cursor: pointer; font-size: 48px; font-size: 36; text-align: center; width: auto; margin-top: 20%;"><a
				href="javascript:close();">关闭</a></li>
		</ul>
	</div>
	<SCRIPT type=text/javascript src="regist_files/simple.js"></SCRIPT>

	<SCRIPT type=text/javascript src="regist_files/rsa.js"></SCRIPT>

	<SCRIPT type=text/javascript src="regist_files/autocomplete.js"></SCRIPT>

	<SCRIPT type=text/javascript src="regist_files/index.js"></SCRIPT>

	<SCRIPT type=text/javascript>
var code;
var xmlhttp;
function startRequest(){
	if(!checkForm()){
		alert("请完善你的注册信息");
		return false;
	}
	if(window.ActiveXObject){
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}else{
		xmlhttp=new XMLHttpRequest();
	}
	var url="/WOC/RegistServlet?nick="+document.getElementById("nick").value+"&password="+document.getElementById("password").value
					+"&sexes="+document.getElementById("sexes").value+"&province="+document.getElementById("country_value").value+"&city="+document.getElementById("province_value").value
					+"&area="+document.getElementById("city_value").value+"&nickname="+document.getElementById("nickname").value;
	xmlhttp.onreadystatechange=stateChange;
	xmlhttp.open("POST", url, true);
	xmlhttp.send(null);
	return false;
}
function stateChange(){
	if(xmlhttp.readyState==4&&xmlhttp.status==200){
	if(xmlhttp.responseText=="usernameRep"){
		document.getElementById("warning").style.display="";
	}else{
	alert("注册成功，你即将登录我们的网站");
	location="/WOC";
	}
}
}
function VadfocusEve(){
	document.getElementById('vad').innerHTML='';
	document.getElementById('vad').style.display='none';
}
function createCode(){
	code="";
	var codeLen=4;
	var checkCode=document.getElementById("code_img");
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
		return false;
	}
	return true;
}
function checkForm(){
	if(document.getElementById("nickname").value==""||document.getElementById("nick").value==""||document.getElementById("password").value!=document.getElementById("password_again").value||document.getElementById("code").value!=code)
	{
		return false;
	}
	return true;
}
function selectMale(){
	document.getElementById("sexes").value="男";
}
function selectFemale(){
	document.getElementById("sexes").value="女";
}
function close(){
	document.getElementById("warning").style.display="none";
}
</SCRIPT>
</BODY>
</HTML>