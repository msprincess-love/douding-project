package com.douding.system;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.douding")
@MapperScan("com.douding.server.mapper")
public class SystemApplication {

//	1.添加logback.xml 后加载log日志
	private static final Logger LOG = LoggerFactory.getLogger(SystemApplication.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SystemApplication.class);
		Environment env = app.run(args).getEnvironment();
		LOG.info("启动成功！！");
		LOG.info("System: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
	}

}
