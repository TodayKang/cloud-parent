package com.xyz.cloud.core.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.xyz.cloud.api.entity.ProductVO;

//Feign实现声明试 REST 调用
//@FeignClient(name = "provider-user", url = "http://10.43.2.139:8000/")
@FeignClient("product-eureka")
public interface FeignClientProduct {

	// 需要带上服务提供者的 context-path
	// 不用原生的默认契约,即没有配置 FeignClientConfig
	@GetMapping("/product-eureka/product/queryById/{productId}")
	ProductVO queryById(@PathVariable(name = "productId") Long productId);

}
