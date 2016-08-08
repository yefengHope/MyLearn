<%@ page language="java"  pageEncoding="utf-8"%>
<html>
  <head>
	<%@ include file="/inc/inc.jsp"%>
  </head>
<body>
<script type="text/javascript">
<n:jszip id="menu_id">
//---------保存菜单权限--------------------
function savepopedom(){
	popedom_submit();
}
function onsuccess(a){
	if('1'===a.responseText.trim()){
		$('msg_id').innerHTML='${org.noka.sys.upds}';
	}else{
		$('msg_id').innerHTML='${org.noka.sys.updf}';
	}
}
</n:jszip>
</script>
<table style="height: 20px;width: 100%">
<tr><td colspan="2"style="text-align: center;vertical-align: top;">${org.user.jsp.fpdoing}[<span style="color: #FF0000">${role.rolename}</span>]${org.role.jsp.roleqx}</td></tr>
<tr><td  style="width: 350px;vertical-align: top;text-align: left;">
<div style="height:30px;">
<!-- 菜单权限 start -->
<b:button id="popedomsave" name="popedomsave"  classstyle="but" onclick="savepopedom();" value="${org.popedom.jsp.popedomsave}"/>
<input type="button" name="save" value="${org.noka.sys.close}" class="but" onclick="parent.NokaBox.box.hide();" />
<span style="color:red" id="msg_id">${msg}</span>
</div>
<div>
<n:form id="popedom_from" action="${rooturl}/popedomsave.nk?muid=${muid}&roleid=${role.roleid}" subfunction="popedom_submit" onsuccess="onsuccess">
	<n:tree 
	id="popedom"
	checktype="all"
	level="10"
	checkname="menuids"
	sql="${sql}" 
	url="${rootpath}/popedomButton.nk" 
	target="buttonpopedom"
	value="${value}"
	pramevar="muid=${muid}&menuid=$[ID]&roleid=${role.roleid}"
	formatcell="org.noka.function.LanguageRead.getLang(java.lang.String name) as name|org.noka.function.LanguageRead.getLang(java.lang.String name) as title"
	/>
</n:form>
</div>
<!-- 菜单权限 end -->
</td>
<td style="vertical-align: top;">
<!-- 操作权限 start -->
<iframe id="buttonpopedom" style="border: 0;"  name="buttonpopedom" width="200px;" height="300px;" src="${rooturl}/popedomButton.nk?muid=${muid}">
</iframe>
<!-- 操作权限 end -->
</td>
</tr>
</table>
</body>
</html>
