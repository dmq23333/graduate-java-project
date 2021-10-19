package com.store.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.store.common.pojo.JsonUtils;
import com.store.common.pojo.StoreResult;
import com.store.common.utils.CookieUtils;
import com.store.mapper.TbUserMapper;
import com.store.pojo.TbUser;
import com.store.pojo.TbUserExample;
import com.store.pojo.TbUserExample.Criteria;
import com.store.sso.dao.JedisClient;
import com.store.sso.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private TbUserMapper userMapper;
	
	@Autowired
	private JedisClient jedisClient;

	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;
	
	@Value("${SSO_SESSION_EXPIRE}")
	private Integer SSO_SESSION_EXPIRE;
	
	@Override
	public StoreResult checkData(String content, Integer type) {
		//创建查询条件
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		//对数据进行校验123分别代表username，phone，Email
		if(1==type) {
			criteria.andUsernameEqualTo(content);
		}else if(2==type) {
			criteria.andPhoneEqualTo(content);
		}else {
			criteria.andEmailEqualTo(content);
		}
		//执行查询
		List<TbUser> list = userMapper.selectByExample(example);
		if(list.size()==0||list==null) {
			return StoreResult.ok(true);
		}
		return StoreResult.ok(false);
	}

	@Override
	public StoreResult createUser(TbUser user) {
		user.setUpdated(new Date());
		user.setCreated(new Date());
		//md5加密
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		userMapper.insert(user);
		return StoreResult.ok();
	}

	@Override
	public StoreResult userLogin(String username, String password,
			HttpServletRequest request, HttpServletResponse response) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> userList = userMapper.selectByExample(example);
		//没有此用户
		if(userList.size()==0||userList==null) {
			return StoreResult.build(400, "用户名或密码错误");
		}
		TbUser user = userList.get(0);
		//比对密码
		if(DigestUtils.md5Digest(password.getBytes()).equals(user.getPassword())) {
			return StoreResult.build(400, "用户名或密码错误");
		}
		//生成token使用uuid生成
		String tokenString = UUID.randomUUID().toString();
		//用户信息序列化,同时清空对象中的密码
		user.setPassword(null);
		String value = JsonUtils.objectToJson(user);
		//把用户信息写入redis
		jedisClient.set(REDIS_USER_SESSION_KEY+":"+tokenString, value);
		//设置session的过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY+":"+tokenString, SSO_SESSION_EXPIRE);
		//写入cookie,有效期 关闭浏览器失效
		CookieUtils.setCookie(request, response, "Store_Token", tokenString);
		//返回token
		return StoreResult.ok(tokenString);
	}

	@Override
	public StoreResult getUserByToken(String token) {
		//根据token从redis中查询用户信息
		String jsonString = jedisClient.get(REDIS_USER_SESSION_KEY+":"+token);
		//判断是否为空
		if(StringUtils.isBlank(jsonString)) {
			return StoreResult.build(400, "此session已过期，请重新登陆");
		}
		//更新过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY+":"+token, SSO_SESSION_EXPIRE);
		//返回用户信息
		return StoreResult.ok(JsonUtils.jsonToPojo(jsonString, TbUser.class));
	}

	@Override
	public StoreResult userLogout(String token) {
		jedisClient.expire(REDIS_USER_SESSION_KEY+":"+token, 0);
		return StoreResult.ok();
	}

	@Override
	public StoreResult updateUser(TbUser user,HttpServletRequest request) {
		//从cookie中获取登陆的token
		String tokenString = CookieUtils.getCookieValue(request, "Store_Token");
		StoreResult storeResult = getUserByToken(tokenString);
		if(storeResult.getStatus()==200) {
			TbUser user2 = (TbUser) storeResult.getData();
			user2.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
			user2.setUpdated(new Date());
			user2.setPhone(user.getPhone());
			user2.setUsername(user.getUsername());
			userMapper.updateByPrimaryKey(user2);
			return StoreResult.ok();
		}
		return StoreResult.build(400, "更新失败");
	}

	@Override
	public TbUser getUserInfo(HttpServletRequest request) {
		//从cookie中获取登陆的token
		String tokenString = CookieUtils.getCookieValue(request, "Store_Token");
		StoreResult storeResult = getUserByToken(tokenString);
		if(storeResult.getStatus()==200) {
			TbUser user2 = (TbUser) storeResult.getData();
			return user2;
		}
		return null;
	}
	

}
