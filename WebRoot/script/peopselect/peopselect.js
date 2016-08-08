function reLeft(vid){
	if('1'==vid.value){
		$('tree_dept').show();
		$('tree_role').hide();
	}else if('2'==vid.value){
		$('tree_dept').hide();
		$('tree_role').show();
	}
	return true;
}
function tree_click(a){
	var rva = $('folid').getValue ();
	if('1'==rva[0]){
		$('dept_c_id').value=a.id;
		$('role_c_id').value='';
		wordDb_reLoadData();
	}else if('2'==rva[0]){
		$('dept_c_id').value='';
		$('role_c_id').value=a.id;
		wordDb_reLoadData();
	}
	return true;
}
function selectvalue(){
	var values="0";//值
	var svalues="0";//显示值
	var inputlist = $$('input[name="userids"]');
	for(var i=0;i<inputlist.length;i++){
		if((inputlist[i].type=="checkbox" || inputlist[i].type=="radio") && inputlist[i].checked){
			values=values+","+inputlist[i].value;
			svalues=svalues+","+$(inputlist[i].parentNode.parentNode.parentNode.cells[3].id+'_div').innerHTML;
		}
	}
	if(values.length>2){
		values=values.substring(2,values.length);
		svalues=svalues.substring(2,svalues.length);
		setValue(svalues,values);
	}else{
		setValue('','');
	}
}
function dclickvalue(row){
	var values=$(row.cells[1].id+'_div').innerHTML;//id
	var svalues=$(row.cells[3].id+'_div').innerHTML;//id
	setValue(svalues,values);
}