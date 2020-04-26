package com.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ConfigSwagger {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)//
				.apiInfo(apiInfo())//
				.select()//
				// 为哪个包下生成接口文档
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework")))
				// 为有@Api注解的Controller生成API文档
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
				// 为有@ApiOperation注解的方法生成API文档
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))//
				.paths(PathSelectors.any())//
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()//
				.title("SwaggerUI 测试 RESTFUL API")// 页面左上角大标题
				.description("API 接口测试")// 描述
				.contact(new Contact("developer", null, "todaykang@163.com"))// 作者
				.version("1.0.0.RELEASE")// 版本号
				.build();
	}

}
