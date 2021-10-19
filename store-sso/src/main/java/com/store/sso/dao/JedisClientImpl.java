package com.store.sso.dao;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisClientImpl implements JedisClient {
	@Autowired 
	private JedisPool jedisPool;

	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String valueString = jedis.get(key);
		jedis.close();
		return valueString;
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String resultString = jedis.set(key,value);
		jedis.close();
		return resultString;
	}

	@Override
	public String hget(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		String resultString = jedis.hget(hkey, key);
		jedis.close();
		return resultString;
	}

	@Override
	public Long hset(String hkey, String key, String value) {
		Jedis jedis = jedisPool.getResource();
		Long resultLong = jedis.hset(hkey, key, value);
		jedis.close();
		return resultLong;
	}

	@Override
	public long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	@Override
	public long expire(String key, long second) {
		Jedis jedis = jedisPool.getResource();
		long result = jedis.expire(key, (int)second);
		jedis.close();
		return result;
	}

	@Override
	public long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

}
