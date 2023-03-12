package com.douding.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Component
public class LoginAdminGatewayFilter implements GatewayFilter, Ordered {

    private static final Logger LOG = LoggerFactory.getLogger(LoginAdminGatewayFilter.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();

        //验证是否正确拦截请求 获得token
        /*
        String token = exchange.getRequest().getHeaders().getFirst("token");
        LOG.info("登录拦截开始,token:{}",token);
        return chain.filter(exchange);
        */

        // 请求地址中不包含/admin/的，不是控台请求，不需要拦截
        if (!path.contains("/admin/")) {
            return chain.filter(exchange);
        }
        //如果是登录 退出,获取验证码的请求 也不需要拦截
        if (path.contains("/system/admin/user/login")
                || path.contains("/system/admin/user/logout")
                || path.contains("/system/admin/kaptcha")) {
            LOG.info("不需要控台登录验证的请求：{}", path);
            return chain.filter(exchange);
        }

        //获取header的token参数,如果没有token 不在允许登录退出以外的其他操作
        String token = exchange.getRequest().getHeaders().getFirst("token");
        LOG.info("控台登录验证开始，token：{}", token);
        if (token == null || token.isEmpty()) {
            LOG.info( "token为空，请求被拦截 HttpStatus.UNAUTHORIZED:没有权限" );
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        //验证token的合法性  从redis中获取登录时生成保存起来的token与前端带来的token是否一致
        Object object = stringRedisTemplate.opsForValue().get(token);
        if (object == null) {
            LOG.warn( "token无效，请求被拦截" );
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }else {

            //token验证通过后下一步就需要进行 用户权限控制了
            //根据用户级别的不同 能够访问的模块权限不同

            LOG.info("token验证已经通过：{}", object);
            // 增加权限校验，gateway里没有LoginUserDto，所以全部用JSON操作
            LOG.info("接口权限校验，请求地址：{}", path);
            boolean exist = false;
            JSONObject loginUserDto = JSON.parseObject(String.valueOf(object));
            JSONArray requests = loginUserDto.getJSONArray("requests");
            // 遍历所有【权限请求】，判断当前请求的地址是否在【权限请求】里
            for (int i = 0, l = requests.size(); i < l; i++) {
                String request = (String) requests.get(i);
                if (path.contains(request)) {
                    exist = true;
                    break;
                }
            }
            if (exist) {
                LOG.info("权限校验通过");
            } else {
                LOG.warn("权限校验未通过");
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            return chain.filter(exchange);
        }//end else

    }

    @Override
    public int getOrder()
    {
//        过滤器的顺序 当前为第一个过滤器  首先进行拦截
        return 1;
    }
}