#集成Oracle

1.查看Oracle版本号
select * from v$version;

2.将ojdbc.jar install到本地
mvn install:install-file -Dfile=ojdbc6.jar -DgroupId=oracle -DartifactId=ojdbc6 -Dversion=11.2.0.3.0 -Dpackaging=jar

3.添加maven依赖
```
<dependency>
	<groupId>oracle</groupId>
	<artifactId>ojdbc6</artifactId>
	<version>11.2.0.3.0</version>
</dependency>
```

4.参考配置
https://github.com/alibaba/druid/wiki/DruidDataSource%E9%85%8D%E7%BD%AE