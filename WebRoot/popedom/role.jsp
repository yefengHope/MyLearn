<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<title></title>
		<%@ include file="/inc/inc.jsp"%>
		<n:script src="script/popedom/role.js"></n:script>
		<n:link href="skins/css/body.css" rel="stylesheet"></n:link>
	</head>
	<body>
<script type="text/javascript">
lang.R1='${org.role.jsp.prdo}';
lang.R2='${org.role.jsp.psrole}';
</script>
<n:NokaBox id="role_box" width="800" height="530"></n:NokaBox>
<!-- 表单 start-->
<div style="height: 20%">
<n:form action="" method="post" id="wordbookform" name="wordbookform" onsuccess="onsuccess" onfailure="onfailure">
<n:Hiddens  json="[{name:'role.roleid',id:'wid_id'},{name:'muid',id:'muid',value:'${muid}'}]" id="hidden_id"></n:Hiddens>
<n:frow>
<n:fcell label="${org.role.jsp.rolename}：">
	<n:InputText width="174" disabled="${b:ButIf('add||update','no:disabled')}"  name="role.rolename" id="wname_id" allownull="false" mesg="该项不能为空" classstyle="text" maxlength="20" ondblclick="DoubleClickClear(this);" />
</n:fcell>
</n:frow>
<n:fcell label="${org.word.jsp.pxun}：">
	<n:InputText  disabled="${b:ButIf('add||update','no:disabled')}" name="role.rolecompositor" id="wcompositor_id" maxlength="20" allownull="false" mesg="${org.word.jsp.intnonull}" classstyle="text"  veri="[1-9]{1}[0-9]{0,9}" chtype="regex" ondblclick="DoubleClickClear(this);" />
</n:fcell>
<n:frow>
<n:fcell label="${org.word.jsp.notes}：" labelwidth="60px">
	<n:Textarea  disabled="${b:ButIf('add||update','no:disabled')}" classstyle="text" name="role.roletext" id="wtext_id" ></n:Textarea>
</n:fcell>
</n:frow>
<n:frow>
<n:fcell style="float: left;padding-left:200px;vertical-align: middle;text-align: center;width:400;">
	<b:button id="add"  name="add"  classstyle="but" onclick="adds()" value="${org.noka.sys.add}"/>
	<b:button id="up" name="update"  style="display: none;" classstyle="but" onclick="updates()" value="${org.noka.sys.update}"/>
</n:fcell>
</n:frow>
<n:forminfo/>
</n:form>
</div>
<div style="height: 70%;width:98%">
<n:DBGrid 
inputData="[{field:'wid_id',value:2},{field:'wname_id',value:3},{field:'wcompositor_id',value:5},{field:'wtext_id',value:4}]"
 formConfirmation="${org.noka.sys.isdel}"
    formNull="${org.noka.sys.dellselect}"
	width="700"
	height="300"
	id="wordDb"
	onfailure="onfailure"
	onsuccess="onsuccess"
	number="true"
	checkrecord="roeidrows"
	checkd="checkbox"
	sql="${sql}"
	pagesize="10"
	selectfiled="${org.role.jsp.rolename} like '%$[d_name,${org.role.jsp.rolename}]%' | and ${org.word.jsp.notes} like '%$[d_c,${org.word.jsp.notes}]%'"
	selectinput="true" 
	title="${org.role.jsp.rolelist}${b:ButIf('add||update',org.noka.sys.dbcortitle)}"
	tableformname="dataform"
	tableformid="dataform"
	tableformAction="${rooturl}/roledel.nk"
	checkname="roleids"
	onrowdblclick="autodata"
	cells="width:10,show:4|width:200,show:1|width:200,show:1"
	custombutton="${b:but('delete',org.noka.sys.dell,'wordDb_ajaxSubmitDataForm()')}${b:but('popedom',org.role.jsp.sqro,'popedoms()')}${b:but('dlfiltratebutton',org.role.jsp.datafi,'dlfiltrate()')}"
   />
   </div>
</body>
</html>
