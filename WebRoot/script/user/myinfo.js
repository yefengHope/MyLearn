//----------修改提交表单--------------------
function updates(){
		nk_submitform();
}
function onsuccess(a){
		switch(a.responseText.trim()){
		case '1':
			$('msg_util').innerHTML=lang.L5;
			break;
		case '2':
			$('msg_util').innerHTML=lang.L6;
			break;
		}
	}
	function onfailure(a){
	}
//-----------页面初始化------------
window.onload = function(){
	$("usname_id").focus();
};