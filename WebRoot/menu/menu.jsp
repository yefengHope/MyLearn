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
	<n:script src="script/menu/menu.js"></n:script>
  </head>
<body>
<b:lan key="org.word.jsp.name_${muid}" value="nk_tree_right"/>
<div>
<div style="float: left;height:98%;">
	<n:tree id="menu" sql="${tsql}" spid="-1" updServerFunction="UPDATE NK_SYS_MENU set MENUPID =? ,MENULEVE=? WHERE MENUID =?" titleonclick="menu_titileclick"  formatcell="org.noka.function.LanguageRead.getLang(java.lang.String name) as name"/>
</div>
<div style="float: left;width:88%;">
<div style="height: 20%;">
<!--表单 start-->
<n:form action="" method="post" id="menuform" name="menuform" onsuccess="onsuccess" onfailure="onfailure">
<n:Hiddens  json="[{name:'menuItem.menuid',id:'menuid_id',value:'${menuItem.menuid}'},{name:'menuItem.menupid',id:'menupid_pid',value:'${menuItem.menupid}'},{name:'menuItem.menuleve',id:'menuleve_id',value:'${menuItem.menuleve}'},{name:'trepwid_id',id:'trepwid_id',value:'${menuItem.menupid}'}]" id="hidden_id"></n:Hiddens>
<n:frow>
	<n:fcell label="${org.menu.jsp.menu_name}：" labelwidth="60">
		<n:InputText width="200"  disabled="${b:ButIf('add||update','no:disabled')}"  name="menuItem.menuname" id="menuname_id" allownull="false" mesg="${org.noka.sys.nonull}" classstyle="text" ondblclick="DoubleClickClear(this);" value="${menuItem.menuname}"/>
	</n:fcell>
	<n:fcell label="${org.menu.jsp.menu_url}：" labelwidth="60">
		<n:InputText width="400" disabled="${b:ButIf('add||update','no:disabled')}"  name="menuItem.menuurl" id="menuurl_id"     mesg="${org.word.jsp.intnonull}" classstyle="text" ondblclick="DoubleClickClear(this);" value="${menuItem.menuurl}"/>
	</n:fcell>
</n:frow>
<n:frow>
    <n:fcell label="${org.menu.jsp.menu_operate}：" labelwidth="60">
     <n:InputText width="678" name="menuItem.menuoperate" id="menuoperate_id"  disabled="${b:ButIf('add||update','no:disabled')}"  classstyle="text" ondblclick="DoubleClickClear(this);" value="${menuItem.menuoperate}"></n:InputText>
    </n:fcell>
</n:frow>
<n:frow>
    <n:fcell label="${org.menu.jsp.menu_image}：" labelwidth="60">
     <n:InputText width="200" name="menuItem.menuimage" id="menuimage_id"  disabled="${b:ButIf('add||update','no:disabled')}"  classstyle="text" ondblclick="DoubleClickClear(this);" value="${menuItem.menuoperate}"></n:InputText>
    </n:fcell>
     <n:fcell label="${org.menu.jsp.menu_target}：" labelwidth="60">
     <n:InputText width="200" name="menuItem.menutarget" id="menutarget_id"  disabled="${b:ButIf('add||update','no:disabled')}"  classstyle="text" ondblclick="DoubleClickClear(this);" value="${menuItem.menuoperate}"></n:InputText>
    </n:fcell>
    <n:fcell label="${org.menu.jsp.menu_taxis}：" labelwidth="60">
     <n:InputText width="134" name="menuItem.menutaxis" allownull="false" id="menutaxis_id"  disabled="${b:ButIf('add||update','no:disabled')}"  classstyle="text" ondblclick="DoubleClickClear(this);" value="${menuItem.menuoperate}"></n:InputText>
    </n:fcell>
</n:frow>
<n:frow>
	<n:fcell label="${org.menu.jsp.menu_nburl}：" labelwidth="60">
     <n:InputText width="678" name="menuItem.menunburl" id="menunburl_id"  disabled="${b:ButIf('add||update','no:disabled')}"  classstyle="text" ondblclick="DoubleClickClear(this);" value="${menuItem.menuoperate}"></n:InputText>
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
	inputData="[{field:'menuid_id',value:2},{field:'menupid_pid',value:3},{field:'menuleve_id',value:4},{field:'menuname_id',value:5},{field:'menuurl_id',value:6},{field:'menuoperate_id',value:7},{field:'menuimage_id',value:8},{field:'menutaxis_id',value:9},{field:'menutarget_id',value:10},{field:'menunburl_id',value:11}]"
	width="1000" 
	height="500"
	id="menudb"
	checkrecord="deptids"
	onfailure="onfailure"
	number="true"
	onsuccess="onsuccess"
	sql="${sql}"
	pagesize="40"
	selectfiled="LEVE like '$[ta,tna,hidden]%' | and ${org.menu.jsp.menu_name} like '%$[d_name,${org.menu.jsp.menu_name}]%' | and ${org.menu.jsp.menu_url} like '%$[d_c,${org.menu.jsp.menu_url}]%'"
	selectinput="true" 
	title="${org.menu.jsp.menu_list}${b:ButIf('add||update',org.noka.sys.dbcortitle)}"
	tableformname="dataform"
	tableformid="dataform"
    checkname="menuids"
	onrowdblclick="detail"
	cells="width:80,show:4|width:80,show:4|width:200,show:4|width:180,show:1|width:100,show:1|width:100,show:1|width:100,show:1|width:100,show:1|width:100,show:1|width:100,show:1"
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
