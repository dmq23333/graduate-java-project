package com.store.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.common.pojo.EUTreeNode;
import com.store.common.pojo.StoreResult;
import com.store.mapper.TbContentCategoryMapper;
import com.store.pojo.TbContentCategory;
import com.store.pojo.TbContentCategoryExample;
import com.store.pojo.TbContentCategoryExample.Criteria;
import com.store.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	
	@Override
	public List<EUTreeNode> getCategoryList(long parentId) {
		//根据parentId查询子节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			EUTreeNode node = new EUTreeNode();
			node.setId(tbContentCategory.getId());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			node.setText(tbContentCategory.getName());
			resultList.add(node);
		}
		return resultList;
	}

	@Override
	public StoreResult insertContentCategory(long parentId, String name) {
		//创建一个pojo
		TbContentCategory tbContentCategory = new TbContentCategory();
		tbContentCategory.setName(name);
		tbContentCategory.setIsParent(false);//新节点isparent是false，父节点需要更新
		//1正常 2删除
		tbContentCategory.setStatus(1);
		tbContentCategory.setParentId(parentId);
		tbContentCategory.setSortOrder(1);
		tbContentCategory.setCreated(new Date());
		tbContentCategory.setUpdated(new Date());
		//添加记录
		tbContentCategoryMapper.insert(tbContentCategory);
		//查看父节点的isparent列是否为true，并更正
		TbContentCategory parentCategory = tbContentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parentCategory.getIsParent()) {
			parentCategory.setIsParent(true);
			//更正
			tbContentCategoryMapper.updateByPrimaryKey(parentCategory);
		}
		return StoreResult.ok(tbContentCategory);
	}

	@Override
	public StoreResult deleteContentCategory(long id) {
		TbContentCategory contentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
		//判断是否为父节点
		if(contentCategory.getIsParent()) {
			//是父节点则递归删除
			deleteNode(contentCategory.getId());
		}
		//需要判断父节点是否还有子节点，若有则不需修改
		//若没有则需修改为叶子节点 isParent属性修改为false
		TbContentCategory parentContentCategory  = tbContentCategoryMapper.selectByPrimaryKey
				(contentCategory.getParentId());
		//判断父节点下所有子节点
		List<TbContentCategory> nodeList = getContentCategoryListByParentId(parentContentCategory.getId());
		if(nodeList.size()<=0||nodeList==null) {
			parentContentCategory.setIsParent(false);
		}
		tbContentCategoryMapper.deleteByPrimaryKey(id);
		return StoreResult.ok();
	}
	
	//递归删除节点方法
	private void deleteNode(long parentId) {
		List<TbContentCategory> list = getContentCategoryListByParentId(parentId);
		for (TbContentCategory tbContentCategory : list) {
			tbContentCategoryMapper.deleteByPrimaryKey(tbContentCategory.getId());
			if(tbContentCategory.getIsParent()) {
				deleteNode(tbContentCategory.getId());
			}
		}
	}
	//根据父节点查询id查询所有子节点
	private List getContentCategoryListByParentId(long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
		return list;
	}

	@Override
	public StoreResult updateContentCategory(long id, String name) {
		//通过id查询节点
		TbContentCategory contentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
		//判断与原来的name值是否相同，相同则不用更新
		if(contentCategory.getName()==name) {
			return StoreResult.ok();
		}
		contentCategory.setName(name);
		contentCategory.setUpdated(new Date());
		tbContentCategoryMapper.updateByPrimaryKey(contentCategory);
		return StoreResult.ok();
	}
}
