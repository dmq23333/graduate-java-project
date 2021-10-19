package com.store.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.sql.ast.expr.SQLCaseExpr.Item;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.store.common.pojo.EUDataGridResult;
import com.store.common.pojo.EUItemParam;
import com.store.common.pojo.StoreResult;
import com.store.mapper.TbItemCatMapper;
import com.store.mapper.TbItemParamMapper;
import com.store.pojo.TbItemCat;
import com.store.pojo.TbItemParam;
import com.store.pojo.TbItemParamExample;
import com.store.pojo.TbItemParamExample.Criteria;
import com.store.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper tbItemParamMapper;
	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	

	@Override
	public EUDataGridResult getItemParamList(int page, int rows) {
		TbItemParamExample example = new TbItemParamExample();
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		List<EUItemParam> euItemParams = new ArrayList<EUItemParam>();
		for (int i = 0; i < list.size(); i++) {
			TbItemCat tbItemCat = new TbItemCat();
			EUItemParam euItemParam = new EUItemParam();
			tbItemCat = tbItemCatMapper.selectByPrimaryKey(list.get(i).getItemCatId());
			euItemParam.setCreated(list.get(i).getCreated());
			euItemParam.setId(list.get(i).getId());
			euItemParam.setItemCatId(list.get(i).getItemCatId());
			euItemParam.setItemCatName(tbItemCat.getName());
			euItemParam.setParamData(list.get(i).getParamData());
			euItemParam.setUpdated(list.get(i).getUpdated());
			euItemParams.add(euItemParam);
		}
		
		
		//设置分页
		PageHelper.startPage(page,rows);
		
		//取分页信息
		PageInfo<EUItemParam> pageInfo = new PageInfo<>(euItemParams);
		long total = pageInfo.getTotal();
		EUDataGridResult result = new EUDataGridResult(total, euItemParams);
		return result;
	}
	
	@Override
	public StoreResult getItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		//判断是否查询到结果
		if(list!=null&&list.size()>0) {
			return StoreResult.ok(list.get(0));
		}
		return StoreResult.ok();
	}
	
	@Override
	public StoreResult insertItemParam(TbItemParam tbItemParam){
		//补全pojo
		tbItemParam.setCreated(new Date());
		tbItemParam.setUpdated(new Date());
		//插入到规格参数模板表
		tbItemParamMapper.insert(tbItemParam);
		return  StoreResult.ok();
	}

	@Override
	public StoreResult deleteItemParam(List<Long> ids) {
		int result = tbItemParamMapper.deleteBatch(ids);
		
		if(result > 0){
			return  StoreResult.ok(ids);
		}
		return StoreResult.ok();
	}

}
