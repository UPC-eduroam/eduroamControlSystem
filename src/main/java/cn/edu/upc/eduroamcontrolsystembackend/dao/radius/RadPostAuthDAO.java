package cn.edu.upc.eduroamcontrolsystembackend.dao.radius;

import cn.edu.upc.eduroamcontrolsystembackend.model.radius.RadPostAuth;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;

/**
 * Created by jay on 2018/08/09
 */

public interface RadPostAuthDAO extends CrudRepository<RadPostAuth, Integer> {

    Iterable<RadPostAuth> findAllByAuthdateBetweenAndAndReplyIs(Timestamp start, Timestamp end, String reply);


}
