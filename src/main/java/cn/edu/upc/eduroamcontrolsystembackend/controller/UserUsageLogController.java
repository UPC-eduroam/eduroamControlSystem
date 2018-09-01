package cn.edu.upc.eduroamcontrolsystembackend.controller;

import cn.edu.upc.eduroamcontrolsystembackend.service.UserUsageLogService;
import cn.edu.upc.eduroamcontrolsystembackend.util.GetUserIdFromRequest;
import cn.edu.upc.eduroamcontrolsystembackend.util.MyDateFormat;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * UserUsageLogController
 *
 * @author jay
 * @date 2018/05/02
 */

@RestController
@RequestMapping("UserUsageLogController")
@PreAuthorize("hasRole('ADMIN')")
public class UserUsageLogController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private GetUserIdFromRequest getUserIdFromRequest;
    @Autowired
    private UserUsageLogService userUsageLogService;
    @Autowired
    private MyDateFormat myDateFormat;

    @ApiOperation("获取指定用户的所有操作记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "startDate", value = "开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "endDate", value = "结束时间", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "objectId", value = "需要查询的用户Id(即操作对象的ID)", required = true, dataType = "String"),
    })
    @GetMapping("/GetUserUsageLogsByUserIdAndDate")
    public Object GetUserUsageLogsByUserIdAndDate(String objectId, String startDate, String endDate) {
        String adminId = getUserIdFromRequest.getUserId(request);
        userUsageLogService.createUserUsageLog(adminId, myDateFormat.formattedDate(), "查询用户 " + objectId + " 的操作日志");
        return userUsageLogService.findAllByUserIdAndOperatingTimeBetween(objectId, startDate, endDate);
    }
}