package cn.edu.upc.eduroamcontrolsystembackend.controller;

import cn.edu.upc.eduroamcontrolsystembackend.dto.ResponseMessage;
import cn.edu.upc.eduroamcontrolsystembackend.service.BlacklistService;
import cn.edu.upc.eduroamcontrolsystembackend.service.UserService;
import cn.edu.upc.eduroamcontrolsystembackend.service.UserUsageLogService;
import cn.edu.upc.eduroamcontrolsystembackend.util.GetUserAuthority;
import cn.edu.upc.eduroamcontrolsystembackend.util.GetUserIdFromRequest;
import cn.edu.upc.eduroamcontrolsystembackend.util.MyDateFormat;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * BlackListController
 *
 * @author jay
 * @date 2018/05/02
 */

@RestController
@RequestMapping("BlackListController")
@PreAuthorize("hasRole('ADMIN')")
public class BlacklistController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private GetUserIdFromRequest getUserIdFromRequest;
    @Autowired
    private UserUsageLogService userUsageLogService;
    @Autowired
    private BlacklistService blacklistService;
    @Autowired
    private GetUserAuthority getUserAuthority;
    @Autowired
    private MyDateFormat myDateFormat;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "添加用户到黑名单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "objectId", value = "需要添加进黑名单的用户Id(即操作对象的ID)", required = true, dataType = "String")
    })
    @PostMapping("/AddUserToBlacklist")
    public Object addUserToBlacklist(String objectId) {
        String adminId = getUserIdFromRequest.getUserId(request);
        if (userService.findFirstByUserId(adminId) != null) {
            return new ResponseMessage(-1, "该用户不存在");
        }
        if (getUserAuthority.getAuthorityByUserId(objectId).contains("ADMIN")) {
            return new ResponseMessage(-1, "无法将管理员加入黑名单");
        }
        blacklistService.createBlacklist(objectId);
        userUsageLogService.createUserUsageLog(adminId, myDateFormat.formattedDate(), "添加用户" + objectId + "到黑名单");
        return new ResponseMessage(0, "加入黑名单成功");
    }

    @ApiOperation(value = "将用户从黑名单移除")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "objectId", value = "需要移除的用户Id(即操作对象的ID)", required = true, dataType = "String")
    })
    @PostMapping("/DeleteUserFromBlackList")
    public Object deleteUserFromBlacklist(String objectId) {
        String adminId = getUserIdFromRequest.getUserId(request);
        blacklistService.deleteBlacklist(objectId);
        userUsageLogService.createUserUsageLog(adminId, myDateFormat.formattedDate(), "将用户" + objectId + "从黑名单移除");
        return true;
    }

    @ApiOperation(value = "根据userId判断用户是否在黑名单中")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "objectId", value = "用户Id(即操作对象的ID)", required = true, dataType = "String"),
    })
    @GetMapping("/IsInBlacklist")
    public Object isInBlacklist(String objectId) {
        String adminId = getUserIdFromRequest.getUserId(request);
        userUsageLogService.createUserUsageLog(adminId, myDateFormat.formattedDate(), "查询用户" + objectId + "是否在黑名单");
        return blacklistService.findByUserId(objectId) != null;
    }

    @ApiOperation(value = "获取黑名单中所有用户")
    @GetMapping("/GetAllUsersFromBlacklist")
    public Object getAllUsersFromBlacklist() {
        String adminId = getUserIdFromRequest.getUserId(request);
        userUsageLogService.createUserUsageLog(adminId, myDateFormat.formattedDate(), "获取所有黑名单");
        return blacklistService.findAll();
    }
}
