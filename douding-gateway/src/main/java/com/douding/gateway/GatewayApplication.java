package com.douding.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.Collections;


@SpringBootApplication
@EnableEurekaClient
public class GatewayApplication {

//	1.添加logback.xml 后加载log日志
	private static final Logger LOG = LoggerFactory.getLogger(GatewayApplication.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(GatewayApplication.class);
		Environment env = app.run(args).getEnvironment();
		LOG.info("启动成功！！");
		LOG.info("Gateway: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
	}



	@Bean
	public CorsWebFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		//1,允许任何来源
		corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
		//2,允许任何请求头
		corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
		//3,允许任何方法(get,post....)
		corsConfiguration.addAllowedMethod("*");
		//4,允许凭证
		corsConfiguration.setAllowCredentials(true);

        /* 在geteway application.properties中 使用uri=lb://business 时出现了跨域报错
            添加上下面这句 后报错没有了
            真奇怪 写 * 就能解决问题  写CorsConfiguration.ALL就报错
        * */
//		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedOriginPattern("*");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsWebFilter(source);
	}



}
