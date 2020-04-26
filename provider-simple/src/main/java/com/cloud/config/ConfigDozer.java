package com.cloud.config;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 为更好的体现 Dozer 的特性，现阶段必须以 XML API 二者结合的方式来编写测试用例<br>
 * https://cloud.tencent.com/developer/article/1458161
 */
@Configuration
public class ConfigDozer {

    @Bean
    public Mapper dozerMapper() {
        Mapper mapper = DozerBeanMapperBuilder.create()//
                //指定 dozer mapping 的配置文件(放到 resources 类路径下即可)，可添加多个 xml 文件，用逗号隔开
                .withMappingFiles("dozer-mapper.xml")//
                .withMappingBuilder(beanMappingBuilder())//
                .build();
        return mapper;
    }

    @Bean
    public BeanMappingBuilder beanMappingBuilder() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                // 个性化配置添加在此
            }
        };
    }

}
