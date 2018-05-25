package com.java4all;

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

	@RequestMapping(value = "test",method = RequestMethod.GET)
	public String test(){
		return "测试入口";
	}
}
