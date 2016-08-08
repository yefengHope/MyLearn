<%@ page language="java"  pageEncoding="utf-8"%>
<html>
  <head>
    <title></title>
	<%@ include file="/inc/inc.jsp"%>
	<n:script src="script/treebook/treebook.js"></n:script>
  </head>
<body>
<b:lan key="org.word.jsp.name_${muid}" value="nk_tree_right"/>
<div>
<div style="float: left;height:98%;">
	<n:tree id="treewod" sql="${tsql}" spid="-1" updServerFunction="UPDATE NK_SYS_TREEBOOK set TPID =? ,TLVIE=? WHERE TID =?" titleonclick="tree_titileclick"  />
</div>
<div style="float: left;width:88%;">
<div style="height: 20%;">
<!--表单 start-->
<n:form action="" method="post" id="wordbookform" name="wordbookform" onsuccess="onsuccess" onfailure="onfailure">
<n:Hiddens  json="[
{name:'treeBook.ttype',value:'${muid}'},
{name:'treeBook.tid',id:'wid_id',value:'${treebook.tid}'},
{name:'treeBook.tpid',id:'pwid_id',value:'${treebook.tpid}'},
{name:'treeBook.tlvie',id:'plvie_id',value:'${treebook.tlvie}'},
{name:'treeBook.tuser',id:'tuser_id',value:'${user.usid}'},
{name:'treeBook.tdept',id:'tdept_id',value:'${user.uswork}'},
{name:'trepwid_id',id:'trepwid_id',value:'${treebook.tpid}'}
]" id="hidden_id"></n:Hiddens>
<n:frow>
	<n:fcell label="${nk_tree_right}：">
		<n:InputText width="174" disabled="${b:ButIf('add||update','no:disabled')}"  name="treeBook.tname" id="wname_id" maxlength="20" allownull="false" mesg="${org.noka.sys.nonull}" classstyle="text" ondblclick="DoubleClickClear(this);" value="${treebook.tname}"/>
	</n:fcell>
	<n:fcell label="${org.word.jsp.pxun}：">
    	<n:InputText disabled="${b:ButIf('add||update','no:disabled')}"  name="treeBook.tpx" id="wcompositor_id" allownull="false" maxlength="20"   mesg="${org.word.jsp.intnonull}" classstyle="text"  veri="[1-9]{1}[0-9]{0,9}" chtype="regex" ondblclick="DoubleClickClear(this);" value="${treebook.tpx}"/>
  </n:fcell>
</n:frow>
   <n:frow>
    <n:fcell label="${org.word.jsp.notes}：">
     <n:Textarea name="treeBook.ttext" id="wtext_id"  disabled="${b:ButIf('add||update','no:disabled')}"  cols="50" rows="5" classstyle="text"></n:Textarea>
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
height="300"
id="wordDb"
checkrecord="treeids"
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
	autosize="true"
/>
</div>
</div>
</div>
</body>
</html>
