	
//-----------添加-------------------------
	function adds(rual){
	    $("pwid_id").value=$("wid_id").value;
	 	$('wordbookform').ajaxSubmit(lang.rurl+"/deptadd.nk");
	}	
//-----------修改-------------------------
	function updates(){
		$('wordbookform').ajaxSubmit(lang.rurl+"/deptupdate.nk");
	}
	
	//----数据自动填写---
	function detail(v){
		$('trepwid_id').value=$("pwid_id").value;
		try{$("update_id").show();}catch(e){};
	}
	//---------------删除-------------------
	function dels(){
		var dataform =$("dataform");
		var mid=-1;
		var mpid=999999;
		var inputlist = window.document.getElementsByName("wordids");// dataform.getElementsByTagName("input");
		var ischeck = false;
		for(var i=0;i<inputlist.length;i++){
			if(inputlist[i].type=='checkbox' && inputlist[i].checked==true){
				ischeck=true;
				try{
					cmid = parseInt(inputlist[i].value);
					var cid = inputlist[i].parentNode.parentNode.parentNode.cells[3].id;
					cmpid = parseInt($(cid+'_div').innerHTML.hTrim());
					if(mpid>=cmpid){
						mid=cmid;
						mpid=cmpid;
					}
				}catch(e){}
				break;
			}
		}
		if(ischeck){
			var r=confirm(lang.L1);
	  		if(r==true){
	  			$('wid_id').value=mid;
	  			$('trepwid_id').value=mpid;
				dataform.action=lang.rurl+'/deptdel.nk';
				wordDb_ajaxSubmitDataForm();
			  }
			}else{
				alert(lang.L2);
			}
	}
	function onsuccess(a){
		switch(a.responseText.trim()){
		case '1':
			$('wordbookform').showInfo(lang.L3);
			try{$("update_id").hide();}catch(e){};
			wordDb_reLoadData();
			setTimeout(function(){
				if($('wid_id').value=='-1')
					$('wid_id').value='1';
				try{$('dept').LoadData($('trepwid_id').value);}catch(e){}//刷新树形菜单
				try{$('dept').LoadData($('wid_id').value);}catch(e){}//刷新树形菜单
				setTimeout(function(){
					try{$('dept').opennode($('wid_id').value);}catch(e){};// 打开本级
	            }, 500);
            }, 500);
			break;
		case '2':
			$('wordbookform').showErro(lang.L6);
			try{$("update_id").hide();}catch(e){};
			break;
		case '3':
			$('wordbookform').showInfo(lang.L5);
			try{$("update_id").hide();}catch(e){};
			wordDb_reLoadData();
			setTimeout(function(){
				if($('wid_id').value=='-1')
					$('wid_id').value='1';
				try{$('dept').LoadData($('trepwid_id').value);}catch(e){}//刷新树形菜单
				try{$('dept').LoadData($('wid_id').value);}catch(e){}//刷新树形菜单
				setTimeout(function(){
					try{$('dept').opennode($('wid_id').value);}catch(e){};// 打开本级
	            }, 500);
            }, 500);
			break;
		case '4':
			$('wordbookform').showErro(lang.L8);
			try{$("update_id").hide();}catch(e){};
			break;
		case '5':
			$('wordbookform').showInfo(lang.L4);
			try{$("update_id").hide();}catch(e){};
			wordDb_reLoadData();
			setTimeout(function(){
				if($('wid_id').value=='-1')
					$('wid_id').value='1';
				try{$('dept').LoadData($('trepwid_id').value);}catch(e){}//刷新树形菜单
				try{$('dept').LoadData($('wid_id').value);}catch(e){}//刷新树形菜单
				try{$('dept').opennode($('trepwid_id').value);}catch(e){};// 打开本级
            }, 500);
			break;
		case '6':
			$('wordbookform').showErro(lang.L7);
			try{$("update_id").hide();}catch(e){};
			break;
		}
	}
	function onfailure(a){
	}
	function dept_titileclick(node){
		$('wtext_id').setValue(node.dtext,true);
		$('wcompositor_id').setValue(node.dpx,true);
		$('wname_id').setValue(node.name,true);
		$('wid_id').value=node.id;
		$('pwid_id').value=node.pid;
		$('trepwid_id').value=node.pid;
		$('plvie_id').value=node.leve;
		$('ta_id').value=node.leve+'_'+node.id;
		try{$("update_id").show();}catch(e){};
		wordDb_reLoadData();
	}