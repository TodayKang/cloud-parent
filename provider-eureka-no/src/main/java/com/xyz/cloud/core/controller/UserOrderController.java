package com.xyz.cloud.core.controller;

import java.util.List;
import java.util.Map;

import com.xyz.cloud.api.entity.OrderUserVO;
import com.xyz.cloud.api.service.IOrderUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags = { "用户订单接口" })
@RequestMapping(value = "/order-user")
public class UserOrderController {

	@Autowired
	private IOrderUserService orderUserService;

	@ApiOperation(value = "保存用户订单")
	@PostMapping(value = "/save")
	public OrderUserVO save(@ApiParam(required = true) @RequestBody OrderUserVO arg) {
		return orderUserService.save(arg);
	}

	@ApiOperation(value = "删除用户订单")
	@PostMapping(value = "/delete")
	public Boolean delete(@ApiParam(required = true) @RequestBody Map<String, Object> map) {
		return orderUserService.delete(map);
	}

	@ApiOperation(value = "修改用户订单")
	@PostMapping(value = "/update")
	public OrderUserVO update(@ApiParam(required = true) @RequestBody OrderUserVO arg) {
		return orderUserService.update(arg);
	}

	@ApiOperation(value = "根据主键查询用户订单")
	@GetMapping(value = "/queryById/{id}")
	public OrderUserVO query(@PathVariable("id") Long id) {
		return orderUserService.query(id);
	}

	@ApiOperation(value = "查询用户订单列表")
	@PostMapping(value = "/query")
	public List<OrderUserVO> query(@ApiParam(value = "查询对象") @RequestBody Map<String, Object> map) {
		return orderUserService.query(map);
	}

	@ApiOperation(value = "查询用户订单总数")
	@PostMapping(value = "/size")
	public Long size(@ApiParam(value = "查询对象") @RequestBody Map<String, Object> map) {
		return orderUserService.size(map);
	}

}
