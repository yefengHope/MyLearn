<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="n" uri="/noka"%>
<html>
  <head>
    <title></title>
    <%@ include file="/inc/inc.jsp"%>
    <n:script src="script/test/test.js"></n:script>
  </head>
<body>
<script type="text/javascript">
function cccccccc(){
	$('test_id').ajaxSubmitDataForm();
	
}
</script>
<n:select id="ffdddd" sql="select wid,wname from nk_sys_wordbook" name="ffff"/>
<n:date name="fff" id="dd"></n:date>
<n:InputPass name="fsss" id="ddd" showpass="true"></n:InputPass>
<!-- 表单 start-->
<n:form action="" method="post" id="wordbookform" name="wordbookform" onsuccess="onsuccess" onfailure="onfailure">
<input type="hidden" value="1" id="wid_id" name="wordBook.tid">
<n:frow>
	<n:fcell label="测林：">
		<n:InputText width="174" chtype="regex" veri="[1-9]{1,3}" name="wordBook.tname" id="wname_id" allownull="false" maxlength="20" mesg="${org.noka.sys.nonull}" classstyle="text" ondblclick="DoubleClickClear(this)"/>
	</n:fcell>
</n:frow>
<n:frow>
<n:fcell style="float: left;padding-left:200px;vertical-align: middle;text-align: center;width:800;">
  <b:button id="add_id" name="add" classstyle="but" onclick="addss()" value="添加"/>&nbsp;&nbsp;
  <b:button id="update_id"  name="update" style="display: none;" classstyle="but"  onclick="updates()" value="${org.noka.sys.update}"/>
</n:fcell>
</n:frow>
<n:forminfo/>
</n:form>


<n:DBGrid height="400px" id="test_id" sql="${sql}" width="500px"
selectfiled="TNAME like %$[ssss,TNAME]%"
outpdfall="true"
inputData="[{field:'wid_id',value:1},{field:'wname_id',value:2}]"
onrowdblclick="detail"
checkd="checkbox"
onfailure="onfailure"
	onsuccess="onsuccess"
checkrecord="wordrows"
tableformAction="testdel.nk"
tableformid="id_dataform"
custombutton="${b:but('delete',org.noka.sys.dell,'cccccccc()')}"
 editCells="[{cell:'TNAME',cfg:{type:'input',allownull:false}}]"
 editRow="true"
/>
</body>
</html>