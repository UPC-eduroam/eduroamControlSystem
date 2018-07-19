package cn.edu.upc.eduroamcontrolsystembackend.dao;

import cn.edu.upc.eduroamcontrolsystembackend.model.Admin;
import org.springframework.data.repository.CrudRepository;

/**
 * AdminDAO
 *
 * @author jay
 * @date 2018/05/02
 */

public interface AdminDAO extends CrudRepository<Admin, Integer> {
    Admin findByAdminId(Boolean adminId);
}
