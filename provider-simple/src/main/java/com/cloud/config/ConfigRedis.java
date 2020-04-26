package com.cloud.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class ConfigRedis {

	@Bean
	public StringRedisTemplate stringRedisTemplate(LettuceConnectionFactory factory) {
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

		StringRedisTemplate template = new StringRedisTemplate(factory);
		template.setEnableDefaultSerializer(true);
		template.setDefaultSerializer(stringRedisSerializer);

		template.setKeySerializer(stringRedisSerializer);
		template.setValueSerializer(stringRedisSerializer);
		template.setHashKeySerializer(stringRedisSerializer);
		template.setHashValueSerializer(stringRedisSerializer);

		template.afterPropertiesSet();
		return template;
	}

	@Bean
	public RedisTemplate<Object, Object> redisTemplate(LettuceConnectionFactory factory) {
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);

		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();

		template.setKeySerializer(stringRedisSerializer);
		template.setValueSerializer(fastJsonRedisSerializer);
		template.setHashKeySerializer(stringRedisSerializer);
		template.setHashValueSerializer(fastJsonRedisSerializer);

		template.afterPropertiesSet();
		return template;
	}

}
