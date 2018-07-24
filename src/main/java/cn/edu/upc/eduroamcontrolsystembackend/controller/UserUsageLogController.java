package cn.edu.upc.eduroamcontrolsystembackend.controller;

import cn.edu.upc.eduroamcontrolsystembackend.service.UserUsageLogService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * UserUsageLogController
 *
 * @author jay
 * @date 2018/05/02
 */

@RestController
@RequestMapping("UserUsageLogController")
public class UserUsageLogController {
    @Autowired
    private UserUsageLogService userUsageLogService;

    /**
     * 创建用户使用日志
     *
     * @param userId    the user id
     * @param loginTime the login time, 在校外登录eduroam的时间
     * @param orgDomain the orgDomain, 用户所在机构的域名
     */
    @PostMapping("/CreateUserUsageLog")
    // public void createUserUsageLog(int userId, Date loginTime, String orgDomain) {

    @ApiOperation("创建用户使用日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "the user id", dataType = "int"),
            @ApiImplicitParam(name = "loginTime", value = "the login time", dataType = "int"),
            @ApiImplicitParam(name = "orgDomain", value = "the orgDomain", dataType = "String")
    })
    public void createUserUsageLog(String userId, Date loginTime, String orgDomain) {

        userUsageLogService.createUserUsageLog(userId, loginTime, orgDomain);
    }

    /**
     * 根据用户id查询所有eduroam登录日志
     *
     * @param userId the user id
     * @return the object, iterable(UserUsageLog)
     */
    @GetMapping("/GetAllUserUsageLogByUserId")
    public Object getAllUserUsageLogByUserId(String userId) {

        return userUsageLogService.findAllByUserId(userId);
    }
}

