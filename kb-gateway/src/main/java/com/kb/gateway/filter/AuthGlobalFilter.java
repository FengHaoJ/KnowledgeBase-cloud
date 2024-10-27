package com.kb.gateway.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.AntPathMatcher;
import com.kb.gateway.config.AuthProperties;
import com.kb.gateway.config.JwtProperties;
import com.kb.common.context.BaseContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import com.kb.common.utils.JwtUtil;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(AuthProperties.class)
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {


    private final AuthProperties authProperties;

    @Autowired
    private JwtProperties jwtProperties;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.获取Request
        ServerHttpRequest request = exchange.getRequest();
        System.out.println(request);
        // 2.判断是否不需要拦截
        if(isExclude(request.getPath().toString())) {
            System.out.println("132");
            // 无需拦截，直接放行
            return chain.filter(exchange);
        }
        // 3.获取请求头中的token
        // 从请求头中获取令牌
        String token = null;
        List<String> headers = request.getHeaders().get(jwtProperties.getUserTokenName());
        System.out.println(headers);
        if (!CollUtil.isEmpty(headers)) {
            token = headers.get(0);
        }

        // 4.校验并解析token

        // 如果无效，拦截
        ServerHttpResponse response = exchange.getResponse();
//        response.setRawStatusCode(401);
//        return response.setComplete();
        Long userId = null;
        try {
            log.info("JWT校验: {}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);

            // 检查令牌是否已过期
            Date expirationDate = claims.getExpiration();
            Date now = new Date();
            if (now.after(expirationDate)) {
                // 令牌已过期，响应401状态码
                log.info("令牌已过期");
                response.setRawStatusCode(401);
                return response.setComplete();
            }

            userId = Long.valueOf(claims.get("uid").toString());
            log.info("当前用户ID：{}", userId);
            log.info("过期时间为：{}",expirationDate);
            // 在BaseContext中存储当前操作者的信息
            BaseContext.setCurrentId(userId);
        } catch (ExpiredJwtException ex) {
            // 令牌超时，响应401状态码
            log.info("令牌已过期");
            response.setRawStatusCode(401);
            return response.setComplete();
        } catch (JwtException ex) {
            // 令牌无效或校验失败，响应401状态码
            log.info("令牌无效");
            response.setRawStatusCode(401);
            return response.setComplete();
        } catch (Exception ex) {
            // 其他异常，响应500状态码
            log.error("JWT校验发生错误", ex);
            response.setRawStatusCode(500);
            return response.setComplete();
        }


        // 传递用户信息
        String userInfo = userId.toString();
        System.out.println("userId = " + userId);
        ServerWebExchange ex = exchange.mutate()
                .request(b -> b.header("user-info", userInfo))
                .build();
        // 6.放行
        return chain.filter(ex);
    }

    private boolean isExclude(String antPath) {
        for (String pathPattern : authProperties.getExcludePaths()) {
            if(antPathMatcher.match(pathPattern, antPath)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
