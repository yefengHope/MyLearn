	
//-----------添加-------------------------
	function adds(rual){
		$("menupid_pid").value=$("menuid_id").value;
	 	$('menuform').ajaxSubmit(lang.rurl+"/menuAdd.nk");
	}	
//-----------修改-------------------------
	function updates(){
		$('menuform').ajaxSubmit(lang.rurl+"/updateMenu.nk");
	}
	
	//----数据自动填写---
	function detail(v){
		$('trepwid_id').value=$("menupid_pid").value;
		try{$("update_id").show();}catch(e){};
	}
	//---------------删除-------------------
	function dels(){
		var dataform =$("dataform");
		var mid=-1;
		var mpid=999999;
		var inputlist = window.document.getElementsByName("menuids");// dataform.getElementsByTagName("input");
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
	  			$('menuid_id').value=mid;
	  			$('trepwid_id').value=mpid;
				dataform.action=lang.rurl+'/menuDel.nk';
				menudb_ajaxSubmitDataForm();
			  }
			}else{
				alert(lang.L2);
			}
	}
	function onsuccess(a){
		switch(a.responseText.trim()){
		case '1':
			$('menuform').showInfo(lang.L3);
			try{$("update_id").hide();}catch(e){};
			$('menudb').reload();
			reAddUpTree();
			break;
		case '2':
			$('menuform').showErro(lang.L6);
			try{$("update_id").hide();}catch(e){};
			break;
		case '3':
			$('menuform').showInfo(lang.L5);
			try{$("update_id").hide();}catch(e){};
			$('menudb').reload();
			reAddUpTree();
			break;
		case '4':
			$('menuform').showErro(lang.L8);
			try{$("update_id").hide();}catch(e){};
			break;
		case '5':
			$('menuform').showInfo(lang.L4);
			try{$("update_id").hide();}catch(e){};
			$('menudb').reload();
			reTree();
			break;
		case '6':
			$('menuform').showErro(lang.L7);
			try{$("update_id").hide();}catch(e){};
			break;
		}
	}
	function reAddUpTree(){
		setTimeout(function(){
			if($('menuid_id').value=='-1')
				$('menuid_id').value='1';
			try{$('menu').LoadData($('trepwid_id').value);}catch(e){}//刷新树形菜单
			try{$('menu').LoadData($('menuid_id').value);}catch(e){}//刷新树形菜单
			setTimeout(function(){
				try{$('menu').opennode($('menuid_id').value);}catch(e){};// 打开本级
            }, 500);
        }, 500);
	}
	function reTree(){
		setTimeout(function(){
			if($('menuid_id').value=='-1')
				$('menuid_id').value='1';
			try{$('menu').LoadData($('trepwid_id').value);}catch(e){}//刷新树形菜单
			try{$('menu').LoadData($('menuid_id').value);}catch(e){}//刷新树形菜单
			try{$('menu').opennode($('trepwid_id').value);}catch(e){};// 打开本级
        }, 500);
	}
	function onfailure(a){
	}
	function menu_titileclick(node){
		$('menuid_id').value=node.id;
		$('menupid_pid').value=node.pid;
		$('menuleve_id').value=node.leve;
		$('trepwid_id').value=node.pid;
		$('menuname_id').setValue(node.name,true);
		$('menuurl_id').setValue(node.menuurl,true);
		$('menuoperate_id').setValue(node.menuoperate,true);
		$('menuimage_id').setValue(node.menuimage,true);
		$('menutarget_id').setValue(node.menutarget,true);
		$('menutaxis_id').setValue(node.menutaxis,true);
		$('menunburl_id').setValue(node.menunburl,true);
		$('ta_id').value=node.leve+'_'+node.id;
		try{$("update_id").show();}catch(e){};
		$('menudb').reload();
	}