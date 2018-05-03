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

    public void createBlacklist(int userId) {
        Blacklist blacklist = new Blacklist(userId);
        blacklistDAO.save(blacklist);
    }

    public void deleteBlacklist(int userId) {
        Blacklist blacklist = blacklistDAO.findByUserId(userId);
        blacklistDAO.delete(blacklist);
    }

    public Blacklist findByUserId(int userId) {
        return blacklistDAO.findByUserId(userId);
    }

    public Iterable<Blacklist> findAll() {
        return blacklistDAO.findAll();
    }

}
