package com.xyz.cloud.core.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "Eureka Service服务" })
@RequestMapping(value = "/eureka-server")
public class EurekaServerController {

	@Autowired
	private DiscoveryClient discoveryClient;

	@ApiOperation(value = "根据Service ID获取实例信息")
	@GetMapping("/serviceId/{serviceId}")
	public List<ServiceInstance> getInstances(@PathVariable(name = "serviceId") String serviceId) {
		return discoveryClient.getInstances(StringUtils.trimToNull(serviceId));
	}

	@ApiOperation(value = "获取所有实例的Service ID")
	@GetMapping("/services")
	public List<String> getServices() {
		return discoveryClient.getServices();
	}

}
