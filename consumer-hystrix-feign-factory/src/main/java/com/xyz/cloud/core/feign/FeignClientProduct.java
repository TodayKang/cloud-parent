package com.xyz.cloud.core.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.xyz.cloud.api.entity.ProductVO;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

//Feign实现声明试 REST 调用
//@FeignClient(name = "provider-user", url = "http://10.43.2.139:8000/")
@FeignClient(name = "provider-eureka", fallback = FeignClientFallbackFactory.class)
public interface FeignClientProduct {

	// 需要带上服务提供者的 context-path
	// 不用原生的默认契约,即没有配置 FeignClientConfig
	@GetMapping("/provider-eureka/product/queryById/{productId}")
	ProductVO queryById(@PathVariable(name = "productId") Long productId);

}

/**
 * 该类需实现FallbackFactory接口，并覆写create方法
 */
@Slf4j
@Component
class FeignClientFallbackFactory implements FallbackFactory<FeignClientProduct> {

	@Override
	public FeignClientProduct create(Throwable cause) {
		return new FeignClientProduct() {

			@Override
			public ProductVO queryById(Long productId) {
				// 日志最好放在各个fallback方法中，而不要直接放在create方法中，否则在引用启动时，就会打印该日志
				// 详见https://github.com/spring-cloud/spring-cloud-netflix/issues/1471
				log.info("FeignClientProduct.queryById error:{}", cause);
				log.info("queryFallback:id={}", productId);
				return new ProductVO();
			}
		};
	}

}