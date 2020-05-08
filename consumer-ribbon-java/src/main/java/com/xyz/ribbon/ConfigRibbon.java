package com.xyz.ribbon;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;

/**
 * Ribbon的配置类,该类不应该在主应用程序上下文的 @ComponentScan 中<br>
 * 也可以在 application.properties中使用属性自定义 Ribbon 配置。<br>
 * 使用属性自定义 Ribbon 配置修改负载均衡规则：<br>
 * provider-eureka.ribbon.NFLoadBalancerRuleClassName: com.netflix.loadbalancer.BestAvailableRule<br>
 */
@Configuration
public class ConfigRibbon {

	@Bean
	public IRule ribbonRule() {
		// 负载均衡策略
		return new BestAvailableRule();
	}

}
