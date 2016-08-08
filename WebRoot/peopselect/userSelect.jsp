<%@ page language="java"  pageEncoding="utf-8"%>
<html>
  <head>
    <title></title>
	<%@ include file="/inc/inc.jsp"%>
	<n:script src="script/peopselect/peopselect.js"></n:script>
  </head>
<body>
<script type="text/javascript">
function setValue(a,b,v){
	try{
	window.parent.$("${v}").setValue(b,a);
	}catch(e){}
	window.parent.$("${v}").hide();
}
</script>
<table style="width:100%">
<tr>
<td style="height: 30px;vertical-align: top;">
<table>
	<tr><td>
		<n:Radios name="rolid" id="folid"  value="1" onclick="reLeft" json="[{value:'1',label:'${org.userselect.jsp.depa}'},{value:'2',label:'${org.userselect.jsp.role}'}]"></n:Radios>
	</td></tr>
	<tr><td>
		<div id="tree_dept" style="display: block;height:260px;vertical-align: top;overflow:auto;">
			<n:tree id="dept_select" sql="${dept_sql}" spid="-1" titleonclick="tree_click"/>
		</div>
			<div id="tree_role" style="display: none;height:260px;vertical-align: top;overflow:auto;">
		<n:tree id="role_select" sql="${role_sql}" spid="-1" titleonclick="tree_click"/>
		</div>
	</td></tr>
</table>
</td>
<td style="vertical-align: top;">
<n:DBGrid 
	width="550"
	height="350"
	id="wordDb"
    title="${org.user.jsp.uinfo}"
    tableformname="dataform"
    checkname="userids"
	sql="${user_sql}"
	pagesize="10"
	selectfiled="USROLE=('$[role_c,${org.userselect.jsp.role},hidden]') | and uswork='$[dept_c,${org.userselect.jsp.depa},hidden]' | and ${org.myuser.jsp.name} like '%$[d_c,${org.myuser.jsp.name}]%' "
	selectinput="true" 	
	checkd="${checkbox}"
	styleid="1"
	onrowdblclick="dclickvalue"
	cells="width:10,show:4|width:150,show:1|width:130,show:1|width:130,show:4|width:130,show:4|width:130,show:4|width:130,show:1|width:130,show:1"
	formatcell="org.noka.function.Function.formdate(java.lang.String ${org.myuser.jsp.bro}) as ${org.myuser.jsp.bro} | org.noka.function.Function.KeyToValue(java.lang.String ${org.myuser.jsp.sex}) as ${org.myuser.jsp.sex}"
	custombutton="<button class='but' onclick='selectvalue()'>${org.userselect.jsp.enter}</button>&nbsp;&nbsp;" 
/>
</td>
</tr>
</table>
</body>
</html>
