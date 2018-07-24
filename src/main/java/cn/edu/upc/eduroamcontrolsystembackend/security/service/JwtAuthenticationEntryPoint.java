package cn.edu.upc.eduroamcontrolsystembackend.security.service;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * This is description.
 *
 * @author jay
 * @date 2018/07/22
 */

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // 如果用户无凭证访问被security保护的资源, 则返回401 "Unauthorized" 错误
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}