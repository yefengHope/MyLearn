<%@ page language="java"  pageEncoding="utf-8"%>
<html>
  <head>
    <title></title>
	<%@ include file="/inc/inc.jsp"%>
	<n:script src="script/popedom/user.js"></n:script>
	<n:link href="skins/css/body.css" rel="stylesheet"></n:link>
  </head>
<body>
<script type="text/javascript">
lang.U1='${org.user.jsp.stopuser}';
lang.U2='${org.user.jsp.suer}';
lang.U3='${org.user.jsp.dbpasserro}';
lang.U4='${org.user.jsp.squro}';
</script>
<div style="height: 40%">
<!-- 表单 start-->
<n:form action="" method="post" id="wordbookform" name="wordbookform" onfailure="onfailure" onsuccess="onsuccess" labeldefaultwidth="60px" inputdefaultwidth="180px">
<n:Hiddens json="[{name:'user.usid',id:'usid_id'},{name:'idname',id:'idname_id',value:'1'},{name:'muid',id:'muid_id',value:'${muid}'},{name:'idname',id:'idname_id'}]" id="hidden_ids"></n:Hiddens>
<n:frow>
<n:fcell label="${org.user.jsp.empployeeno}：">
<n:InputText  disabled="${b:ButIf('add||update','no:disabled')}"  name="user.usname" id="usname_id" maxlength="20" allownull="false"  mesg="${org.noka.sys.nonullorin}"  ondblclick="DoubleClickClear(this);"/>
</n:fcell>
<n:fcell label="${org.myuser.jsp.name}：">
<n:InputText  width="140" disabled="${b:ButIf('add||update','no:disabled')}"  name="user.usxname" id="usxname_id" maxlength="20" allownull="false" mesg="${org.noka.sys.nonull}" classstyle="text"  ondblclick="DoubleClickClear(this);"/>
</n:fcell>
<n:fcell label="${org.user.jsp.pass}：">
<n:InputPass showpass="true" width="138" disabled="${b:ButIf('add||update','no:disabled')}" name="user.uspassword" id="uspassword_id" mesg="${org.user.jsp.dbpasserro}" allownull="false" classstyle="text" ondblclick="DoubleClickClear(this);"></n:InputPass>
</n:fcell>
<n:fcell label="${org.user.jsp.dpass}：">
<n:InputPass showpass="true" width="153" disabled="${b:ButIf('add||update','no:disabled')}" name="uspassword2" id="uspassword_id2" mesg="${org.user.jsp.dbpasserro}" allownull="false" classstyle="text" ondblclick="DoubleClickClear(this);"></n:InputPass>
</n:fcell>
</n:frow>
<n:frow>
    <n:fcell label="${org.myuser.jsp.sex}：">
    <n:Radios width="120" disabled="${b:ButIf('add||update','no:disabled')}" name="user.ussex" id="ussex_id" value="1" json="[{value:'1',label:'${org.myuser.jsp.man}'},{value:'2',label:'${org.myuser.jsp.wman}'}]"></n:Radios>
    </n:fcell>
    <n:fcell label="${org.myuser.jsp.bro}：">
   <n:date width="122" allownull="false" readonly="${b:ButIf('add||update','no:readonly')}" id="usborth_id" name="user.usborth" classstyle="text" isInitNowDate="yes" size="20"/>
    </n:fcell>
    <n:fcell label="${org.myuser.jsp.moble}：">
    <n:InputText width="138" disabled="${b:ButIf('add||update','no:disabled')}" id="usmoble_id" name="user.usmoble" classstyle="text"  mesg="${org.myuser.jsp.pmoble}" value="${useritem.usmoble}"/>
    </n:fcell>
    <n:fcell label="${org.myuser.jsp.tel}：">
    <n:InputText  width="153" disabled="${b:ButIf('add||update','no:disabled')}" id="ustel_id" name="user.ustel" classstyle="text"  mesg="${org.myuser.jsp.ptel}" value="${useritem.ustel}"/>
  	</n:fcell>
  </n:frow>
 <n:frow>
    <n:fcell label="${org.myuser.jsp.email}：">
    <n:InputText  disabled="${b:ButIf('add||update','no:disabled')}" id="usemail_id" name="user.usemail" classstyle="text"  value="${useritem.usemail}"/>
    </n:fcell>
    <n:fcell label="${org.user.jsp.isstr}：">
    <n:Radios name="user.uspass" width="140" disabled="${b:ButIf('add||update','no:disabled')}" id="isstart" value="1" json="[{value:'1',label:'${org.noka.sys.yes}'},{value:'0',label:'${org.noka.sys.no}'}]"></n:Radios>
    </n:fcell>
    <n:fcell label="${org.user.jsp.workn}：">
    <n:treeselect  id="uswork_id" spid="-1" readonly="${b:ButIf('add||update','no:readonly')}"  name="user.uswork"  sql="${deptsql}" allownull="false"/>
	</n:fcell>
	<n:fcell label="${org.user.jsp.usfugle}：">
    <n:selecbutton width="135" allownull="false" id="usfugle_id" bwidth="700" bheight="450" name="user.usfugle" readonly="${b:ButIf('add||update','no:readonly')}">${rooturl}/userselect.nk?sid=0&s=usfugle_s&v=usfugle_id</n:selecbutton>
	</n:fcell>
 </n:frow>
   <n:frow>
   		<n:fcell label="${org.user.jsp.orgn}：">
	    <n:treeselect  id="usgroup_id" spid="-1"  readonly="${b:ButIf('add||update','no:readonly')}"  name="user.usgroup"  sql="${gropusql}" allownull="false"/>
		</n:fcell>
 	</n:frow>
   <n:frow>
    <n:fcell label="${org.word.jsp.notes}：">
    <n:Textarea width="906" name="user.ustext" id="ustext_id" disabled="${b:ButIf('add||update','no:disabled')}"></n:Textarea>
    </n:fcell>
  </n:frow>
  <n:frow>
  <n:fcell style="float: left;padding-left:200px;text-align: center;width:400px;">
  <b:button id="add"  name="add" classstyle="but" onclick="adds()" value="${org.noka.sys.add}"/>
  <b:button id="up"  name="update" style="display: none;" classstyle="but" onclick="updates()" value="${org.noka.sys.update}"/>
  </n:fcell>
