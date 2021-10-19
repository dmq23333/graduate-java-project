package com.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.common.pojo.StoreResult;
import com.store.mapper.TbAdminMapper;
import com.store.pojo.TbAdmin;
import com.store.pojo.TbAdminExample;
import com.store.pojo.TbAdminExample.Criteria;
import com.store.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private TbAdminMapper adminMapper;

	@Override
	public StoreResult adminLogin(String username, String password) {
		TbAdminExample  adminExample = new TbAdminExample();
		Criteria criteria = adminExample.createCriteria();
		criteria.andNameEqualTo(username);
		List<TbAdmin> adminList = adminMapper.selectByExample(adminExample);
		
		if(adminList.size()==0||adminList==null) {
			return StoreResult.build(400, "用户名错误");
		}
		TbAdmin admin = adminList.get(0);
		if(admin.getPassword()==password) {
			return StoreResult.build(400, "密码错误");
		}
		return StoreResult.ok();
	}

}
