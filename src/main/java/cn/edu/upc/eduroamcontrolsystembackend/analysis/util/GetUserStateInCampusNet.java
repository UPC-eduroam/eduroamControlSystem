package cn.edu.upc.eduroamcontrolsystembackend.analysis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

/**
 * 通过校园网计费系统相关接口获取指定用户当前在校园网中的状态
 * <p>
 * Created by jay on 2018/08/12
 */

public class GetUserStateInCampusNet {
    @Value("${campus-net.api.root}" + "${campus-net.api.getUserOnlineState}")
    private String getUserStateInCampusNetAPI;

    @Autowired
    private RestTemplate restTemplate;

    //获取用户在线状态的函数, 需根据实际情况改写
    public String getUserOnlineState(String userId) {
        String url = getUserStateInCampusNetAPI;
        String result = restTemplate.getForObject(url + "?userId=" + userId, String.class);
        return result.substring(result.indexOf("<online>"), result.indexOf("</online>")).substring("<online>".length());
    }
}
