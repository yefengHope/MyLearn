<%@ page language="java"  pageEncoding="utf-8"%>
<html>
  <head>
    <title></title>
	<%@ include file="/inc/inc.jsp"%>
  </head>
<body>
<script type="text/javascript">
function onsuccess(a){
		switch(a.responseText.trim()){
		case '1':
			$('msg_id').innerHTML=lang.L5;
			break;
		case '2':
			$('msg_id').innerHTML=lang.L8;
			break;
		}
	}
function submitForm(){
	$('buttonform').ajaxSubmit();
}
</script>
<table width="90%" height="100%">
<tr><td align="left"  valign="top">
<n:form id="buttonform" action="${rooturl}/buttondom.nk" method="post" name="buttonform" onsuccess="onsuccess">
<input type="hidden" name="muid" id="muid" value="${muid}" />
<b:button id="buttondom"  type="button" name="buttondom"  classstyle="but"  value="${org.popedom.jsp.buttondom}" onclick="submitForm()"/>
<font color="red" id="msg_id">${msg}</font><br>
<input type="hidden" name="menuid" value="${menuItem.menuid}" /><br>
<input type="hidden" name="roleid" value="${roleid}" /><br>
${buttonlist}
</n:form>
</td>
</tr>
</table>
</body>
</html>
