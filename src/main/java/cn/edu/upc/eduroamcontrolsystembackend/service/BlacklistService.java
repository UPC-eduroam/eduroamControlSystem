package cn.edu.upc.eduroamcontrolsystembackend.service;

import cn.edu.upc.eduroamcontrolsystembackend.dao.BlacklistDAO;
import cn.edu.upc.eduroamcontrolsystembackend.model.Blacklist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * BlackListService
 *
 * @author jay
 * @date 2018/05/02
 */

@Service
public class BlacklistService {
    @Autowired
    private BlacklistDAO blacklistDAO;

    /**
     * 创建黑名单
     *
     * @param userId
     */
    public Blacklist createBlacklist(String userId) {
        Blacklist blacklist = new Blacklist(userId);
        return blacklistDAO.save(blacklist);
    }

    /**
     * 从黑名单中把某个user删除
     *
     * @param userId
     */
    public void deleteBlacklist(String userId) {
        Blacklist blacklist = blacklistDAO.findFirstByUserId(userId);
        blacklistDAO.deleteByUserId(blacklist.getUserId());
    }

    /**
     * 通过id判断是否在黑名单
     *
     * @return
     */
    public Boolean findByUserId(String userId) {
        Blacklist blacklist = blacklistDAO.findFirstByUserId(userId);
        if (blacklist == null) {
            return false;
        } else return true;
    }

    /**
     * 查找所有黑名单用户
     *
     * @return
     */
    public Iterable<Blacklist> findAll() {
        return blacklistDAO.findAll();
    }

}
