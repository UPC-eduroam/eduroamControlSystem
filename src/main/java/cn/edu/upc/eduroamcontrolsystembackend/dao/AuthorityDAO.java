package cn.edu.upc.eduroamcontrolsystembackend.dao;

import cn.edu.upc.eduroamcontrolsystembackend.model.Authority;
import cn.edu.upc.eduroamcontrolsystembackend.model.AuthorityType;
import org.springframework.data.repository.CrudRepository;

/**
 * 权限表的DAO
 *
 * @author jay
 * @date 2018/07/22
 */

public interface AuthorityDAO extends CrudRepository<Authority, Integer> {
    Authority findFirstByAuthorityType(AuthorityType authorityType);
}
