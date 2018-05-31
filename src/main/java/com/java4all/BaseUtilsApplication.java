package com.java4all;

import com.java4all.redis.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BaseUtilsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseUtilsApplication.class, args);
	}

	@Autowired
	private Cache cache;

	@RequestMapping(value = "test",method = RequestMethod.GET)
	public String test(){
		return "测试入口";
	}

	@RequestMapping(value = "redisAdd",method = RequestMethod.GET)
	public void redisAddName(String name){
		System.out.println(name);
		cache.stringSetValueAndExpireTime("name",name,20*1000);
	}
}
