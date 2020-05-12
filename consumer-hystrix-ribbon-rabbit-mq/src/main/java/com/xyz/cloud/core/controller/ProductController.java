package com.xyz.cloud.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xyz.cloud.api.entity.ProductVO;

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
	@HystrixCommand(fallbackMethod = "queryFallback", commandProperties = {
			// 超时时间
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
			// 设置滚动时间窗的长度
			@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "30000") })
	@GetMapping("/queryById/{productId}")
	public ProductVO getById(@PathVariable(name = "productId") Long productId) {
		return restTemplate.getForObject("http://product-eureka/product/queryById/" + productId, ProductVO.class);
	}

	public ProductVO queryFallback(Long id) {
		log.info("queryFallback:id={}", id);
		return new ProductVO();
	}

	@GetMapping("/provider-instance")
	public void logUserInstance() {
		ServiceInstance serviceInstance = loadBalancerClient.choose("product-eureka");
		// 打印当前选择的是哪个节点
		log.info("当前节点信息:{}", JSON.toJSONString(serviceInstance));
	}

}
