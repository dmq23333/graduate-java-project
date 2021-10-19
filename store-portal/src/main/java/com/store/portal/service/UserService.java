package com.store.portal.service;

import com.store.pojo.TbUser;

public interface UserService {
	TbUser getUserByToken(String token);
}
