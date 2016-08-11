<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>客户信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@ include file="/inc/inc.jsp"%>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	

</head>

<body>
	<n:form id="customerForm" name="customerForm" method="post" onsuccess="onsuccess" onfailure="onfailure">
		<n:frow>
			<n:fcell label="客户名称:">
				<n:InputText name="customer.cucustomername" id="cucustomername" allownull="false" mesg="不能为空"></n:InputText>
			</n:fcell>
			<n:fcell label="客户编号:">
				<n:InputText name="customer.cucrmnumber" id="cucrmnumber"></n:InputText>
			</n:fcell>
			<n:fcell label="跟进人:">
				<n:selecbutton id="cufollowid"></n:selecbutton>
			</n:fcell>
		</n:frow>
		<n:frow>
			<n:fcell label="申请姓名:">
				<n:InputText name="customer.cuapplyname" id="cuapplyname"></n:InputText>
			</n:fcell>
			<n:fcell label="申请类型:">
				<n:InputText name="customer.cuapplytype" id="cuapplytype"></n:InputText>
			</n:fcell>
			<n:fcell label="证件号码:">
				<n:InputText name="customer.cudoccard" id="cudoccard"></n:InputText>
			</n:fcell>
		</n:frow>
		<n:frow>
			<n:fcell label="申请地址:">
				<n:InputText name="customer.cuapplyadress" id="cuapplyadress"></n:InputText>
			</n:fcell>
			<n:fcell label="详细地址:">
				<n:Textarea name="customer.cuapplyadressdetail"
					id="cuapplyadressdetail"></n:Textarea>
			</n:fcell>
			<n:fcell label="申请时间:">
				<n:date name="customer.cuapplydate" id="cuapplydate"></n:date>
			</n:fcell>
		</n:frow>
		<n:frow>
			<b:button name="add" id="add" value="添加" classstyle="but" onclick="adds()"/>
			<b:button name="update" id="up" value="修改" classstyle="but" onclick="update()"/>
		</n:frow>
	</n:form>
	<n:DBGrid 
	id="customerDB" 
	sql="${sql}" 
	width="800" 
	height="600"
	title="客户列表"
	number="true"
	checkd="checkbox"
	editRow="true"
	outpdfall="true"
	tableformid="customerId"
	onsuccess="onsuccess"
	onfailure="onfailure"></n:DBGrid>
	
	
	<script type="text/javascript">
		//---------添加用户------------
		function adds(){
			if(formcheck()){
				$("customerForm").ajaxSubmit(lang.rurl+"/customerinfoadd.nk");
			}
		}
		//---------更新用户------------		
		function update(){
			$("customerForm").ajaxSubmit(lang.rurl+"/customerinfoupdate.nk");
		}
		function onsuccess(a){
			if('1'==a.responseText.trim()){
				$("customerForm").showInfo(lang.L3);
				try{$("up").hide();}catch(e){};
				customerDB_reLoadData();
			}else if('2'==a.responseText.trim()) {
				$("customerForm").showErro(lang.L6);
				try{$("up").hide();}catch(e){};
			}else if('3'==a.responseText.trim()) {
				$("customerForm").showInfo(lang.L5);
				try{$("up").hide();}catch(e){};
				$('usname_id').disabled='';
				customerDB_reLoadData();
			}else if('4'==a.responseText.trim()) {
				$("customerForm").showErro(lang.L8);
				try{$("up").hide();}catch(e){};
			}else if('5'==a.responseText.trim()) {
				$("customerForm").showInfo(lang.L4);
				try{$("up").hide();}catch(e){};
				$('usname_id').disabled='';
				customerDB_reLoadData();
			}else if('6'==a.responseText.trim()) {
				$("customerForm").showErro(lang.L7);
			}else if('7'==a.responseText.trim()) {
				$("customerForm").showInfo(lang.L5);
				try{$("up").hide();}catch(e){};
				customerDB_reLoadData();
			}else if('8'==a.responseText.trim()) {
				$("customerForm").showErro(lang.L8);
				try{$("up").hide();}catch(e){};
			}else if('9'==a.responseText.trim()) {
				$("customerForm").showInfo(lang.L5);
				try{$("up").hide();}catch(e){};
				customerDB_reLoadData();
			}else if('10'==a.responseText.trim()) {
				$("customerForm").showErro(lang.L8);
				try{$("up").hide();}catch(e){};
			}
		}
		function onfailure(a){
			console.log("执行失败!!");
		}
	</script>
</body>
</html>
