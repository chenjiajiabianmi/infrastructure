package com.vanceinfo.javaserial.services;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.vanceinfo.javaserial.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml","classpath:spring-mvc.xml" })
public class UserServiceImplTest {
	private static final Logger logger = Logger.getLogger(UserServiceImplTest.class);
	
	private IUserService userService;

	public IUserService getUserSerivce() {
		return userService;
	}

	@Autowired
	public void setUserSerivce(IUserService userSerivce) {
		this.userService = userSerivce;
	}
	
	@Test
	public void getUserByIdtest() {
		User u = userService.getUserById(1,false);
		logger.info(JSON.toJSONStringWithDateFormat(u, "yyyy-MM-dd HH:mm:ss"));
	}
}
