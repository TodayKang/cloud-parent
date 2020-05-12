package com.xyz.cloud.core.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xyz.cloud.api.entity.OrderUserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(tags = { "服务消费者-用户订单" })
@RequestMapping(value = "/order-user")
public class OrderUserController {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${service.url1}")
	private String url;

	@ApiOperation(value = "根据产品ID查询")
	@HystrixCommand(fallbackMethod = "fallbackQueryById", commandProperties = {
			// 超时时间
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
			// 设置滚动时间窗的长度
			@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "30000") })
	@GetMapping("/queryById/{id}")
	public OrderUserVO query(@PathVariable(name = "id") Long id) {
		return restTemplate.getForObject(url + "/order-user/queryById/" + id, OrderUserVO.class);
	}

	@ApiOperation(value = "查询用户订单列表")
	@HystrixCommand(fallbackMethod = "fallbackQuery", commandProperties = {
			// 超时时间
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
			// 设置滚动时间窗的长度
			@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "30000") })
	@PostMapping(value = "/query")
	public List<OrderUserVO> query(@RequestBody Map<String, Object> map) {
		OrderUserVO[] list = restTemplate.postForObject(url + "/order-user/query", map, OrderUserVO[].class);
		return new ArrayList<>(Arrays.asList(list));
	}

	@ApiOperation(value = "查询用户订单总数")
	@HystrixCommand(fallbackMethod = "fallbackQuerySize", commandProperties = {
			// 超时时间
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
			// 设置滚动时间窗的长度
			@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "30000") })
	@PostMapping(value = "/size")
	public Long size(@RequestBody Map<String, Object> map) {
		return restTemplate.postForObject(url + "/order-user/size", map, Long.class);
	}

	public OrderUserVO fallbackQueryById(Long id) {
		log.info("fallbackQuery:req={}", id);
		return new OrderUserVO();
	}

	public List<OrderUserVO> fallbackQuery(Map<String, Object> map) {
		log.info("fallbackQuery:req={}", map);
		return null;
	}

	public Long fallbackQuerySize(Map<String, Object> map) {
		log.info("fallbackQuerySize:req={}", map);
		return null;
	}

}
