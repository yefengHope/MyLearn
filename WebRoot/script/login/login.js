function $(a) {
	return document.getElementById(a);
}
//---------表单验证------------------------
  function validate(a,b) {
		if ($("id_name").value == ""){
			alert(a);
			return false;
		}else if ($("id_password").value == "") {
			alert(b);
			return false;
		}else{
			return true;
		}
	}
  //-----------表单提交-----------------------
  function login(a,b) {
	if (validate(a,b)) {
		$("loginform").submit();
	}
  }
  //-----------焦点初始化---------------------
  window.onload = function() {
		window.focus();
		$("id_name").focus();
		document.body.onkeypress = function(event) {
			event = event || window.event;
			if(event.keyCode == 13) {
				login();
			}
		};
	};