<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="orderConsignForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>订单ID:</td>
	            <td>
	            	<input type="hidden" name="orderId" style="width: 20px;"></input>
	            	<span class="orderId-show"></span>	
	            </td>
	        </tr>
	        <tr>
	            <td>物流名称:</td>
	            <td><input class="easyui-textbox" type="text" name="shippingName" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>物流单号:</td>
	            <td><input class="easyui-textbox" name="shippingCode" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	      
	    </table>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitOrderForm()">提交</a>
	    <script type="text/javascript">
	
	function submitOrderForm(){
		if(!$('#orderConsignForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		$.post("/order/consign",$("#orderConsignForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','商品发货成功!','info',function(){
					$("#orderConsignWindow").window('close');
					$("#orderList").datagrid("reload");
				});
			}
		});
	}

</script>
	    
	    
	</div>
</div>
