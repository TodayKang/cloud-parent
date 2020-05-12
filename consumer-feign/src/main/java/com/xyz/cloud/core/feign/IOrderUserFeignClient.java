package com.xyz.cloud.core.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.xyz.cloud.api.entity.OrderUserVO;

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
