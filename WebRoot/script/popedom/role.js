//-----------添加-------------------------
function adds(){
 	$("wordbookform").ajaxSubmit(lang.rurl+"/roleadd.nk");
}
//-----------修改-------------------------
function updates(){
 	$("wordbookform").ajaxSubmit(lang.rurl+"/roleupdate.nk");
}
//-----------授权-------------------------------
function popedoms(){
    var dataform =$("dataform");
	var inputlist = window.document.getElementsByName("roleids");// dataform.getElementsByTagName("input");
	var roleid="0";
	var ischeck = false;
	for(var i=0;i<inputlist.length;i++){
		if(inputlist[i].type=='checkbox' && inputlist[i].checked==true){
			ischeck=true;
			roleid = inputlist[i].value;
			break;
		}
	}
	if(ischeck){
		var nmuid=$('muid').value;
		$('role_box').show({iframe:lang.rurl+'/popedom.nk?muid='+nmuid+'&roleid='+roleid,fixed:false,width:450,height:450});
	}else{
		alert(lang.R2);
	}
}
//----------数据自动填写-------------------------
function autodata(v){
	try{$("up").show();}catch(e){};
}
//-----------数据过滤---------------------
function dlfiltrate(){
 var dataform =$("dataform");
	var inputlist = window.document.getElementsByName("roleids");// dataform.getElementsByTagName("input");
	var roleid="0";
	var ischeck = false;
	for(var i=0;i<inputlist.length;i++){
		if(inputlist[i].type=='checkbox' && inputlist[i].checked==true){
			ischeck=true;
			roleid = inputlist[i].value;
			break;
		}
	}
	if(ischeck){
		var nmuid=$('muid').value;
		$('role_box').show({iframe:lang.rurl+'/dlfitarte.nk?muid='+nmuid+'&roleid='+roleid,fixed:false,width:600,height:450});
	}else{
		alert(lang.R1);
	}
}
function onsuccess(a){
	switch(a.responseText.trim()){
	case '1':
		$('wordbookform').showInfo(lang.L4);
		try{$("up").hide();}catch(e){};
		wordDb_reLoadData();
		break;
	case '2':
		$('wordbookform').showErro(lang.L7);
		try{$("up").hide();}catch(e){};
		break;
	case '3':
		$('wordbookform').showInfo(lang.L5);
		try{$("up").hide();}catch(e){};
		wordDb_reLoadData();
		break;
	case '4':
		$('wordbookform').showErro(lang.L8);
		try{$("up").hide();}catch(e){};
		break;
	case '5':
		$('wordbookform').showInfo(lang.L3);
		try{$("up").hide();}catch(e){};
		wordDb_reLoadData();
		break;
	case '6':
		$('wordbookform').showErro(lang.L6);
		try{$("up").hide();}catch(e){};
		break;
	}
}
function onfailure(a){
}