package com.xb.safe.security;

import com.xb.danilov.sessionmanager.dto.Role;
import org.springframework.security.core.GrantedAuthority;
import java.io.Serializable;

public class CustomRole implements GrantedAuthority, Serializable {
    private String roleString;

    @Override
    public String getAuthority() {
        return roleString;
    }

    public CustomRole(Role role) {
        this.roleString = role.id;
    }

    @Override
    public String toString() {
        return "CustomRole{"
                + "roleString='" + roleString + '\''
                + '}';
    }
}
