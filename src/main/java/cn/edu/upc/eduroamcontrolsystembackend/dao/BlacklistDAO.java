package cn.edu.upc.eduroamcontrolsystembackend.dao;

import cn.edu.upc.eduroamcontrolsystembackend.model.Blacklist;
import org.springframework.data.repository.CrudRepository;

/**
 * BlacklistDAO
 *
 * @author jay
 * @date 2018/05/02
 */

public interface BlacklistDAO extends CrudRepository<Blacklist, Integer> {
    Blacklist findByUserId(int userId);

    void deleteByUserId(int id);
}
