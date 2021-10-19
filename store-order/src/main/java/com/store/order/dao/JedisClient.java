package com.store.order.dao;

public interface JedisClient {
	String get(String key);
	String set(String key, String value);
	String hget(String hkey, String key);
	//将哈希表 hkey 中的域 key 的值设为 value 。
	Long hset(String hkey, String key, String value);
	//将 key 中储存的数字值增一
	long incr(String key);
	//为给定 key 设置生存时间(s)，当 key 过期时(生存时间为 0 )，它会被自动删除。
	long expire(String key, long second);
	//查看过期时间
	long ttl(String key);
}
