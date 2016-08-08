<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/inc/nosession_inc.jsp"%>
<title>${org.noka.sys.Projectname}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0" />
<link rel="shortcut icon" type="image/x-icon" href="${rooturl}/main/noka.ico"/>
<n:link rel="stylesheet" type="text/css" href="skins/css/bootstrap.min.css"/>
<n:link rel="stylesheet" type="text/css" href="skins/css/login.css"></n:link>
<n:script src="script/login/login.js"></n:script>
</head>
<body>
<div class="box">
		<div class="login-box">
			<div class="login-title text-center">
				<h1><small>BMS</small></h1>
			</div>
			<div class="login-content ">
			<div class="form">
			<form action="${rooturl}/login.nk" method="post" id="loginform" target="_top">
				<div class="form-group">
					<div class="col-xs-12  ">
						<div class="input-group">
							<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
							<input type="text" name="user.usname" id="id_name"  class="form-control" placeholder="用户名"/>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-12  ">
						<div class="input-group">
							<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
							<input  type="password"  name="user.uspassword" id="id_password" class="form-control"/>
						</div>
					</div>
				</div>
				<div class="form-group form-actions">
					<div class="col-xs-4 col-xs-offset-4 ">
						<button type="submit" class="btn btn-sm btn-info"><span class="glyphicon glyphicon-off"></span> 登录</button>
					</div>
				</div>
				<div class="form-group erromsg">
					${erro}
				</div>
			</form>
			</div>
		</div>
	</div>
</div>

</body>

</html>