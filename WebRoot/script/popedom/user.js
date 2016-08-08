//--------表单数据合法性检查--------------------------
function formcheck(){
	if($("uspassword_id").value!=$("uspassword_id2").value){
   	   alert(lang.U3);
	   $("uspassword_id").focus();
   	}
   		return true;
}
//-------------自动填写数据----------------------------
function detail(v){
	$('usname_id').disabled="disabled";
	try{$("up").show();}catch(e){};
}
//----------添加数据----------------------------------
function adds(){
	if(formcheck()){//通过验证
		$("wordbookform").ajaxSubmit(lang.rurl+"/useradd.nk");
	}
}
//----------修改数据---------------------------------
function updates(){
   if(formcheck()){//通过验证
		$("wordbookform").ajaxSubmit(lang.rurl+"/userupdate.nk");
	}
}
//--------启用------------------------------------
function duserstart(){
	var roids = $('wordDb').getFormSelect();
	if(roids.length>0){
		dataform.action=lang.rurl+'/userstart.nk';
		wordDb_ajaxSubmitDataForm();//
	}else{
		alert(lang.U2);
	}
}
function dellform(){
	var roids = $('wordDb').getFormSelect();
	if(roids.length>0){
		var r=confirm(lang.L1);
  		if(r==true){
	    	$("dataform").action=lang.rurl+'/userdel.nk';
	    	wordDb_ajaxSubmitDataForm();
		  }
	}else{
		alert(lang.L2);
	}
}
//--------禁 用------------------------------------
function userstop(){
	var roids = $('wordDb').getFormSelect();
	if(roids.length>0){
		dataform.action=lang.rurl+'/userstop.nk';
		wordDb_ajaxSubmitDataForm();
	}else{
		alert(lang.U1);
	}
}
//--------分配角色--------------------------------
function popedoms(){
	var roids = $('wordDb').getFormSelect();
	$('wordDb').setFormSubmitInfo('','');
	if(roids.length>0){
		$('noka_box').show({iframe:lang.rurl+'/userrole.nk?user.usid='+roids[0],fixed:false,width:600,height:300});
	}else{
		alert(lang.U4);
	}
}
//--------fugle----------------------------------
function nsetfugle(a){
	$("usfugle_s").value=a.responseText;
}
function onsuccess(a){
	if('1'==a.responseText.trim()){
		$("wordbookform").showInfo(lang.L3);
		try{$("up").hide();}catch(e){};
		wordDb_reLoadData();
	}else if('2'==a.responseText.trim()) {
		$("wordbookform").showErro(lang.L6);
		try{$("up").hide();}catch(e){};
	}else if('3'==a.responseText.trim()) {
		$("wordbookform").showInfo(lang.L5);
		try{$("up").hide();}catch(e){};
		$('usname_id').disabled='';
		wordDb_reLoadData();
	}else if('4'==a.responseText.trim()) {
		$("wordbookform").showErro(lang.L8);
		try{$("up").hide();}catch(e){};
	}else if('5'==a.responseText.trim()) {
		$("wordbookform").showInfo(lang.L4);
		try{$("up").hide();}catch(e){};
		$('usname_id').disabled='';
		wordDb_reLoadData();
	}else if('6'==a.responseText.trim()) {
		$("wordbookform").showErro(lang.L7);
	}else if('7'==a.responseText.trim()) {
		$("wordbookform").showInfo(lang.L5);
		try{$("up").hide();}catch(e){};
		wordDb_reLoadData();
	}else if('8'==a.responseText.trim()) {
		$("wordbookform").showErro(lang.L8);
		try{$("up").hide();}catch(e){};
	}else if('9'==a.responseText.trim()) {
		$("wordbookform").showInfo(lang.L5);
		try{$("up").hide();}catch(e){};
		wordDb_reLoadData();
	}else if('10'==a.responseText.trim()) {
		$("wordbookform").showErro(lang.L8);
		try{$("up").hide();}catch(e){};
	}
}
function onfailure(a){
}