package com.lizza.lock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.security.RunAs;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistributedLockApplicationTests {

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void testString() {
		redisTemplate.opsForValue().set("name", "lizza");
		System.out.println(redisTemplate.opsForValue().get("name"));
	}

}
