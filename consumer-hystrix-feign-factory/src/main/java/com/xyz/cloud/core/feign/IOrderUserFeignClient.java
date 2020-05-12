package com.xyz.cloud.core.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.xyz.cloud.api.entity.OrderUserVO;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

//Feign实现声明试 REST 调用
//@FeignClient(name = "provider-mysql", url = "http://10.43.2.139:8000/")
@FeignClient("provider-mysql")
public interface IOrderUserFeignClient {

	// 需要带上服务提供者的 context-path
	// 不用原生的默认契约,即没有配置 FeignClientConfig
	@GetMapping("/provider-mysql/order-user/queryById/{id}")
	OrderUserVO query(@PathVariable(name = "id") Long id);

	@PostMapping("/provider-mysql/order-user/query")
	List<OrderUserVO> query(@RequestBody Map<String, ?> map);

	@PostMapping("/provider-mysql/order-user/size")
	Long size(@RequestBody Map<String, ?> map);

}

/**
 * 该类需实现FallbackFactory接口，并覆写create方法
 */
@Slf4j
@Component
class OrderUserFeignClientFallbackFactory implements FallbackFactory<IOrderUserFeignClient> {
	@Override

	public IOrderUserFeignClient create(Throwable cause) {

		// 日志最好放在各个fallback方法中，而不要直接放在create方法中，否则在引用启动时，就会打印该日志
		// 详见https://github.com/spring-cloud/spring-cloud-netflix/issues/1471
		return new IOrderUserFeignClient() {

			@Override
			public Long size(Map<String, ?> map) {
				log.info("OrderUserFeignClientFallbackFactory.size error:{}", cause);
				return null;
			}

			@Override
			public List<OrderUserVO> query(Map<String, ?> map) {
				log.info("OrderUserFeignClientFallbackFactory.query error:{}", cause);
				return null;
			}

			@Override
			public OrderUserVO query(Long id) {
				log.info("OrderUserFeignClientFallbackFactory.query error:{}", cause);
				return null;
			}
		};
	}

}