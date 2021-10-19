var TT = TAOTAO = {
	checkLogin : function(){
		var _ticket = $.cookie("Store_Token");
		if(!_ticket){
			return ;
		}
		$.ajax({
			url : "http://localhost:8084/user/token/" + _ticket,
			dataType : "jsonp",
			type : "GET",
			success : function(data){
				if(data.status == 200){
					var username = data.data.username;
					var html = username + "，欢迎来到！<a href=\"javascript:void(0);\" onclick=\"js_method()\" class=\"link-logout\">[退出]</a>";
					$("#loginbar").html(html);
				}
			}
		});
	}
}
$(function(){
	// 查看是否已经登录，如果已经登录查询登录信息
	TT.checkLogin();
});

function js_method(){
	var _ticket = $.cookie("Store_Token");
	$.ajax({
	url : "http://localhost:8084/user/logout/" + _ticket,
	dataType : "jsonp",
	type : "GET",
	success : function(data){
	if(data.status == 200){
	alert("成功退出");
	window.location.href="http://localhost:8082";
		}
	}
});
};
		
