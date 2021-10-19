<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理员登录</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/body.css"/> 
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
</head>
<body>
<div class="container">
	<section id="content">
		<form action="" method="post" id="formLogin">
			<h1>E-Shop管理员登录</h1>
			<div>
				<input type="text" name="username" placeholder="用户名" data-options="required:true" id="username" />
			</div>
			<div>
				<input type="password" name="password" placeholder="密码" data-options="required:true" id="password" />
			</div>
			 <div class="">
				<span class="help-block u-errormessage" id="js-server-helpinfo">&nbsp;</span>			</div> 
			<div>
				<!-- <input type="submit" value="Log in" /> -->
				<input type="submit" value="登录" class="btn btn-primary" id="js-btn-login"/>
				<!-- <a href="#">忘记密码?</a>-->
				<!-- <a href="#">Register</a> -->
			</div>
		</form><!-- form -->
		  <!-- button -->
	</section><!-- content -->
</div>
<!-- container -->


<br><br><br><br>

    
    <script type="text/javascript">
    	$("#js-btn-login").click(function(){
    		var username = $("[name=username]").val();
    		var password = $("[name=password]").val();
    		var redirectUrl = "";
    		
    		$.post("/login", $("#formLogin").serialize(),function(data){
				if (data.status == 200) {
					alert("登录成功！");
					if (redirectUrl == "") {
						location.href = "http://localhost:8080/index";
					} else {
						location.href = redirectUrl;
					}
				} else {
					alert("登录失败，原因是：" + data.msg);
					$("#username").select();
				}
			});
    		window.location.href="localhost:8080";
    	});
    </script>
</body>
</html>