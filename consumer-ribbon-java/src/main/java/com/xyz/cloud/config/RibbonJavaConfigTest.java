package com.xyz.cloud.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

import com.xyz.ribbon.ConfigRibbon;

/**
 * 使用RibbonClient 为特定name的Ribbon Client自定义配置 <br>
 * 使用 @RibbonClient 的 configuration 属性，指定Ribbon的配置类<br>
 * 可参考的示例: http://spring.io/guides/gs/client-side-load-balancing/
 */
@Configuration
@RibbonClient(name = "provider-mysql", configuration = ConfigRibbon.class)
public class RibbonJavaConfigTest {

}
