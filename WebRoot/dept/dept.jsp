<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="/inc/inc.jsp"%>
<html>
  <head>
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<n:script src="script/dept/dept.js"></n:script>
  </head>
<body>
<b:lan key="org.word.jsp.name_${muid}" value="nk_tree_right"/>
<div>
<div style="float: left;height:98%;">
	<n:tree id="dept" sql="${tsql}" spid="-1" titleonclick="dept_titileclick" updServerFunction="UPDATE NK_SYS_DEPT set DPID =? ,DLVIE=? WHERE DID =?" />
</div>
<div style="float: left;width:88%;">
<div style="height: 20%;">
<!--表单 start-->
<n:form action="" method="post" id="wordbookform" name="wordbookform" onsuccess="onsuccess" onfailure="onfailure">
<n:Hiddens  json="[{name:'deptItem.did',id:'wid_id',value:'${dept.did}'},{name:'deptItem.dpid',id:'pwid_id',value:'${dept.dpid}'},{name:'deptItem.dlvie',id:'plvie_id',value:'${dept.dlvie}'},{name:'deptItem.duser',id:'tuser_id',value:'${user.usid}'},{name:'deptItem.ddept',id:'tdept_id',value:'${user.uswork}'},{name:'trepwid_id',id:'trepwid_id',value:'${dept.dpid}'}]" id="hidden_id"></n:Hiddens>
<n:frow>
	<n:fcell label="${nk_tree_right}：">
		<n:InputText width="174"  disabled="${b:ButIf('add||update','no:disabled')}"  name="deptItem.dname" id="wname_id" maxlength="20" allownull="false" mesg="${org.noka.sys.nonull}" classstyle="text" ondblclick="DoubleClickClear(this);" value="${treebook.tname}"/>
	</n:fcell>
	<n:fcell label="${org.word.jsp.pxun}：">
		<n:InputText disabled="${b:ButIf('add||update','no:disabled')}"  name="deptItem.dpx" id="wcompositor_id" allownull="false" maxlength="20"   mesg="${org.word.jsp.intnonull}" classstyle="text"  veri="[1-9]{1}[0-9]{0,9}" chtype="regex" ondblclick="DoubleClickClear(this);" value="${treebook.tpx}"/>
	</n:fcell>
</n:frow>
<n:frow>
    <n:fcell label="${org.word.jsp.notes}：" labelwidth="60px">
     <n:Textarea name="deptItem.dtext" id="wtext_id"  disabled="${b:ButIf('add||update','no:disabled')}"  cols="50" rows="5" classstyle="text"></n:Textarea>
    </n:fcell>
</n:frow>
<div style="clear: both;"></div>
<n:frow style="float: left;padding-left:200px;vertical-align: middle;text-align: center;width:400;">
  <b:button id="add_id" name="add" classstyle="but" onclick="adds()" value="${org.noka.sys.add}"/>
  <b:button id="update_id"  style="display: none;" name="update"  classstyle="but" onclick="updates()" value="${org.noka.sys.update}"/>
</n:frow>
<n:forminfo/>
</n:form>
<!-- 表单 end -->
</div>
<div style="height: 70%">
<!-- 数据列表  start -->
<n:DBGrid
	inputData="[{field:'wid_id',value:2},{field:'pwid_id',value:3},{field:'pwid_id',value:3},{field:'wname_id',value:4},{field:'wcompositor_id',value:6},{field:'wtext_id',value:5},{field:'plvie_id',value:7}]"
	width="700" 
	height="400"
	id="wordDb"
	checkrecord="deptids"
	onfailure="onfailure"
	onsuccess="onsuccess"
	number="true"
	sql="${sql}"
	pagesize="40"
	selectfiled="LEVE like '$[ta,tna,hidden]%' | and ${nk_tree_right} like '%$[d_name,${nk_tree_right}]%' | and ${org.word.jsp.notes} like '%$[d_c,${org.word.jsp.notes}]%'"
	selectinput="true" 
	title="${org.word.jsp.wordlist}${b:ButIf('add||update',org.noka.sys.dbcortitle)}"
	tableformname="dataform"
	tableformid="dataform"
    checkname="wordids"
	onrowdblclick="detail"
	cells="width:80,show:4|width:80,show:4|width:200,show:1|width:180,show:1|width:100,show:1"
	checkd="checkbox"
	hideselect="${b:ButIf('delete','false:true')}"
	custombutton="${b:but('delete',org.noka.sys.dell,'dels()')}"
	styleid="1"
/>
</div>
</div>
</div>
</body>
</html>
