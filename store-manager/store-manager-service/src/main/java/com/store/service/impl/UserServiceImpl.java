package com.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.store.common.pojo.EUDataGridResult;
import com.store.common.pojo.StoreResult;
import com.store.mapper.TbUserMapper;
import com.store.pojo.TbUser;
import com.store.pojo.TbUserExample;
import com.store.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private TbUserMapper userMapper;

	@Override
	public EUDataGridResult getUserList(int page, int rows) {
		TbUserExample example = new TbUserExample();
		//设置分页
		PageHelper.startPage(page, rows);
		List<TbUser> userList = userMapper.selectByExample(example);
		//取分页信息
		PageInfo<TbUser> pageInfo = new PageInfo<TbUser>(userList);
		long total = pageInfo.getTotal();
		EUDataGridResult result = new EUDataGridResult(total, userList);
		return result;
	}

	@Override
	public StoreResult deleteUserById(long[] userId) {
		for (long l : userId) {
			userMapper.deleteByPrimaryKey(l);
		}
		return StoreResult.ok();
	}

}
