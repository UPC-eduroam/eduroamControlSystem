package cn.edu.upc.eduroamcontrolsystembackend.analysis.util;

import cn.edu.upc.eduroamcontrolsystembackend.model.radius.RadPostAuth;
import cn.edu.upc.eduroamcontrolsystembackend.service.RadPostAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * 从RADIS服务器的数据库中获取最新的且已接受的认证请求, 返回所有的用户名
 * Created by jay on 2018/08/12
 */

@Service
public class GetLatestAccessRequests {
    @Autowired
    private RadPostAuthService radPostAuthService;

    public Iterable<RadPostAuth> getAllLatestUsernames(Timestamp start, Timestamp end) {
        return radPostAuthService.findAllByAuthdateBetween(start, end);
    }

}
