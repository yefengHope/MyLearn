<%@ page language="java"  pageEncoding="UTF-8"%>
<html>
  <head>
    <title></title>
    <%@ include file="/inc/inc.jsp"%>
    <n:script src="script/test/test.js"></n:script>
    <n:link href="skins/css/body.css" rel="stylesheet"></n:link>
  </head>
<body>
<n:DBGrid height="400" id="sfff" sql="${sql}" width="600" title="test"/>
</body>
</html>