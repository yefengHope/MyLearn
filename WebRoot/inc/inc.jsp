<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="n" uri="/noka"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="b" uri="/sys"%>
<!-- js  -->
<n:script src="script/public.js"></n:script>
<%if(request.getSession().getAttribute("user")==null){%>
 <script type="text/javascript">
 	window.parent.location = "${rooturl}/index.jsp";
 </script>    
<%}%>
<!-- css  -->
<n:link href="skins/css/main.css" rel="stylesheet"></n:link>
<script type="text/javascript">
<n:jszip id="inc_jsid">
	try{
		document.onkeypress=banBackSpace;   
		document.onkeydown=banBackSpace;
		//document.oncontextmenu=function(){return false;};
	}catch(e){}
	var lang={
		L1:'${org.noka.sys.isdel}',//你确定要删除这些数据吗？
		L2:'${org.noka.sys.dellselect}',//请选择删除项
		L3:'${org.noka.sys.adds}',//添加成功
		L4:'${org.noka.sys.dells}',//删除成功
		L5:'${org.noka.sys.upds}',//修改成功
		L6:'${org.noka.sys.addf}',//添加失败
		L7:'${org.noka.sys.dellf}',//删除失败
		L8:'${org.noka.sys.updf}',//修改失败
		rurl:''
	};
	</n:jszip>
	lang.rurl='${rootpath}';
</script>