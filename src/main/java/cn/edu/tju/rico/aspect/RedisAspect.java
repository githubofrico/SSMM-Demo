package cn.edu.tju.rico.aspect;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import cn.edu.tju.rico.cache.RedisCache;

/**
 * Title: Redis切面
 * Description:在查询数据库前先查询Redis，若查询失败，则穿透到数据库，并将从数据库查询到的数据保存至Redis，
 * 然后下次查询可直接命中Redis缓存.
 * 
 * @author rico
 * @created 2017年6月27日 上午11:04:26
 */
@Component
@Aspect
public class RedisAspect {

	private RedisCache redisCache;

	public RedisCache getRedisCache() {
		return redisCache;
	}

	@Resource(name = "redisCache")
	public void setRedisCache(RedisCache redisCache) {
		this.redisCache = redisCache;
	}

	@Around("execution(* cn.edu.tju.rico.service.impl.UserServiceImpl.getUser(..))")
	public Object around(ProceedingJoinPoint joinPoint) {

		System.out.println("先从Redis中查询数据...");

		// 获取目标方法参数
		int id = 0;
		Object[] args = joinPoint.getArgs();
		if (args != null && args.length > 0) {
			id = (Integer) args[0];
		}

		// Redis中Key的格式
		String redisKey = String.valueOf(id);

		// 前置：先到redis中查询缓存
		Object objectFromRedis = redisCache.getDataFromFromRedis(redisKey);
		if (null != objectFromRedis) { // 缓存命中
			System.out.println("从Redis中查询到了数据，不需要查询数据库...");
			return objectFromRedis;
		}

		// 缓存未命中
		System.out.println("没有从Redis中查到数据...");

		// 后置：从数据库中查询并将查询结果存入redis中
		Object object = null;
		try {
			object = joinPoint.proceed();   // 执行目标方法
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("从数据库中查询到数据...");
		System.out.println("将数据库查询结果存储到Redis中...");
		redisCache.setDatatoRedis(redisKey, object);

		return object;
	}
}
