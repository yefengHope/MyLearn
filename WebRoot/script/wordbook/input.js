function addss(rooturl){
  	$('wordbookform').ajaxSubmit(rooturl+"/wordbookadd.nk");
}
//-----------修改-------------------------
function updates(rooturl){
	$('wordbookform').ajaxSubmit(rooturl+"/wordBookupdate.nk");
}
//----数据自动填写---
function detail(v){
	try{
		$("update_id").show();
	}catch(e){};
}
//------------------------------------------
function onsuccess(a){
	if('1'==a.responseText.trim()){
		$('wordbookform').showInfo(lang.L3);
		try{$("update_id").hide();}catch(e){};
		$('wordDb').reload();
	}else if('2'==a.responseText.trim()) {
		$('wordbookform').showErro(lang.L6);
		try{$("update_id").hide();}catch(e){};
	}else if('3'==a.responseText.trim()) {
		$('wordbookform').showInfo(lang.L5);
		try{$("update_id").hide();}catch(e){};
		$('wordDb').reload();
	}else if('4'==a.responseText.trim()) {
		$('wordbookform').showErro(lang.L8);
		try{$("update_id").hide();}catch(e){};
	}else if('5'==a.responseText.trim()) {
		$('wordbookform').showInfo(lang.L4);
		try{$("update_id").hide();}catch(e){};
		$('wordDb').reload();
	}else if('6'==a.responseText.trim()) {
		$('wordbookform').showErro(lang.L7);
		try{$("update_id").hide();}catch(e){};
	}
}
function onfailure(a){
}