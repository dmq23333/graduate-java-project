/*
 * package com.store.rest.jedis;
 * 
 * import org.junit.Test; import org.springframework.context.ApplicationContext;
 * import org.springframework.context.support.ClassPathXmlApplicationContext;
 * 
 * import redis.clients.jedis.Jedis; import redis.clients.jedis.JedisPool;
 * import redis.clients.jedis.JedisPoolConfig;
 * 
 * public class JedisTest {
 * 
 * @Test public void testJedis(){ //创建一个jedis对象 Jedis jedis = new
 * Jedis("192.168.146.128",6379); jedis.auth("123456");
 * //直接调用jedis对象的方法,方法名称和redis命令一致 jedis.set("key1", "test"); String string =
 * jedis.get("key1"); System.out.println(string); //关闭jedis jedis.close(); }
 * //使用连接池
 * 
 * @Test public void testJedisPool() { //创建一个连接池对象 JedisPoolConfig config = new
 * JedisPoolConfig(); config.setMaxTotal(50);
 * //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。 config.setMaxIdle(5);
 * //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；单位毫秒
 * //小于零:阻塞不确定的时间, 默认-1 config.setMaxWaitMillis(1000*100);
 * //在borrow(引入)一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
 * config.setTestOnBorrow(true); //return 一个jedis实例给pool时，是否检查连接可用性（ping()）
 * config.setTestOnReturn(true); //connectionTimeout 连接超时（默认2000ms） //soTimeout
 * 响应超时（默认2000ms） JedisPool jedisPool = new
 * JedisPool(config,"192.168.146.128",6379,2000,"123456"); //从连接池获得jedis对象 Jedis
 * jedis = jedisPool.getResource(); String string = jedis.get("key1");
 * System.out.println(string); //关闭jedis对象 jedis.close(); jedisPool.close(); }
 * 
 * 
 * @Test public void testSpringJedisSingle() { ApplicationContext
 * applicationContext = new
 * ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
 * JedisPool pool = (JedisPool) applicationContext.getBean("redisClient"); Jedis
 * jedis = pool.getResource(); String string = jedis.get("key1");
 * System.out.println(string); jedis.close(); pool.close(); }
 * 
 * 
 * }
 */
