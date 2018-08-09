package cn.edu.upc.eduroamcontrolsystembackend.security.controller;

import cn.edu.upc.eduroamcontrolsystembackend.dao.primary.UserDAO;
import cn.edu.upc.eduroamcontrolsystembackend.dto.ResponseMessage;
import cn.edu.upc.eduroamcontrolsystembackend.model.primary.User;
import cn.edu.upc.eduroamcontrolsystembackend.security.service.JwtTokenUtil;
import cn.edu.upc.eduroamcontrolsystembackend.security.service.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is description.
 *
 * @author jay
 * @date 2018/07/21
 */

@RestController
public class AuthenticationController {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserDAO userDAO;

    //登录接口, 生成token
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public Object createAuthenticationToken(String username, String password) throws AuthenticationException {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {
            return new ResponseMessage(-1, "登录失败, 用户名或密码错误");
        }
        // 生成token
        final String token = jwtTokenUtil.generateToken((JwtUser) userDetails);
        Map<Object, Object> map = new HashMap<>();
        User user = userDAO.findFirstByUserId(userDetails.getUsername());
        map.put("user", user);
        map.put("token", token);
        return map;
    }

    //刷新token接口
    @RequestMapping(value = "/token/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {

        String token = request.getHeader(tokenHeader);

        logger.info("refresh接口接收到刷新请求, token: " + token);

        String username = jwtTokenUtil.getUserIdFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            logger.info("已为用户 " + username + " 刷新token");
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(refreshedToken);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
