function oncl(node){
	setTimeout(function(){
		try{
			var ak = node.selfid.substring(0,node.selfid.length-'_tree'.length);//_tree
			$(ak+'_value').value=$(ak+'_key').value+'@'+$(ak+'_userfield').value+'@'+$(ak+'_deptfield').value+'@'+$(ak+'_radios').getValue()+'@'+$(ak+'_tree').value;//表名@用户字段@部门字段@类型@指定部门
		}catch(e){};
	},500); 
	return true;
}
function type_onclick(a){
	var ak = a.name.substring(0,a.name.length-'_radios'.length);
	if(a.value=='5')
		$(ak+'_div').show();
	else
		$(ak+'_div').hide();
	$(ak+'_value').value=$(ak+'_key').value+'@'+$(ak+'_userfield').value+'@'+$(ak+'_deptfield').value+'@'+a.value+'@'+$(ak+'_tree').value;
	return true;
}
function onsuccess(a){
	switch(a.responseText.trim()){
	case '1':
		$('msg_util').innerHTML=lang.L5;
		try{$("up").hide();}catch(e){};
		break;
	case '2':
		$('msg_util').innerHTML=lang.L8;
		break;
	}
}
function onfailure(a){
}