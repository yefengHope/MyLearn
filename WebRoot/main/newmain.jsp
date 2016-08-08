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
		color:#ffffff;
		text-align: center;
	}
	</style>
	<script type="text/javascript">
	var menus = ${jsmenulist}
	function leftMenu(menu){
		for(var i=0;i<menus.length;i++){
			if(menus[i].muid==menu.muid){
				$('noka_menu').reload(menus[i].sub);
				return;
			}
		}
		
		$('noka_menu').reload(menu);
	}
	</script>
  </head>
  <body>
  <div class="top_tools" >
  <div style="width:300px;float:left;"><div class="top_logo">BMS LOGO</div>
  	<!-- 一级菜单 -->
  	<n:toptool id="topmenu_left" direction="left" onclick="leftMenu">
  		${JSMenuTopList}
	</n:toptool>
  </div>
  <div style="width: 50%;float:right;">
  	<!-- 用户名 -->
  	<n:toptool id="topmenu_rigth" direction="right">
  		[{name:'${user.usxname}',sub:[{name:'退出',url:'${rooturl}'}]}]
	</n:toptool>
  </div>
  </div>
  	<!-- 二级和三级菜单 -->
    <n:leftmenu cent="noka_menu_cent_body" id="noka_menu" open="1" >
    ${JSMenuLeftList}
    </n:leftmenu>
	<iframe id="noka_menu_cent_body" name="main_body" style="left:50px;float:left;top:50px;right:0px;bottom:0px;position: absolute;border: 0"  src=""></iframe>
  </body>
</html>
