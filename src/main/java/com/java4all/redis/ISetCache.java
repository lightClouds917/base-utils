package com.java4all.redis;

import java.util.Set;

/**
 * 封装redisTemplate对set处理接口
 * @description 
 * @author 
 * @date 2018年4月19日 上午10:16:13
 */
public interface ISetCache {
	/**
	 * 将一个或多个 value 元素加入到集合 key 当中，已经存在于集合的 value 元素将被忽略。
	 * 
	 * @description
	 * @author 
	 * @date 2018年4月19日上午10:10:39
	 * @param key
	 * @param values
	 * @return 被添加到集合中的新元素的数量，不包括被忽略的元素
	 */
	Long setAddSetMap(String key, String... values);

	/**
	 * 获取set集合的大小
	 * 
	 * @description
	 * @author 
	 * @date 2018年4月19日上午10:11:00
	 * @param key
	 * @return
	 */
	Long setGetSizeForSetMap(String key);

	/**
	 * 获取set集合中的元素
	 * 
	 * @description
	 * @author 
	 * @date 2018年4月19日上午10:11:06
	 * @param key
	 * @return
	 */
	Set<String> setGetMemberOfSetMap(String key);

	/**
	 * 检查元素是不是set集合中的
	 * 
	 * @description
	 * @author 
	 * @date 2018年4月19日上午10:11:12
	 * @param key
	 * @param o
	 * @return
	 */
	boolean setCheckIsMemberOfSet(String key, Object o);
}
