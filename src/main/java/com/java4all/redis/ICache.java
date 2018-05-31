package com.java4all.redis;

import java.util.Collection;

/**
 * 封装redisTemplate公用处理接口
 * @description 
 * @author 
 * @date 2018年4月19日 上午10:18:41
 */
public interface ICache extends IStringCache,ISetCache,IListCache,IHashCache{
	/**
	 * 删除键为key的缓存(hash/set/list/string)
	 * 
	 * @description
	 * @author 
	 * @date 2018年4月19日上午9:56:24
	 * @param key
	 */
	void deleteFromRedis(String key);
	
	/**
	 * 删除键为key的缓存(hash/set/list/string)
	 * @description 
	 * @author 
	 * @date 2018年4月19日上午10:22:09
	 * @param keys
	 */
	void deleteFromRedis(Collection<String> keys);
}
