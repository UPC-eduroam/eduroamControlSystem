package cn.edu.upc.eduroamcontrolsystembackend.dao.primary;

import cn.edu.upc.eduroamcontrolsystembackend.model.primary.Blacklist;
import org.springframework.data.repository.CrudRepository;

/**
 * BlacklistDAO
 *
 * @author jay
 * @date 2018/05/02
 */

public interface BlacklistDAO extends CrudRepository<Blacklist, Integer> {
    Blacklist findFirstByUserId(String userId);
}
