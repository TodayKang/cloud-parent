package com.xyz.cloud.redis;

import java.time.Duration;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.xyz.lang.constant.Constant;

@Service
public class RedisService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public Object get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	public void set(String key, Object value) {
		redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(Constant.cacheSec));
	}

	public void set(String key, Object value, Long seconds) {
		redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(seconds));
	}

	public Boolean delete(String key) {
		return redisTemplate.delete(key);
	}

	public Long deleteLike(String pattern) {
		Set<String> keys = redisTemplate.keys(pattern);
		if (CollectionUtils.isNotEmpty(keys)) {
			return redisTemplate.delete(keys);
		}

		return null;
	}

}
