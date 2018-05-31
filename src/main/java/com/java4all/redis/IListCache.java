package com.java4all.redis;

import java.util.List;

/**
 * 封装redisTemplate对list处理接口
 * @description 
 * @author 
 * @date 2018年4月19日 上午10:16:52
 */
public interface IListCache {
	/**
	 * 从右向左存压栈
	 * @description 
	 * @author 
	 * @date 2018年4月19日上午10:09:06
	 * @param key
	 * @param value
	 */
	void listRightPushList(String key, String value);

	/**
	 * 从右出栈
	 * @description 
	 * @author 
	 * @date 2018年4月19日上午10:09:21
	 * @param key
	 * @return
	 */
	String listRightPopList(String key);

	/**
	 * 从左向右存压栈
	 * @description 
	 * @author 
	 * @date 2018年4月19日上午10:09:28
	 * @param key
	 * @param value
	 */
	void listLeftPushList(String key, String value);

	/**
	 * 从左出栈
	 * @description 
	 * @author 
	 * @date 2018年4月19日上午10:09:35
	 * @param key
	 * @return
	 */
	String listLeftPopList(String key);

	/**
	 * 集合list的长度
	 * @description 
	 * @author 
	 * @date 2018年4月19日上午10:09:43
	 * @param key
	 * @return
	 */
	Long listSize(String key);

	/**
	 * 查询列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定。
	 * @description 
	 * @author 
	 * @date 2018年4月19日上午10:09:49
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	List<String> listRangeList(String key, Long start, Long end);

	/**
	 * 移除key中值为value的i个,返回删除的个数；如果没有这个元素则返回0
	 * @description 
	 * @author 
	 * @date 2018年4月19日上午10:09:59
	 * @param key
	 * @param i
	 * @param value
	 * @return
	 */
	Long listRemoveFromList(String key, long i, Object value);

	/**
	 * 根据下标查询list中某个值
	 * @description 
	 * @author 
	 * @date 2018年4月19日上午10:10:05
	 * @param key
	 * @param index
	 * @return
	 */
	String listIndexFromList(String key, long index);

	/**
	 * 根据下标设置value
	 * @description 
	 * @author 
	 * @date 2018年4月19日上午10:10:20
	 * @param key
	 * @param index
	 * @param value
	 */
	void listSetValueToList(String key, long index, String value);

	/**
	 * 裁剪(删除), 删除 除了[start,end]以外的所有元素
	 * @description 
	 * @author 
	 * @param key
	 * @param start
	 * @param end
	 */
	void listTrimByRange(String key, Long start, Long end);
}
