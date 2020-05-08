package com.xyz.cloud.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.xyz.cloud.api.entity.ProductVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(tags = { "consumer产品信息相关接口" })
@RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@ApiOperation(value = "根据产品ID查询")
	@GetMapping("/queryById/{productId}")
	public ProductVO getById(@PathVariable(name = "productId") Long productId) {
		return restTemplate.getForObject("http://provider-eureka/product/queryById/" + productId, ProductVO.class);
	}

	@ApiOperation(value = "根据服务ID获取服务信息")
	@GetMapping("/instance/{serviceId}")
	public ServiceInstance logUserInstance(@PathVariable String serviceId) {
		ServiceInstance instance = loadBalancerClient.choose(serviceId);
		// 返回当前选择的是哪个节点
		log.info("当前选择调用服务的节点:{}", instance);
		return instance;
	}

}
