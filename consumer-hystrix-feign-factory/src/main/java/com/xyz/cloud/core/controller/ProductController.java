package com.xyz.cloud.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.xyz.cloud.api.entity.ProductVO;
import com.xyz.cloud.core.feign.FeignClientProduct;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "consumer产品信息相关接口" })
@RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

	@Autowired
	private FeignClientProduct feignClientProduct;

	@ApiOperation(value = "根据产品ID查询")
	@GetMapping("/queryById/{productId}")
	public ProductVO getById(@PathVariable(name = "productId") Long productId) {
		return feignClientProduct.queryById(productId);
	}

}
