package com.xyz.cloud.core.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "使用Spring Cloud Config统一管理微服务配置" })
@RequestMapping(value = "/config")
public class CloudConfigController {

	@Value("${profile}")
	private String profile;

	@ApiOperation(value = "根据产品ID查询")
	@GetMapping("/get")
	public String getProfile() {
		return profile;
	}

}
