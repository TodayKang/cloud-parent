package com.xyz.cloud.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.xyz.cloud.api.entity.ProductVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "consumer产品信息相关接口" })
@RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${service.url1}")
	private String url;

	@ApiOperation(value = "根据产品ID查询")
	@GetMapping("/queryById/{productId}")
	public ProductVO getById(@PathVariable(name = "productId") Long productId) {
		return restTemplate.getForObject(url + "/product/queryById/" + productId, ProductVO.class);
	}

}
