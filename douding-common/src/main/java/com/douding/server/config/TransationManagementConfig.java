package com.douding.server.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
    @EnableTransactionManagement 自动开启数据库事务
 */

@EnableTransactionManagement
@Configuration
public class TransationManagementConfig {
}
