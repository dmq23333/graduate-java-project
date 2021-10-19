package com.store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.store.common.pojo.EUDataGridResult;
import com.store.common.pojo.StoreResult;
import com.store.common.utils.ExceptionUtil;
import com.store.common.utils.IDUtils;
import com.store.mapper.TbItemDescMapper;
import com.store.mapper.TbItemMapper;
import com.store.mapper.TbItemParamItemMapper;
import com.store.pojo.TbItem;
import com.store.pojo.TbItemDesc;
import com.store.pojo.TbItemExample;
import com.store.pojo.TbItemExample.Criteria;
import com.store.pojo.TbItemParamItem;
import com.store.pojo.TbItemParamItemExample;
import com.store.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
	

	@Override
	public TbItem getItemById(long itemId) {
		//TbItem tbItem = tbItemMapper.selectByPrimaryKey(itemId);
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = tbItemMapper.selectByExample(example);
		if(list!=null&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		TbItemExample example = new TbItemExample();
		//设置分页
		PageHelper.startPage(page, rows);
		List<TbItem> list = tbItemMapper.selectByExample(example);
		//取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		EUDataGridResult result = new EUDataGridResult(total, list);
		
		return result;
	}
	
	@Override
	public StoreResult createItem(TbItem tbItem,String desc,String itemParam) throws Exception {
		
		//item补全
		//生成商品id
		long itemId = IDUtils.genItemId();
		tbItem.setId(itemId);
		//商品状态1-正常 2-下架 3-删除
		tbItem.setStatus((byte) 1);
		tbItem.setCreated(new Date());
		tbItem.setUpdated(new Date());
		//插入到数据库
		tbItemMapper.insert(tbItem);
		//保证插入描述和插入商品是同一个事务
		StoreResult result = insertItemDesc(itemId, desc);
		if(result.getStatus()!=200) {
			throw new Exception();
		}
		//添加规格参数
		result = insertParamItem(itemId, itemParam);
		if(result.getStatus()!=200) {
			throw new Exception();
		}
		//返回
		return StoreResult.ok();
		
	}
	
	public StoreResult insertItemDesc(Long itemId,String descString) {
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(itemId);
		tbItemDesc.setItemDesc(descString);
		tbItemDesc.setCreated(new Date());
		tbItemDesc.setUpdated(new Date());
		tbItemDescMapper.insert(tbItemDesc);
		
		return StoreResult.ok();
	}
	
	public StoreResult insertParamItem(long itemId, String itemParam) {
		//创建一个pojo
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		//向表中插入数据
		tbItemParamItemMapper.insert(itemParamItem);
		
		return StoreResult.ok();
	}
	
	@Override
	public StoreResult getItemDesc(long itemId) {
		//根据商品id查询商品描述信息
		TbItemDesc itemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);
		return StoreResult.ok(itemDesc);
	}

	@Override
	public StoreResult getItemParam(long itemId) {
		//根据商品id查询商品规格参数信息
		TbItemParamItemExample example = new TbItemParamItemExample();
		com.store.pojo.TbItemParamItemExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
		if(list!=null&&list.size()>0) {
			TbItemParamItem paramItem = list.get(0);
			return StoreResult.ok(paramItem);
		}
		return StoreResult.build(400, "无此商品规格");
	}

	@Override
	public StoreResult deleteItemById(long[] itemId) {
		//删除商品
		for (long l : itemId) {
			tbItemMapper.deleteByPrimaryKey(l);
		}
		//删除商品描述
		for (long l : itemId) {
			tbItemDescMapper.deleteByPrimaryKey(l);
		}
		String id = String.valueOf(itemId);
		
		return StoreResult.ok();
	}

	@Override
	public StoreResult dropOfItem(long[] itemId) {
		for (long l : itemId) {
			TbItem item = tbItemMapper.selectByPrimaryKey(l);
	            item.setStatus((byte) 2);
	            //创建时间不变
	            item.setCreated(item.getCreated());
	            //更新日期改变
	            item.setUpdated(new Date());
	            tbItemMapper.updateByPrimaryKeySelective(item);

		}
		return StoreResult.ok();
	}

	@Override
	public StoreResult upperOfItem(long[] itemId) {
		for (long l : itemId) {
			TbItem item = tbItemMapper.selectByPrimaryKey(l);
	            item.setStatus((byte) 1);
	            //创建时间不变
	            item.setCreated(item.getCreated());
	            //更新日期改变
	            item.setUpdated(new Date());
	            tbItemMapper.updateByPrimaryKeySelective(item);

		}

		return StoreResult.ok();
	}

	@Override
	public StoreResult updateItem(TbItem tbItem, String desc, String itemParam) {
		try {
			TbItem tbItem2 = tbItemMapper.selectByPrimaryKey(tbItem.getId());
			TbItemDesc itemDesc = tbItemDescMapper.selectByPrimaryKey(tbItem.getId());
			itemDesc.setItemDesc(desc);
			itemDesc.setUpdated(new Date());
			tbItemDescMapper.updateByPrimaryKey(itemDesc);
			TbItemParamItemExample example = new TbItemParamItemExample();
			com.store.pojo.TbItemParamItemExample.Criteria criteria = example.createCriteria();
			criteria.andItemIdEqualTo(tbItem.getId());
			TbItemParamItem tbItemParamItem = tbItemParamItemMapper.selectByExample(example).get(0);
			tbItemParamItem.setParamData(itemParam);
			tbItemParamItem.setUpdated(new Date());
			tbItemParamItemMapper.updateByPrimaryKey(tbItemParamItem);
			//补全tbItem
			tbItem.setCreated(tbItem2.getCreated());
			tbItem.setUpdated(new Date());
			tbItem.setStatus(tbItem2.getStatus());
			tbItemMapper.updateByPrimaryKey(tbItem);
		} catch (Exception e) {
			e.printStackTrace();
			return StoreResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return StoreResult.ok();
	}
}
