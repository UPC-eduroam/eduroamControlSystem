package cn.edu.upc.eduroamcontrolsystembackend.dao.primary;

import cn.edu.upc.eduroamcontrolsystembackend.model.primary.Authority;
import cn.edu.upc.eduroamcontrolsystembackend.model.primary.AuthorityType;
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
