package com.xyz.cloud.trace.redis;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.xyz.lang.constant.Constant;

@Service
public class StringRedisService {

	@Autowired
	private StringRedisTemplate redisTemplate;

	public String get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	public void set(String key, String value) {
		redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(Constant.cacheSec));
	}

}
