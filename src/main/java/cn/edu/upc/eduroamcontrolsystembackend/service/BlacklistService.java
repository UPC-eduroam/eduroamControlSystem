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
 * @param userId
 */
    public Blacklist createBlacklist(int userId) {
        Blacklist blacklist = new Blacklist(userId);
      return  blacklistDAO.save(blacklist);
    }
    /**
     * 从黑名单中把某个user删除
     *
     * @param userId
     */
    public void deleteBlacklist(int userId) {
        Blacklist blacklist = blacklistDAO.findByUserId(userId);
        blacklistDAO.deleteByUserId(blacklist.getId());
    }
    /**
     * 通过id查找黑名单用户
     *
     * @return
     */
    public Boolean findByUserId(int userId) {

        Blacklist blacklist=blacklistDAO.findByUserId(userId);
        if (blacklist==null){
            return false;
        }else return true;
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