</n:frow>
<n:forminfo/>
</n:form>
</div>
<div style="width:98%;height: 60%;">
<!-- 表单 end -->
<n:DBGrid 
	inputData="[{field:'usid_id',value:2},{field:'usname_id',value:3,veri:true},{field:'usxname_id',value:4,veri:true},{field:'ussex_id',value:16},{field:'usborth_id',value:11},{field:'usmoble_id',value:6},{field:'ustel_id',value:7},{field:'usemail_id',value:8},{field:'isstart',value:13},{field:'ustext_id',value:12},{field:'uspassword_id',value:14,veri:true},{field:'uspassword_id2',value:14,veri:true},{field:'idname_id',value:14},{field:'uswork_id',value:15},{field:'usfugle_id',value:17,show:18},{field:'usgroup_id',value:'USGROUP'}]"
	width="1424" 
	height="590"
	id="wordDb"
	onfailure="onfailure"
	onsuccess="onsuccess"
    title="${org.user.jsp.uinfo}${b:ButIf('add||update',org.noka.sys.dbcortitle)}"
    tableformname="dataform"
    tableformid="dataform"
    checkname="userids"
    checkrecord="userrows"
    number="true"
	sql="${sql}"
	pagesize="40"
	cells="width:0,show:4|width:120,show:1|width:180,show:1|width:60,show:1|width:120,show:1|width:120,show:1|width:120,show:1|width:200,show:1
	|width:200,show:4|width:200,show:4|width:200,show:4|width:200,show:4|width:200,show:4|width:200,show:4|width:200,show:4|width:200,show:4|width:200,show:4|width:200,show:4
	|width:200,show:1"
	selectfiled="${org.user.jsp.usern} like '%$[d_name,${org.user.jsp.usern}]%' | and ${org.myuser.jsp.name} like '%$[d_c,${org.myuser.jsp.name}]%' | and USTEXT like '%$[d_text,${org.word.jsp.notes}]%'"
	selectinput="true" 	
	checkd="checkbox"
	styleid="1"
	onrowdblclick="detail"
	tableformAction="${rooturl}/userdel.nk"
	hideselect="${b:ButIf('delete||popedom||start||stop','false:true')}"
	formatcell=" org.noka.function.Function.findDept(java.lang.String 组织机构) as 组织机构  | org.noka.function.Function.KeyToValue(java.lang.String ${org.myuser.jsp.sex}) as ${org.myuser.jsp.sex} | org.noka.function.Function.getfugle(java.lang.String USFUGLEN) as USFUGLEN"
	custombutton="${b:but('delete',org.noka.sys.dell,'dellform()')}${b:but('start',org.user.jsp.str,'duserstart()')}${b:but('stop',org.user.jsp.sto,'userstop()')}${b:but('popedom',org.user.jsp.fprole,'popedoms()')}"
	outpdfall="true"
	outtitle="用户信息"
/>
</div>
<!-- ============================================= -->
<n:NokaBox id="noka_box" width="580" height="300"></n:NokaBox>
<!-- 授权end -->
</body>
</html>