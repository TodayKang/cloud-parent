package com.xyz.cloud.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.xyz.cloud.api.entity.CustomerAddressVO;

@RestController
@RequestMapping(value = "/customer-address", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerAddressController {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${service.url}")
	private String url;

	@GetMapping("/get/{addressId}")
	public CustomerAddressVO getById(@PathVariable(name = "addressId") Long id) {
		return restTemplate.getForObject(url + "/customer-address/" + id, CustomerAddressVO.class);
	}

}
