package cn.edu.upc.eduroamcontrolsystembackend.security.service;

import java.io.Serializable;

/**
 * This is description.
 *
 * @author jay
 * @date 2018/07/23
 */

public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
