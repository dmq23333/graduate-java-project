<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" /> 
    <meta name="format-detection" content="telephone=no" />  
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" /> 
    <meta name="format-detection" content="telephone=no" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>收货地址选择页 -电子商城</title>
    <style>
        .shade{
                width: 80%; 
                height: 100%; 
                overflow-y: hidden; 
                position: fixed; 
                z-index: 100; 
                top: 0; 
                background: black; 
                opacity: 0.5;
        }
        .shade_content{width: 400px; border-radius: 10px; height: 500px; position: fixed; z-index: 101; top: 50%; left: 50%; margin-top: -250px; margin-left: -400px; background: #FFFFFF; }
		.shade_content_div{margin: 0px auto; text-align: center; height: 100%; width: 80%; margin-top: 20px;}
		.shade_title{font-size: 15px; text-align: center; font-weight: bold; font: "微软雅黑";}
        .shade_colse{text-align: right; margin-top: 10px; }
		.shade_colse button{font-size: 20px; color: white; outline: 0px; margin-right: 10px; border-radius: 50px; background: red; height: 30px; width: 30px; border: 0px;}
   		.btn_remove{border-radius: 5px; background: #31B0D5; color: white; width: 80px; height: 20px; border: 0px; outline: 0px; font-size: 15px;margin-top: 5px;}
		.sub_set{border-radius: 5px; background: #31B0D5; color: white; width: 80px; height: 20px; border: 0px; outline: 0px; font-size: 15px;margin-top: 5px;}
    	.input_style{height: 20px; border-radius: 5px; width: 200px; outline: 0px; border: 1px solid #CCCCCC; margin-top: 0px;}
		.span_style{font-size: 18px;margin-top: 20px;}
    </style>
	<!--结算页面样式-->	
	<link rel="stylesheet" type="text/css" href="/css/base.css" media="all" />
    <link type="text/css" rel="stylesheet"  href="/css/order-commons.css" source="widget"/>	
	<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
	<script type="text/javascript" src="/js/base.js"></script>	
	<script type="text/javascript" src="/js/order.common.js"></script>
	<script type="text/javascript" src="/js/jquery.checkout.js"></script>
	<script type="text/javascript">
	var SHIPPINGLIST={
			doSubmit:function(){
				$.post("/add/slist.html",$("#personShippingAdd").serialize(), function(data){
					if(data.status == 200){
						alert('添加地址成功！');
						window.location.reload();
						onclick_close();
					} else {
						alert("添加失败！");
					}
				});
			},
	doEdit:function(){
		$.post("/update/slist.html",$("#personShippingAdd").serialize(), function(data){
			if(data.status == 200){
				alert('修改地址成功！');
				window.location.reload();
				onclick_close();
			} else {
				alert("修改失败！");
			}
		});
	}
	}
	
	
	function onclick_close() {
		var shade_content = $(".shade_content");
		var shade = $(".shade");
			shade_content.hide();
			shade.hide();
	}

	function use_NewConsignee(){
	$(".shade_content").show();
		$(".shade").show();
		var input_out = $(".input_style");
		for (var i = 0; i <= input_out.length; i++) {
			if ($(input_out[i]).val() != "") {
				$(input_out[i]).val("");
			}
		}
}
	function use_UpdateConsignee(data){

		$(".shade_content").show();
			$(".shade").show();
			var input_out = $(".input_style");
			var receriverName = $(data).parents("div.consignee-item").find("span.us-receiverName").text();
			var receiverPhone = $(data).parents("div.consignee-item").find("span.us-receiverPhone").text();
			var receiverState = $(data).parents("div.consignee-item").find("span.us-receiverState").text();
			var receiverCity = $(data).parents("div.consignee-item").find("span.us-receiverCity").text();
			var receiverDistrict = $(data).parents("div.consignee-item").find("span.us-receiverDistrict").text();
			var receiverAddress = $(data).parents("div.consignee-item").find("span.us-receiverAddress").text();
			var receiverZip = $(data).parents("div.consignee-item").find("span.us-receiverZip").text();
			var id = $(data).parents("div.consignee-item").find("span.us-id").text();
			$(input_out[0]).attr("value",receiverState);
			$(input_out[1]).attr("value",receiverCity);
			$(input_out[2]).attr("value",receiverDistrict);
			$(input_out[3]).attr("value",receiverAddress);
			$(input_out[4]).attr("value",receiverZip);
			$(input_out[5]).attr("value",receriverName);
			$(input_out[6]).attr("value",receiverPhone);
			$(input_out[7]).attr("value",id);
	}
	
	
	$(function(){
		$(".shade_content").hide();
				$(".shade").hide();
		});

	
		function onclick_choose(data){
			var receriverName = $(data).find("span.us-receiverName").text();
			var receiverPhone = $(data).find("span.us-receiverPhone").text();
			var receiverState = $(data).find("span.us-receiverState").text();
			var receiverCity = $(data).find("span.us-receiverCity").text();
			var receiverDistrict = $(data).find("span.us-receiverDistrict").text();
			var receiverAddress = $(data).find("span.us-receiverAddress").text();
			var receiverZip = $(data).find("span.us-receiverZip").text();
			var id = $(data).find("span.us-id").text();
			var r = confirm("选择此收货地址吗");
			if(r==true){
				$.post("/shipping/confirm/"+id+".html",function(data){
					location.href = "http://localhost:8082/order/order-cart.html";
				});
			}
			
		}
	</script>
</head>	<body id="mainframe">
<jsp:include page="commons/shortcut.jsp" />
<!--shortcut end-->

<div class="w w1 header clearfix">
    <div id="logo"><a href="/"><img src="/images/taotao-logo.gif" alt="电子商城"></a></div>
</div>

<form id="shippingForm" class="hide" action="" method="post">
		<input type="hidden" id="receiverName-input" name="receiverName" value=" "/>
		<input type="hidden" id="receiverPhone-input" name="receiverPhone" value=" "/>
		<input type="hidden" id="receiverState-input" name="receiverState" value=" "/>
		<input type="hidden" id="receiverCity-input" name="receiverCity" value=" "/>
		<input type="hidden" id="receiverDistrict-input" name="receiverDistrict" value=" "/>
		<input type="hidden" id="receiverAddress-input" name="receiverAddress" value=" "/>
		<input type="hidden" id="receiverZip-input" name="receiverZip" value=" "/>
		<input type="hidden" id="id-input" name="id" value=" "/>
</form>

<!-- main -->
<div id="container">
	<div id="content" class="w">
		<div class="m">
			<div class="mt">
				<h2>收货地址列表查看</h2>
			</div>
			<div class="mc">
				<div class="checkout-steps">
<!--  /widget/consignee-step/consignee-step.tpl -->
<div class="step-tit">
	<h3>收货人信息</h3>
	<div class="extra-r">
		<a href="#none" class="ftx-05" onclick="javascript:use_NewConsignee()">新增收货地址</a>
	</div>
</div>
<div class="step-cont">
	<div class="consignee-list" id="consignee-list1">
		<a href="#none" id="prev" class="prev arrow-btns"></a>
		<a href="#none" id="next" class="next arrow-btns"></a>
		<div id="consignee1" class="list-cont ui-switchable-body">
            <div id="consignee-ret"></div>
           
   			<ul class="ui-switchable-panel-main" id="consignee-list">
				<!---->
				 <c:forEach items="${userShippingList}" var="userShipping">
				<li class="ui-switchable-panel" id="consignee_index_137617472"
					selected="selected" style="cursor: pointer;">
					<span style='display:none;' class="us-id">${userShipping.id}</span>
					<div class="consignee-item"
						consigneeId="137617472" id="consignee_index_div_137617472"
						onclick="onclick_choose(this)">
						<b></b>
						<span style='display:none;' class="us-id">${userShipping.id}</span>
						<div class="user-name">
							<div class="fl">
								<strong limit="4"><span class="us-receiverName">${userShipping.receiverName}</span></strong>&nbsp;&nbsp;收
							</div>
							<div class="fr"><span class="us-receiverPhone">${userShipping.receiverPhone}</span></div>
							<div class="clr"></div>
						</div>
						<div class="mt10" limit="15"><span class="us-receiverState">${userShipping.receiverState} </span><span class="us-receiverCity">${userShipping.receiverCity}</span></div>
						<div class="adr-m" limit="30"><span class="us-receiverDistrict">${userShipping.receiverDistrict}</span> <span class="us-receiverAddress">${userShipping.receiverAddress}</span></div>
						<div class="adr-m" limit="30"><span class="us-receiverZip">${userShipping.receiverZip}</span></div>
						<div class="op-btns ar">
							<a href="#none" onclick="javascript:use_UpdateConsignee(this);"
								class="ftx-05 mr10 edit-consignee" fid="137617472">编辑</a>
							<a href="#none" class="ftx-05 del-consignee hide"
								fid="137617472">删除</a>
						</div>
					</div>
				</li>
				</c:forEach>
				<!---->
			</ul>
		</div>
	</div>
</div>
<!--/ /widget/consignee-step/consignee-step.tpl -->
	


<!-- /main -->
<div class="shade_content" style ="display: block;">
			<div class="col-xs-12 shade_colse">
				<button onclick="javascript:onclick_close();">x</button>
			</div>
			<div class="nav shade_content_div">
				<div class="col-xs-12 shade_title">
					收货地址编辑与新增
				</div>
				<div class="col-xs-12 shade_from">
					<form action="" method="post" id="personShippingAdd">
						<div class="col-xs-12">
							<span class="span_style" id="">省&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份</span>
							<input class="input_style" type="" name="receiverState" id="region" value="" placeholder="&nbsp;&nbsp;请输入您的所在省份" />
						</div>
						<div class="col-xs-12">
							<span class="span_style" id="">城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市</span>
							<input class="input_style" type="" name="receiverCity" id="region" value="" placeholder="&nbsp;&nbsp;请输入您的所在城市" />
						</div>
						<div class="col-xs-12">
							<span class="span_style" id="">区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;县</span>
							<input class="input_style" type="" name="receiverDistrict" id="region" value="" placeholder="&nbsp;&nbsp;请输入您的所在区/县" />
						</div>
						<div class="col-xs-12">
							<span class="span_style" id="">详细地址</span>
							<input class="input_style" type="" name="receiverAddress" id="address" value="" placeholder="&nbsp;&nbsp;请输入您的详细地址" />
						</div>
						<div class="col-xs-12">
							<span class="span_style" id="">邮政编号</span>
							<input class="input_style" type="" name="receiverZip" id="number_this" value="" placeholder="&nbsp;&nbsp;请输入您的邮政编号" />
						</div>
						<div class="col-xs-12">
							<span class="span_style" class="span_sty" id="">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</span>
							<input class="input_style" type="" name="receiverName" id="name_" value="" placeholder="&nbsp;&nbsp;请输入您的姓名" />
						</div>
						<div class="col-xs-12">
							<span class="span_style" id="">手机号码</span>
							<input class="input_style" type="" name="receiverPhone" id="phone" value="" placeholder="&nbsp;&nbsp;请输入您的手机号码" />
							<input class="input_style" name="id" style='display:none;' value="" />
						</div>
						<div class="col-xs-12">
							<input class="btn_remove" type="button" id="" onclick="javascript:onclick_close();" value="取消" />
							<input type="submit" class="sub_set" id="sub_setID" value="提交" onclick="SHIPPINGLIST.doSubmit();" />
							<input type="submit" class="sub_set" id="sub_setID" value="修改" onclick="SHIPPINGLIST.doEdit();" />
						</div>
					</form>
				</div>
			</div>
		</div>
	<jsp:include page="commons/footer.jsp" />
	</body>
	
</html>