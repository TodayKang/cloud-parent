package com.xyz.cloud.core.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xyz.cloud.api.entity.OrderUserVO;
import com.xyz.cloud.core.feign.IOrderUserFeignClient;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "服务消费者-用户订单" })
@RequestMapping(value = "/order-user")
public class OrderUserController {

	@Autowired
	private IOrderUserFeignClient orderUserFeignClient;

	@ApiOperation(value = "根据产品ID查询")
	@GetMapping("/queryById/{productId}")
	public OrderUserVO query(@PathVariable(name = "id") Long id) {
		return orderUserFeignClient.query(id);
	}

	@ApiOperation(value = "查询用户订单列表")
	@PostMapping(value = "/query")
	public List<OrderUserVO> query(@RequestBody Map<String, Object> map) {
		return orderUserFeignClient.query(map);
	}

	@ApiOperation(value = "查询用户订单总数")
	@PostMapping(value = "/size")
	public Long size(@RequestBody Map<String, Object> map) {
		return orderUserFeignClient.size(map);
	}

}
