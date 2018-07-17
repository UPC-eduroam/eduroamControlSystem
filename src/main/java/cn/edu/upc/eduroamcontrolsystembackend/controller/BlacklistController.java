package cn.edu.upc.eduroamcontrolsystembackend.controller;

import cn.edu.upc.eduroamcontrolsystembackend.dto.SwaggerParameter;
import cn.edu.upc.eduroamcontrolsystembackend.service.BlacklistService;
import cn.edu.upc.eduroamcontrolsystembackend.dao.BlacklistDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
/**
 * BlackListController
 *
 * @author jay
 * @date 2018/05/02
 */

@RestController
@RequestMapping("BlackListController")
public class BlacklistController {
    @Autowired
    private BlacklistService blacklistService;
    /**
     * 将用户加入黑名单
     *
     * @param userId the user id
     * @return the object
     */
    @ApiOperation(value = "添加黑名单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query",name ="userId",value = "用户Id",required = true,dataType = "int"),
            @ApiImplicitParam(paramType = "query",name ="reason",value = "加入原因",required = true,dataType = "String")
    })
    @PostMapping("/CreateBlacklist")
    public Object createBlacklist(int userId) {
        blacklistService.createBlacklist(userId);
        return true;
    }

    /**
     * 将用户移出黑名单
     *
     * @param userId the user id
     * @return the object
     */
    @ApiOperation(value = "移除黑名单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query" , name = "userId",value = "用户Id",required = true,dataType = "int")
    })
    @PostMapping("/DeleteByUserId")
    public Object deleteBlacklist(int userId) {
        blacklistService.deleteBlacklist(userId);
        return true;
    }

    /**
     * 根据id判断是否在黑名单
     *
     * @param userId the user id
     * @return the object, true/false
     */
    @ApiOperation(value = "判断是否是黑名单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String"),
            @ApiImplicitParam(paramType = "query" , name = "userId",value = "用户Id",required = true,dataType = "int"),
            @ApiImplicitParam(paramType = "query",name ="reason",value = "属于黑名单",required = true,dataType = "boolean")
    })
    @GetMapping("/IsBlacklist")
    public Object isBlacklist(int userId) {
        return blacklistService.findByUserId(userId) != null;
    }

    /**
     * 取得所有黑名单
     *
     * @return the object, iterable(Blacklist)
     */
    @ApiOperation(value = "取得所有黑名单")
    @ApiImplicitParam(paramType = "query", name = SwaggerParameter.Authorization, dataType = "String")
    @GetMapping("/RetrieveAllBlacklist")
    public Object retrieveAllBlacklist() {

        return blacklistService.findAll();
    }


}
