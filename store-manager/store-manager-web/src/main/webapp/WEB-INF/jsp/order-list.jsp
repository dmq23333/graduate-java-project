<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table class="easyui-datagrid" id="orderList" title="订单列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/order/list',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'orderId',width:60">订单ID</th>
            <th data-options="field:'payment',width:70">实付金额</th>
            <th data-options="field:'paymentType',width:60,formatter:TAOTAO.formatPaymentType">支付类型</th>
            <th data-options="field:'postFee',width:60,formatter:TAOTAO.formatPrice">邮费</th>
            <th data-options="field:'status',width:60,align:'right',formatter:TAOTAO.formatOrderStatus">状态</th>
            <th data-options="field:'createTime',width:130,align:'center',formatter:TAOTAO.formatDateTime">订单创建时间</th>
            <th data-options="field:'updateTime',width:130,align:'right',formatter:TAOTAO.formatDateTime">订单更新时间</th>
            <th data-options="field:'paymentTime',width:130,align:'center',formatter:TAOTAO.formatDateTime">付款时间</th>
            <th data-options="field:'consignTime',width:130,align:'center',formatter:TAOTAO.formatDateTime">发货时间</th>
            <th data-options="field:'endTime',width:130,align:'center',formatter:TAOTAO.formatDateTime">交易完成时间</th>
            <th data-options="field:'closeTime',width:130,align:'center',formatter:TAOTAO.formatDateTime">交易关闭时间</th>
            <th data-options="field:'shippingName',width:70,align:'right'">物流名称</th>
            <th data-options="field:'shippingCode',width:70,align:'right'">物流单号</th>
            <th data-options="field:'userId',width:60,align:'right'">用户id</th>
            <th data-options="field:'buyerMessage',width:60,align:'right'">买家留言</th>
            <th data-options="field:'buyerNick',width:60,align:'right'">买家昵称</th>
            <th data-options="field:'buyerRate',width:60,align:'right'">买家是否已经评价</th>
        </tr>
    </thead>
</table>
<div id="orderConsignWindow" class="easyui-window" title="订单发货" data-options="modal:true,closed:true,iconCls:'icon-save',href:'consign-order'" style="width:80%;height:80%;padding:10px;">
</div>
<div id="orderItemWindow" class="easyui-window" title="订单详情" data-options="modal:true,closed:true,iconCls:'icon-save',href:'order-item'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var orderList = $("#orderList");
    	var sels = orderList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].orderId);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbar = [{
        text:'交易关闭',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中订单!');
        		return ;
        	}
        	$.messager.confirm('确认','确定关闭ID为 '+ids+' 的订单吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/order/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','关闭订单成功!',undefined,function(){
            					$("#orderList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    },{
        text:'发货',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个订单才能发货!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个订单!');
        		return ;
        	}
        	
        	$("#orderConsignWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#orderList").datagrid("getSelections")[0];
        			data.priceView = TAOTAO.formatPrice(data.paymentType);
        			$("#orderConsignForm").form("load",data);
        			//加载订单id
        			$("#orderConsignForm [name=orderId]").val(data.orderId);
        			$("span.orderId-show").text(data.orderId);
        		}
        	}).window("open");
        }
    },{
        text:'查看详细',
        iconCls:'icon-add',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个订单才能查看!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个订单!');
        		return ;
        	}
        	
        	$("#orderItemWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#orderList").datagrid("getSelections")[0];
        			data.priceView = TAOTAO.formatPrice(data.paymentType);
        			var params = {"ids":ids};
        			$.post("/order/order-item",params,function(data){
        			      $("#tbOrderItem").datagrid("loadData",data.data);
        			      });
        		}
        	}).window("open");
        	
        }
    }];
</script>