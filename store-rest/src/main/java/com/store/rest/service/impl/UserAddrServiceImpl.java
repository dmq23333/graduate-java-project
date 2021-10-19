package com.store.rest.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.common.pojo.StoreResult;
import com.store.mapper.TbUserShippingMapper;
import com.store.pojo.TbUserShipping;
import com.store.pojo.TbUserShippingExample;
import com.store.pojo.TbUserShippingExample.Criteria;
import com.store.rest.service.UserAddrService;

@Service
public class UserAddrServiceImpl implements UserAddrService{
	

	
	@Autowired 
	private TbUserShippingMapper userShippingMapper;

	@Override
	public StoreResult getUserShipingByUserId(long userId) {
		//根据userid查询收货地址
		TbUserShippingExample example = new TbUserShippingExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<TbUserShipping> userShippingList = userShippingMapper.selectByExample(example);
		if(userShippingList!=null&&userShippingList.size()>0) {
			return StoreResult.ok(userShippingList);
		}
		return StoreResult.build(400, "暂无收货地址");
	}

	@Override
	public StoreResult createUserShippingAddr(TbUserShipping userShipping) {
		
		//补全pojo
		userShipping.setCreated(new Date());
		userShipping.setUpdated(new Date());
		//插入表中
		userShippingMapper.insert(userShipping);
		return StoreResult.ok();
	}

	@Override
	public StoreResult getUserShippingByID(long shippingId) {
		TbUserShipping userShipping = userShippingMapper.selectByPrimaryKey(shippingId);
		if(userShipping!=null) {
			return StoreResult.ok(userShipping);
		}
		return StoreResult.build(400, "无此收货地址");
	}

	@Override
	public StoreResult updateUserShippingAddr(TbUserShipping userShipping) {
		userShipping.setCreated(new Date());
		userShipping.setUpdated(new Date());
		
		userShippingMapper.updateByPrimaryKey(userShipping);
		
		return StoreResult.ok();
	}

}
