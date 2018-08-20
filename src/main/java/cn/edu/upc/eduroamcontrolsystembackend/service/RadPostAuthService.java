package cn.edu.upc.eduroamcontrolsystembackend.service;

import cn.edu.upc.eduroamcontrolsystembackend.dao.radius.RadPostAuthDAO;
import cn.edu.upc.eduroamcontrolsystembackend.model.radius.RadPostAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Created by jay on 2018/08/12
 */

@Service
public class RadPostAuthService {
    @Autowired
    private RadPostAuthDAO radPostAuthDAO;

    public Iterable<RadPostAuth> findAllByAuthdateBetween(Timestamp start, Timestamp end) {
        return radPostAuthDAO.findAllByAuthdateBetween(start, end);
    }

}
