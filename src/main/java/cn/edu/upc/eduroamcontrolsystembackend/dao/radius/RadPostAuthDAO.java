package cn.edu.upc.eduroamcontrolsystembackend.dao.radius;

import cn.edu.upc.eduroamcontrolsystembackend.model.radius.RadPostAuth;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by jay on 2018/08/09
 */

public interface RadPostAuthDAO extends CrudRepository<RadPostAuth, Integer> {
    @Query(value = "SELECT DISTINCT username FROM radpostauth WHERE authdate >= ?1 AND authdate <= ?2 AND reply='Access-Accept'", nativeQuery = true)
    Iterable<Object> findLoginedUsersByAuthdate(Timestamp startDate, Timestamp endDate);

}
