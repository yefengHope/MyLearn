<%@ page language="java"  pageEncoding="utf-8"%>
<html>
  <head>
    <title></title>
	<%@ include file="/inc/inc.jsp"%>
	<link rel="shortcut icon" type="image/x-icon" href="${rooturl}/main/noka.ico">
	<style type="text/css">
	html,body{
		margin: 0px;
		height:100%;
	}
	.top_tools{
		width: 100%;
		height:50px;
		background-color:#0099cc;
		font-weight: 600;
	}
	.top_logo{
		width:100px;
		float:left;
		line-height:50px;
		font-weight: 600;
		padding-top:10px;
		color:#ffffff;
		text-align: center;
	}
	</style>
	<script type="text/javascript">
	var menus = ${jsmenulist}
	function leftMenu(menu){
		for(var i=0;i<menus.length;i++){
			if(menus[i].muid==menu.muid){
				try{
					setTimeout(function(){
						$('noka_menu').reload(menus[i].sub);
					}, 200);
				}catch(e){}
				return;
			}
		}
		$('noka_menu').reload(menu);
	}
	function updatepass(){
		$('uppass_id').submit();
	}
	</script>
  </head>
  <body>
  <div style="display: none;">
  <form action="${rooturl}/mypassword.nk" target="mainFrame" id="uppass_id"></form>
  <n:form id="seturlpost">
  <input type="hidden" name="nowurl" id="nowurl_id">
  </n:form>
  </div>
  <div class="top_tools" >
  <div style="width:300px;float:left;"><div class="top_logo"><img alt="" src="${rooturl}/main/noka.ico"></div>
  	<!-- 一级菜单 -->
  	<n:toptool id="topmenu_left" direction="left" onclick="leftMenu" keepstate="true">
  		${JSMenuTopList}
	</n:toptool>
  </div>
  <div style="width: 50%;float:right;">
  	<!-- 用户名 -->
  	<n:toptool id="topmenu_rigth" direction="right">
  		[{name:'${user.usxname}',sub:[{name:'修该密码',onclick:updatepass},{name:'退出系统',url:'${rooturl}'}]}]
	</n:toptool>
  </div>
  </div>
  	<!-- 二级和三级菜单 -->
    <n:leftmenu cent="noka_menu_cent_body"  id="noka_menu" open="1" keepstate="true">
    ${JSMenuLeftList}
    </n:leftmenu>
	<iframe id="noka_menu_cent_body" name="mainFrame" style="left:50px;float:left;top:50px;right:0px;bottom:0px;position: absolute;border: 0"  src="${nowurl}"></iframe>
  </body>
</html>
