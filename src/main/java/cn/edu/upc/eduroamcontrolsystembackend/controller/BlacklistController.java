package cn.edu.upc.eduroamcontrolsystembackend.controller;

import cn.edu.upc.eduroamcontrolsystembackend.service.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/IsBlacklist")
    public Object isBlacklist(int userId) {
        return blacklistService.findByUserId(userId) != null;
    }

    /**
     * 取得所有黑名单
     *
     * @return the object, iterable(Blacklist)
     */
    @GetMapping("/RetrieveAllBlacklist")
    public Object retrieveAllBlacklist() {
        return blacklistService.findAll();
    }


}
