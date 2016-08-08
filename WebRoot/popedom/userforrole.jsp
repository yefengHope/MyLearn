<%@ page language="java"  pageEncoding="utf-8"%>
<html>
  <head>
    <title></title>
	<%@ include file="/inc/inc.jsp"%>
  </head>
<body>
<script type="text/javascript">
<n:jszip id="uforjs_id">
function formsbumitprvae(roid){
	var ops = $("roleidsspx").options;
	if (ops.length > 0) {
		var str = "";
		for (var i = 0; i < ops.length; i++) {
			str += ("," + ops[i].value);
		}
	}
	userroledojs(str.substring(1),roid);//调用服务器方法
}
function rundata(d){
	var i=d.responseText;
	if(i=="1")
		$('inm_id').innerHTML="[${org.user.jsp.fpsu}]";
	else
	    $('inm_id').innerHTML="[${org.user.jsp.fpfd}]";
}
</n:jszip>
</script>
<n:SerFun refunction="rundata" id="userroledo" webfunction="userroledojs(roleids,usid)" serverfunction="org.noka.pro.SysPro.userproledo(java.lang.String roleids,java.lang.String usid)"></n:SerFun>
<table width="100%" height="100%">
<tr>
<td align="center"  valign="top">
<div>
${org.user.jsp.fpdoing}[${user.usname}]${org.user.jsp.userfp}<font id="inm_id" color="red"></font>
<table width="100%" border="0" align="center" cellpadding="0"cellspacing="0" class="border3 margin3">
<tr><td></td>
</tr>
</table>
</div>
<div>
<n:selectDouble 
id="scsss"
rightselectname="rsename"
rightSql="${rsql}"
con="${con}" 
lietselectid="allrole"
buttionClass="but" 
lietshowname="${org.user.jsp.allrol}" 
reightshowname="${org.user.jsp.sdrol}"
lietSql="${sql}" 
isfind="v" 
rightselectid="roleidsspx" />
</div>
<div style="padding-left:250px;">
<input type="button" name="save" value="${org.noka.sys.save}" class="but" onclick="formsbumitprvae('${user.usid}');" />&nbsp;
<input type="button" name="save" value="${org.noka.sys.close}" class="but" onclick="parent.NokaBox.box.hide()"/><br>
</div>
</td>
</tr>
</table>

</body>
</html>
