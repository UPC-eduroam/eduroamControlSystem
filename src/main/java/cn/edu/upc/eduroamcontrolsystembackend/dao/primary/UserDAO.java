package cn.edu.upc.eduroamcontrolsystembackend.dao.primary;

import cn.edu.upc.eduroamcontrolsystembackend.model.primary.Authority;
import cn.edu.upc.eduroamcontrolsystembackend.model.primary.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * UserDAO
 *
 * @author jay
 * @date 2018/05/02
 */

public interface UserDAO extends CrudRepository<User, Integer> {
    User findFirstByUserId(String userId);

    User findFirstByEmailAddress(String emailAddress);

    List<User> findAllByAuthorities(List<Authority> authorities);

}
