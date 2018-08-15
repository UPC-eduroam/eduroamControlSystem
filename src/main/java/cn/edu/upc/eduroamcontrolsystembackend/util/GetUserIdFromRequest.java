package cn.edu.upc.eduroamcontrolsystembackend.util;

import cn.edu.upc.eduroamcontrolsystembackend.security.service.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Action;

/**
 * Created by jay on 2018/08/15
 */
@Service
public class GetUserIdFromRequest {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public String getUserId(HttpServletRequest httpServletRequest) {
        return jwtTokenUtil.getUserIdFromToken(httpServletRequest.getHeader("Authorization"));
    }
}
