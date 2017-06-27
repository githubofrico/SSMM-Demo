package cn.edu.tju.rico.cache;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.edu.tju.rico.util.SerializeUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
  
/**        
 * Title: Redis 缓存存取    
 * Description: 将数据保存到Redis或者将数据从Redis取出
 * @author rico       
 * @created 2017年6月27日 上午11:09:19    
 */      
@Component
public class RedisCache {

	private JedisPool jedisPool;

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	@Resource(name = "jedisPool")
	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	/**     
	 * @description 从Redis缓存中查询，反序列化
	 * @author rico       
	 * @created 2017年6月27日 上午9:33:14     
	 * @param redisKey
	 * @return     
	 */
	public Object getDataFromFromRedis(String redisKey){
		
		// 查询
		Jedis jedis = jedisPool.getResource();
		byte[] result = jedis.get(redisKey.getBytes());
		// 查询为空，Redis中不包含目标数据
		if (null == result) {
			return null;
		}
		
		// 查询到结果，反序列化
		return SerializeUtil.unSerialize(result);
	}
	
	  
	/**     
	 * @description 将数据库的查询结果放入Redis,序列化
	 * @author rico       
	 * @created 2017年6月27日 上午9:32:36     
	 * @param redisKey
	 * @param obj     
	 */
	public void setDatatoRedis(String redisKey, Object obj){
		
		// 序列化
		byte[] bytes = SerializeUtil.serialize(obj);
		
		// 存入Redis
		Jedis jedis = jedisPool.getResource();
		String success = jedis.set(redisKey.getBytes(), bytes);
		
		if ("OK".equals(success)) {
			System.out.println("数据成功保存到缓存Redis...");
		}
	}
}
