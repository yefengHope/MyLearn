//--------------去掉左右空格-----------------------------------
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
//--------------去掉左空格-------------------------------------    
String.prototype.lTrim = function() {
	return this.replace(/(^\s*)/g, "");
};
//--------------去掉右空格-------------------------------------    
String.prototype.rTrim = function() {
	return this.replace(/(\s*$)/g, "");
};
//--------------去掉html的空格符------------------------------
String.prototype.hTrim = function() {
	return this.replace(/(\&nbsp\;)/g, "");
};
//--------------设置绝对宽高-----------------------------------
function setWinHeight(obj) {
	var win = obj;
	if (document.getElementById) {
		if (win && !window.opera) {
			if (win.contentDocument && win.contentDocument.body.offsetHeight)
				win.height = win.contentDocument.body.offsetHeight;
			else if (win.Document && win.Document.body.scrollHeight)
				win.height = win.Document.body.scrollHeight + 30;
		}
	}
}
//--------------双击空件清空内容-------------------------------
function DoubleClickClear(e) {
	e.value = '';
}
//-------------返回浏览器类型----------------------------------
function GetBrowserType() {
	var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
	var isOpera = userAgent.indexOf("Opera") > -1;
	if (isOpera) {
		return "Opera";
	}
	; //判断是否Opera浏览器
	if (userAgent.indexOf("Firefox") > -1) {
		return "FF";
	} //判断是否Firefox浏览器
	if (userAgent.indexOf("Safari") > -1) {
		return "Safari";
	} //判断是否Safari浏览器
	if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1
			&& !isOpera) {
		return "IE";
	}
	; //判断是否IE浏览器
}
//-----------------------------------------------------------
function banBackSpace(e) {
	var ev = e || window.event;//获取event对象      
	var obj = ev.target || ev.srcElement;//获取事件源      
	var t = obj.type || obj.getAttribute('type');//获取事件源类型     
	var vReadOnly = obj.getAttribute('readonly');
	var vEnabled = obj.getAttribute('enabled');
	vReadOnly = (vReadOnly == null) ? false : vReadOnly;
	vEnabled = (vEnabled == null) ? true : vEnabled;
	var flag1 = (ev.keyCode == 8
			&& (t == "password" || t == "text" || t == "textarea") && (vReadOnly == true || vEnabled != true)) ? true
			: false;
	var flag2 = (ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea") ? true
			: false;
	if (flag2) {
		return false;
	}
	if (flag1) {
		return false;
	}
}
//-----------form check-------------------
function formcheck() {
	var inputlist = $$('input[type="hidden"][name$="_check"]');
	for (var i = 0; i < inputlist.length; i++) {
		if (inputlist[i].value == "yes") {
			var na = inputlist[i].name;
			na = na.substring(0, na.length - '_check'.length);
			noka_SDF5SDFL23JLSDGSD7DSMQS324SDSDFN_issuc(na, false);
			return false;
		}
	}
	return true;
}