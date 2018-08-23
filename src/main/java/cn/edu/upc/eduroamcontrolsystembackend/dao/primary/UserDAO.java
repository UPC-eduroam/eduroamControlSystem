package cn.edu.upc.eduroamcontrolsystembackend.dao.primary;

import cn.edu.upc.eduroamcontrolsystembackend.model.primary.User;
import org.springframework.data.repository.CrudRepository;

/**
 * UserDAO
 *
 * @author jay
 * @date 2018/05/02
 */

public interface UserDAO extends CrudRepository<User, Integer> {
    User findFirstByUserId(String userId);

    User findFirstByEmailAddress(String emailAddress);
}
