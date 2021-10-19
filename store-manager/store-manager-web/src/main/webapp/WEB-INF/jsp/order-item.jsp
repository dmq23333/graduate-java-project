<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<table class="easyui-datagrid" id="tbOrderItem" title="商品列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">ID</th>
            <th data-options="field:'itemId',width:120">商品ID</th>
            <th data-options="field:'orderId',width:70">订单ID</th>
            <th data-options="field:'num',width:60">数量</th>
            <th data-options="field:'price',width:70,align:'right',formatter:TAOTAO.formatPrice">价格</th>
            <th data-options="field:'title',width:500,align:'left'">商品标题</th>
            <th data-options="field:'picPath',width:70,align:'center',formatter:TAOTAO.formatUrl">图片地址</th>
        </tr>
    </thead>
   
</table>
	
</div>
