package cn.edu.upc.eduroamcontrolsystembackend.dao;

import cn.edu.upc.eduroamcontrolsystembackend.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * UserDAO
 *
 * @author jay
 * @date 2018/05/02
 */

public interface UserDAO extends CrudRepository<User, Integer> {
    User findFirstByUserId(String userId);
}
