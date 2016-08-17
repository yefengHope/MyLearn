<%@ page language="java"  pageEncoding="utf-8"%>
<html>
  <head>
    <title></title>
	<%@ include file="/inc/inc.jsp"%>
	<n:script src="script/user/myinfo.js"></n:script>
	<n:link href="skins/css/body.css" rel="stylesheet"></n:link>
  </head>
<body>
<!-- 表单 start-->
<n:form action="${rootpath}/myuserupdate.nk" method="post" id="wordbookform" name="wordbookform" subfunction="nk_submitform" onsuccess="onsuccess" onfailure="onfailure">
<input  type="hidden" name="user.usid" value="${useritem.usid}" />
<input type="hidden" name="muid" id="muid" value="${muid}" />
<table border="0" cellpadding="1" cellspacing="1" class="input_table">
  <tr>
    <td align="right" height="32">${org.myuser.jsp.name}：</td>
    <td><table><tr><td><n:InputText name="user.usxname" id="usname_id" allownull="false" mesg="${org.myuser.jsp.namenonull}" classstyle="text" value="${useritem.usxname}" maxlength="20"></n:InputText></td></tr></table></td>
    <td align="right" height="32">${org.myuser.jsp.sex}：</td>
    <td>
    <n:Radios width="120" name="user.ussex" value="${useritem.ussex}" id="ussex_id" json="[{value:'1',label:'${org.myuser.jsp.man}'},{value:'2',label:'${org.myuser.jsp.wman}'}]"></n:Radios>
    </td>
    <td align="right" height="32">${org.myuser.jsp.bro}：</td>
    <td>&nbsp;<n:date width="110" id="usborth_id" name="user.usborth" classstyle="text"  isInitNowDate="yes" readonly="yes" value="${n:fd(useritem.usborth,'yyyy-MM-dd',true)}" /> </td>
  </tr>
  <tr>
    <td align="right" height="32">${org.myuser.jsp.moble}：</td>
    <td><n:InputText id="usmoble_id" name="user.usmoble" classstyle="text"  mesg="${org.myuser.jsp.pmoble}" value="${useritem.usmoble}"/></td>
    <td align="right" height="32">${org.myuser.jsp.tel}：</td>
    <td><n:InputText id="ustel_id" name="user.ustel" classstyle="text"  mesg="${org.myuser.jsp.ptelg}" value="${useritem.ustel}"/></td>
    <td align="right" height="32">${org.myuser.jsp.email}：</td>
    <td><n:InputText width="126" id="usemail_id" name="user.usemail" classstyle="text" mesg="${org.myuser.jsp.pemail}" value="${useritem.usemail}"/></td>
  </tr>
  <tr>
    <td valign="top">${org.word.jsp.notes}：</td>
    <td colspan="5"><n:Textarea  width="457" name="user.ustext" id="ustext_id"  classstyle="text" value="${useritem.ustext}"></n:Textarea></td>
  </tr>
  <tr><td colspan="6" style="text-align: center;padding-left: 150px;">
  <b:button id="update_id"  name="update"  classstyle="but" onclick="updates();"  value="${org.noka.sys.update}"/>
  <font style="color: red;" id="msg_util">${msg}</font>
  </td></tr>
</table>
</n:form>
<!-- 表单 end -->
</body>
</html>
