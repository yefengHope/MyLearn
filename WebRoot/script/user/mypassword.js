function updates(Ler){
    if($("newpassword_id").value!= $("uspassword_id").value){
  		alert(Ler);
  		$("newpassword_id").value="";
  		$("uspassword_id").value="";
  		$("newpassword_id").focus();
  }else{
	  nk_submitform();
	}
}

function onsuccess(a) {
	switch (a.responseText.trim()) {
	case '1':
		$('msg_util').innerHTML = lang.L5;
		break;
	case '2':
		$('msg_util').innerHTML = lang.L6;
		break;
	}
}
function onfailure(a) {
}