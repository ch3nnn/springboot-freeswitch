# 说明文档

## inbound 代码示例
### pom依赖
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--
  ~ Licensed to the Apache Software Foundation (ASF) under one or more
  ~ contributor license agreements.  See the NOTICE file distributed with
  ~ this work for additional information regarding copyright ownership.
  ~ The ASF licenses this file to You under the Apache License, Version 2.0
  ~ (the "License"); you may not use this file except in compliance with
  ~ the License.  You may obtain a copy of the License at
  ~
  ~     http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  -->

<project xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://maven.apache.org/POM/4.0.0"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <artifactId>freeswitch-esl-all</artifactId>
        <groupId>link.thingscloud</groupId>
        <version>1.6.4.RELEASE</version>
    </parent>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>

        <netty.version>4.1.65.Final</netty.version>
        <spring-boot.version>2.3.1.RELEASE</spring-boot.version>

        <java.version>1.8</java.version>

        <!-- Compiler settings properties -->
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>

    <artifactId>freeswitch-esl-spring-boot-starter-example</artifactId>
    <name>freeswitch-esl-spring-boot-starter-example-${project.version}</name>

    <description>Example project for Freeswitch Esl Spring Boot Starter</description>

    <dependencies>
        <dependency>
            <groupId>${project.groupId}</groupId>
            <artifactId>freeswitch-esl-spring-boot-starter</artifactId>
            <version>${project.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>link.thingscloud</groupId>
            <artifactId>spring-boot-common-aop</artifactId>
            <version>1.0.0-RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.freeswitch.esl.client</groupId>
            <artifactId>org.freeswitch.esl.client</artifactId>
            <version>0.9.2</version>
        </dependency>

        <!--  JSON格式数据结构   -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.78</version>
        </dependency>

        <!--   redis     -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
            <version>2.2.6.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
            <version>2.6.2</version>
        </dependency>


        <!--Jackson包-->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
        </dependency>

    </dependencies>

</project>

```



## outbound示例

### 修改dialplan配置

出于演示目的，这里修改/usr/local/freeswitch/conf/dialplan/default.xml，在文件开头部分添加一段：

```
<extension name="socket_400_example">
      <condition field="destination_number" expression="^400\d+$">
            <action application="socket" data="localhost:8040 async full"/>
      </condition>
</extension>
```
即：当来电的被叫号码为400开头时，fs将利用socket，连接到localhost:8040


## 接口文档
### 一、机器人外呼发起

* 接口地址: /callcenter/api/startOutbound
* 请求方法: POST
* 请求参数:

* 返回数据:
```
{
code: 200,
message: "success"
data: null
}
message: 请求处理消息
code = 200  请求处理成功
code != 200 请求处理失败，警告消息提示：message内容
```



## 相关资料
[freeswitch笔记](https://www.cnblogs.com/yjmyzz/p/freeswitch-esl-java-client-turorial.html)

[github: freeswitch 事件套接字基于 netty 4 并具有一些新功能](https://github.com/zhouhailin/freeswitch-esl-all)
