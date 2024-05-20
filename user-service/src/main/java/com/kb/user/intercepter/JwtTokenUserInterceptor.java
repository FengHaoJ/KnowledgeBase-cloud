package com.kb.user.intercepter;



import context.BaseContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import com.kb.user.properties.JwtProperties;
import utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


/**
 * jwt令牌校验的拦截器
 */
@Component
@Slf4j
public class JwtTokenUserInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校验jwt
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            // 当前拦截到的不是动态方法，直接放行
            return true;
        }

        // 从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getUserTokenName());

        // 校验令牌
        try {
            log.info("JWT校验: {}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);

            // 检查令牌是否已过期
            Date expirationDate = claims.getExpiration();
            Date now = new Date();
            if (now.after(expirationDate)) {
                // 令牌已过期，响应401状态码
                log.info("令牌已过期");
                log.info("过期时间为：{}",expirationDate);
                response.setStatus(401);
                return false;
            }

            Long userId = Long.valueOf(claims.get("uid").toString());
            log.info("当前用户ID：{}", userId);
            log.info("过期时间为：{}",expirationDate);
            // 在BaseContext中存储当前操作者的信息
            BaseContext.setCurrentId(userId);
            // 请求允许通过，继续处理
            return true;
        } catch (ExpiredJwtException ex) {
            // 令牌超时，响应401状态码
            log.info("令牌已过期");
            response.setStatus(401);
            return false;
        } catch (JwtException ex) {
            // 令牌无效或校验失败，响应401状态码
            log.info("令牌无效");
            response.setStatus(401);
            return false;
        } catch (Exception ex) {
            // 其他异常，响应500状态码
            log.error("JWT校验发生错误", ex);
            response.setStatus(500);
            return false;
        }
    }
}
