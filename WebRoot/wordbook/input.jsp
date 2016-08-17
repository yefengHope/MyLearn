<%@ page language="java"  pageEncoding="UTF-8"%>
<html>
  <head>
    <title></title>
	<%@ include file="/inc/inc.jsp"%>
	<n:script src="script/wordbook/input.js"></n:script>
	<n:link href="skins/css/body.css" rel="stylesheet"></n:link>
  </head>
<body>
<b:lan key="org.word.jsp.name_${muid}" value="nk_word_input"/>
<!-- 表单 start-->
<div style="height:200px;">
<n:form id="" ></n:form>
<n:form action="" method="post" id="wordbookform" name="wordbookform" onsuccess="onsuccess" onfailure="onfailure">
<n:Hiddens json="[{name:'wordBook.wid',id:'wid_id'},{name:'wordBook.wtype',value:'${muid}'},{name:'wordBook.wcworkdept',value:'${user.uswork}'},{name:'wordBook.wuser',value:'${user.usid}'}]" id="hidden_ids"></n:Hiddens>
<n:frow>
	<n:fcell label="${nk_word_input}：">
		<n:InputText width="174" disabled="${b:ButIf('add||update','no:disabled')}"  name="wordBook.wname" id="wname_id" allownull="false" maxlength="20" mesg="${org.noka.sys.nonull}" classstyle="text" ondblclick="DoubleClickClear(this)"/>
	</n:fcell>
	<n:fcell label="${org.word.jsp.pxun}：">
		<n:InputText disabled="${b:ButIf('add||update','no:disabled')}"  name="wordBook.wcompositor" id="wcompositor_id" maxlength="20"   mesg="${org.word.jsp.intnonull}" classstyle="text"  allownull="false" chtype="regex" veri="[1-9]{1,3}" ondblclick="DoubleClickClear(this)"/>
	</n:fcell>
</n:frow>
<n:frow>
	<n:fcell label="${org.word.jsp.notes}："> 
		<n:Textarea  name="wordBook.wtext" id="wtext_id"  disabled="${b:ButIf('add||update','no:disabled')}"  cols="50" rows="5" classstyle="text"></n:Textarea>
	</n:fcell>
</n:frow>
<n:frow>
<n:fcell style="float: left;padding-left:200px;vertical-align: middle;text-align: center;width:800;">
  <b:button id="add_id" name="add" classstyle="but" onclick="addss()" value="${org.noka.sys.add}"/>&nbsp;&nbsp;
  <b:button id="update_id"  name="update" style="display: none;" classstyle="but"  onclick="updates()" value="${org.noka.sys.update}"/>
</n:fcell>
</n:frow>
<n:forminfo/>
</n:form>
</div>
<div style="top:180px;bottom:40px;width:95%; position: absolute;">
<n:DBGrid
    inputData="[{field:'wid_id',value:1},{field:'wname_id',value:2},{field:'wcompositor_id',value:4},{field:'wtext_id',value:3}]"
    formConfirmation="${org.noka.sys.isdel}"
    formNull="${org.noka.sys.dellselect}"
	width="700" 
	height="300"
	id="wordDb"
	checkrecord="wordrows"
	onfailure="onfailure"
	onsuccess="onsuccess"
	sql="${sql}"
	selectfiled="${nk_word_input} like %$[wdname,${nk_word_input}]%  | #[ano,and^${org.noka.sys.tand}!or^${org.noka.sys.tor}]# ${org.word.jsp.notes} like '%$[d_c,${org.word.jsp.notes}]%'"
	selectinput="true" 
	title="${org.word.jsp.wordlist}${b:ButIf('add||update',org.noka.sys.dbcortitle)}"
	outtitle="打印标题"
	tableformname="dataform"
    checkname="wordids"
    cells="width:100,show:4|width:200,show:1|width:200,show:1|width:60,show:1"
	checkd="checkbox"
	onrowdblclick="detail"
	checkvalue="0"
	pagesize="3"
	tableformid="id_dataform"
	tableformAction="wordBookdel.nk"
	compositor="WCDATE"
	descorasc="desc"
	outpdfall="true"
	outpdfnopage="true"
	outexcelall="true"
	hideselect="${b:ButIf('delete','false:true')}"
	custombutton="${b:but('delete',org.noka.sys.dell,'wordDb_ajaxSubmitDataForm()')}"
	outfilename="基础字典表$[yyyy-MM-dd]"
/>
</div>
</body>
</html>