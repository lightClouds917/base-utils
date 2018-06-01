package com.java4all.redis;

/**
 * 封装redisTemplate对字符串处理接口
 * @description 
 * @author 
 * @date 2018年4月19日 上午10:15:30
 */
public interface IStringCache {

	/**
	 * 如果key已经存在且是一个字符串,APPEND命令将value追加到原来的值的末尾. 如果key不存在,APPEND就简单的将给定key设置为value
	 * 
	 * @description
	 * @author 
	 * @date 2018年4月19日上午10:11:28
	 * @param key
	 * @param value
	 * @return 追加value后,字符串的长度
	 */
	Integer stringAppendString(String key, String value);

	/**
	 * 获取指定键的值
	 * 
	 * @description
	 * @author 
	 * @date 2018年4月19日上午10:12:35
	 * @param key
	 * @return
	 */
	String stringGetStringByKey(String key);

	/**
	 * 获取存储在键上的字符串的子字符串
	 * 
	 * @description
	 * @author 
	 * @date 2018年4月19日上午10:12:43
	 * @param key
	 * @param start
	 * @param end
	 * @return 截取后的字符串
	 */
	String stringGetSubStringFromString(String key, long start, long end);

	/**
	 * 将键的整数值按给定的长整型数值增加
	 * 
	 * @description
	 * @author 
	 * @date 2018年4月19日上午10:13:03
	 * @param key
	 * @param delta
	 * @return 增长后的结果集
	 */
	Long stringIncrementLongString(String key, Long delta);

	/**
	 * 键的整数值按给定的浮点型数值增加
	 * 
	 * @description
	 * @author 
	 * @date 2018年4月19日上午10:13:32
	 * @param key
	 * @param delta
	 * @return 增长后的结果集
	 */
	Double stringIncrementDoubleString(String key, Double delta);

	/**
	 * 设置指定键的值
	 * 
	 * @description
	 * @author 
	 * @date 2018年4月19日上午10:13:53
	 * @param key
	 * @param value
	 */
	void stringSetString(String key, String value);

	/**
	 * 设置键的字符串值并返回其旧值
	 * 
	 * @description
	 * @author 
	 * @date 2018年4月19日上午10:14:03
	 * @param key
	 * @param value
	 * @return
	 */
	String stringGetAndSet(String key, String value);

	/**
	 * 使用键和到期时间来设置值,时间单位默认为毫秒
	 * 
	 * @description
	 * @author 
	 * @date 2018年4月19日上午10:14:22
	 * @param key
	 * @param value
	 * @param timeout
	 */
	void stringSetValueAndExpireTime(String key, String value, long timeout);
}
