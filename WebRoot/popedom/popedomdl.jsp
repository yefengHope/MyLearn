<%@ page language="java"  pageEncoding="utf-8"%>
<html>
  <head>
    <title></title>
	<%@ include file="/inc/inc.jsp"%>
	<n:script src="script/popedom/popedomdl.js"></n:script>
  </head>
<body>
<table width="100%">
<tr>
<td align="center">
<n:form action="${rooturl}/dfitartedo.nk" method="post" subfunction="popedomdl_submid" onsuccess="onsuccess" onfailure="onfailure" name="popedomdl_from" id="popedomdl_from_id">
<input  type="hidden" name="roleid" value="${roleItem.roleid}" />
<input  type="hidden" name="muid" id="muid_id" value="${muid}" />
<table background="1" width="100%">
<tr>
<td colspan="2">
${org.user.jsp.fpdoing}[<span style="color: #FF0000">${roleItem.rolename}</span>]${org.role.jsp.roledatafi}<font id="msg_util" color="red">${msg}</font>
<table width="100%" border="0" align="center" cellpadding="0"cellspacing="0" class="border3 margin3">
<tr><td></td>
</tr>
</table>
</td>
</tr>
<tr>
<td>
<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#69C3EE"  >
<tr>
<td bgcolor="#A8D2EE">${org.role.jsp.tablename}</td>
<td bgcolor="#A8D2EE">${org.role.jsp.dodept}</td>
</tr>
<c:forEach items="${dbtableList}" var="dlfiltrate">
<tr>
<td   bgcolor="#FFFFFF">${dlfiltrate.lang}</td>
<td   bgcolor="#FFFFFF">
<n:Radios id="select_${dlfiltrate.key}_radios" json="${dlfiltrate.select}" value="${dlfiltrate.radiovalue}" onclick="type_onclick"></n:Radios>
<div id="select_${dlfiltrate.key}_div" style="display: ${dlfiltrate.treeshow};"><n:treeselect titlecliek="fase" checktype="nomuch" checkboxonclick="oncl" id="select_${dlfiltrate.key}_tree" width="100px" name="select_${dlfiltrate.key}" sql="${sql}" value="${dlfiltrate.treevalue}"/>
</div>
</td>
<td>
<input id="select_${dlfiltrate.key}_userfield" name="user_fieldn" type="hidden" value="${dlfiltrate.userfield}" /><!-- 用户字段 -->
<input id="select_${dlfiltrate.key}_deptfield" name="dept_fieldn" type="hidden" value="${dlfiltrate.deptfield}" /><!-- 部门字段 -->
<input id="select_${dlfiltrate.key}_key" name="dlroleids_kdy" type="hidden" value="${dlfiltrate.key}"/><!-- 表名 -->
<input id="select_${dlfiltrate.key}_value" name="dlroleids" type="hidden"" width="800" value="${dlfiltrate.kvalue}"/><!-- 提交值-->
</td>
</tr>
</c:forEach>
</table>
</td>
</tr>
<tr>
<td colspan="2" align="center">
<b:button id="dfitartedo"  type="button" name="dfitartedo"  onclick="popedomdl_submid()" classstyle="but"  value="${org.popedom.jsp.dfitartedo}"/>
<input type="button" name="su" value="${org.noka.sys.close}"  id="d" class="but" onclick="parent.NokaBox.box.hide()"/>
</td>
</tr>
</table>
</n:form>
</td>
</tr>
</table>
</body>
</html>