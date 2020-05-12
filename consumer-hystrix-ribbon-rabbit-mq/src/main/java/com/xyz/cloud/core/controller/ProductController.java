package com.xyz.cloud.core.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xyz.cloud.api.entity.OrderUserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(tags = { "consumer产品信息相关接口" })
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@Autowired
	private RestTemplate restTemplate;

	@ApiOperation(value = "根据产品ID查询")
	@HystrixCommand(fallbackMethod = "fallbackQueryById", commandProperties = {
			// 超时时间
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
			// 设置滚动时间窗的长度
			@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "30000") })
	@GetMapping("/queryById/{id}")
	public OrderUserVO query(@PathVariable(name = "id") Long id) {
		return restTemplate.getForObject("http://product-mysql/product/queryById/" + id, OrderUserVO.class);
	}

	@ApiOperation(value = "查询用户订单列表")
	@HystrixCommand(fallbackMethod = "fallbackQuery", commandProperties = {
			// 超时时间
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
			// 设置滚动时间窗的长度
			@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "30000") })
	@PostMapping(value = "/query")
	public List<OrderUserVO> query(@RequestBody Map<String, Object> map) {
		OrderUserVO[] list = restTemplate.postForObject("http://provider-mysql/order-user/query", map, OrderUserVO[].class);
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
		return restTemplate.postForObject("http://provider-mysql/order-user/size", map, Long.class);
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

	@GetMapping("/provider-instance")
	public void logUserInstance() {
		ServiceInstance serviceInstance = loadBalancerClient.choose("product-eureka");
		// 打印当前选择的是哪个节点
		log.info("当前节点信息:{}", JSON.toJSONString(serviceInstance));
	}

}
