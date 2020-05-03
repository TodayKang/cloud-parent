#Spring Cloud文档

项目以maven构建，全部基于开源框架。

##项目打包编译
```
//忽略测试编译
mvn clean package -Dmaven.test.skip=true

//指定环境编译
mvn clean package -Dmaven.test.skip=true -P prd
//指定环境编译，需要在pom.xml配置
<profiles>
    <profile>
        <id>dev</id>
        <activation>
            <!-- active default -->
            <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
            <spring.profiles.active>dev</spring.profiles.active>
        </properties>
    </profile>
    <profile>
        <id>prd</id>
        <properties>
            <spring.profiles.active>prd</spring.profiles.active>
        </properties>
    </profile>
</profiles>
```

##项目运行方式
1.通过maven的方式运行
```
//若pom.xml没有配置profiles
mvn clean spring-boot:run

//若pom.xml配置profiles
mvn clean spring-boot:run -Pprd
```

2.通过application.yml配置方式运行
```
//在spring.profiles配置属性，如:spring.profiles: node1
mvn clean spring-boot:run -Dspring-boot.run.profiles=node1
mvn clean spring-boot:run -Dspring-boot.run.profiles=node2
mvn clean spring-boot:run -Dspring-boot.run.profiles=node3
```

3.通过.jar方式运行
```
java -jar xxxx.jar
java -jar xxxx.jar -–spring.profiles.active=prd
java -jar eureka-nodes.jar --spring.profiles.active=node1
java -jar eureka-nodes.jar --spring.profiles.active=node2
java -jar eureka-nodes.jar --spring.profiles.active=node3


