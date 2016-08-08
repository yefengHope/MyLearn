<%@ page language="java"  pageEncoding="UTF-8"%>
<html>
  <head>
    <title></title>
	<%@ include file="/inc/inc.jsp"%>
	<n:script src="script/user/mypassword.js"></n:script>
  </head>
<body>
<!-- 表单 start-->
<n:form action="${rooturl}/mypasswordupdate.nk" method="post" id="wordbookform" name="wordbookform" subfunction="nk_submitform" onsuccess="onsuccess" onfailure="onfailure">
<table border="0" cellpadding="1" cellspacing="1" class="input_table">
  <tr>
    <td><div class="content">${org.login.jsp.username}：</div></td>
    <td>
    <n:InputText name="user.usname" id="usname_id" classstyle="text" readonly="true" value="${user.usname}"  mesg="${org.user.jsp.usernameerro}"></n:InputText>
    </td>
  </tr>
  <tr>
    <td><div class="content">${org.role.jsp.oldpass}：</div></td>
    <td><n:InputPass name="oldpassword" id="oldpassword_id" classstyle="text"  allownull="false" mesg="${org.role.jsp.oldpasserro}" ></n:InputPass></td>
  </tr>
    <tr>
    <td><div class="content">${org.role.jsp.newpass}：</div></td>
    <td><n:InputPass name="newpassword" id="newpassword_id" classstyle="text"  allownull="false"  mesg="${org.role.jsp.npasserro}"></n:InputPass></td>
  </tr>
    <tr>
    <td><div class="content">${org.role.jsp.rnespass}：</div></td>
    <td><n:InputPass name="user.uspassword" id="uspassword_id" classstyle="text"  allownull="false"  mesg="${org.role.jsp.rnpasserro}"></n:InputPass></td>
  </tr>
  
  <tr>
    <td colspan="2" align="center"><input type="button" name="up" value="${org.noka.sys.save}" class="but" onclick="updates('${org.role.jsp.onpasserro}');"/> 
    <font id="msg_util" color="red">${msg}</font>
    </td>
  </tr>
</table>
</n:form>
<!-- 表单 end -->

</body>
</html>
