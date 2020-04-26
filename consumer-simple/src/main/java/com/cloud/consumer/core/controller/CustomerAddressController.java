package com.cloud.consumer.core.controller;

import com.cloud.api.entity.CustomerAddressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
