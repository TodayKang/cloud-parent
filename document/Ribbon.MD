#使用Ribbon实现客户端侧负载均衡

1.服务消费者基于RestTemplate整合Ribbon
@LoadBalanced注解可以为RestTemplate整合Ribbon，使其具备负载均衡的能力。
```
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigApplication {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
```

2.服务消费者基于Java自定义代码整合Ribbon
```
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

/**
 * 使用RibbonClient 为特定name的Ribbon Client自定义配置 <br>
 * 使用 @RibbonClient 的 configuration 属性，指定Ribbon的配置类<br>
 * 可参考的示例: http://spring.io/guides/gs/client-side-load-balancing/
 */
@Configuration
@RibbonClient(name = "provider-eureka", configuration = ConfigRibbon.class)
public class RibbonJavaConfigTest {

}
```

3.服务消费者基于属性自定义整合Ribbon
```
#支持的属性如下，配置的前级是<clientName>.ribbon:
#NFLOadBalancerClassName:配置ILoadBalancer的实现类
#NFLOadBaLancerRuleCLasslame:配置IRule的实现类
#NFLoadBatancerPingClaslame:配置Iping的实现热
#NIMSServerListClassName:配置 Serverlist的实现类
#NIMSServerListFilterClassName:配置ServerListFilter的实现类
provider-eureka:
  ribbon:
    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.BestAvailableRule
```

