<%@ page language="java"  pageEncoding="UTF-8"%>
<html>
  <head>
    <title></title>
	<%@ include file="/inc/inc.jsp"%>
	<n:script src="script/user/mypassword.js"></n:script>
	<n:link href="skins/css/body.css" rel="stylesheet"></n:link>
  </head>
<body>
<!-- 表单 start-->
<div style="width: 90%;height: 90%;line-height: 90%;vertical-align: middle;margin-left: auto;margin-right: auto;">
<n:form action="${rooturl}/mypasswordupdate.nk" method="post" id="wordbookform" name="wordbookform" inputdefaultwidth="300px" labeldefaultwidth="120px" subfunction="nk_submitform" onsuccess="onsuccess" onfailure="onfailure">
<n:frow>
	<n:fcell label="${org.login.jsp.username}："><n:InputText width="200px" name="user.usname" id="usname_id" classstyle="text" readonly="true" value="${user.usname}"  mesg="${org.user.jsp.usernameerro}"></n:InputText></n:fcell>
</n:frow>
<n:frow>
	<n:fcell label="${org.role.jsp.oldpass}："><n:InputPass width="200px" name="oldpassword" showpass="true" id="oldpassword_id" classstyle="text"  allownull="false" mesg="${org.role.jsp.oldpasserro}" ></n:InputPass></n:fcell>
</n:frow>
<n:frow>
	<n:fcell label="${org.role.jsp.newpass}："><n:InputPass width="200px" name="newpassword" showpass="true" id="newpassword_id" classstyle="text"  allownull="false"  mesg="${org.role.jsp.npasserro}"></n:InputPass></n:fcell>
</n:frow>
<n:frow>
	<n:fcell label="${org.role.jsp.rnespass}："><n:InputPass width="200px" name="user.uspassword" showpass="true" id="uspassword_id" classstyle="text"  allownull="false"  mesg="${org.role.jsp.rnpasserro}"></n:InputPass></n:fcell>
</n:frow>
<n:frow style="text-align: center;">
	<input type="button" name="up" value="${org.noka.sys.save}" class="but" onclick="updates('${org.role.jsp.onpasserro}');"/> 
	<font id="msg_util" color="red">${msg}</font>
</n:frow>
</n:form>
</div>
<!-- 表单 end -->

</body>
</html>
