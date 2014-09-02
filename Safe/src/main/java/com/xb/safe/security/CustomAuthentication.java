package com.xb.safe.security;

import com.xb.danilov.sessionmanager.client.SessionManagerClient;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class CustomAuthentication implements Authentication, Serializable {
    protected boolean authenticated;
    protected String session;
    protected String userName;
    protected Object details;
    protected List<? extends GrantedAuthority> authority = new ArrayList<>();

    public CustomAuthentication(String session, String userName, List<? extends GrantedAuthority> authority, Object details) {
        this.session = session;
        this.userName = userName;
        this.authority = authority;
        this.details = details;
        this.authenticated = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authority;
    }

    @Override
    public Object getCredentials() {
        return userName;
    }

    @Override
    public Object getDetails() {
        return details;
    }

    @Override
    public Object getPrincipal() {
        return userName;
    }

    @Override
    public boolean isAuthenticated() {
        if (authenticated) {
            try {
                SessionManagerClient smc = SpringApplicationContext.getBean(SessionManagerClient.class);
                return smc.validateSession(session);
            } catch (Exception ex) {
                return false;
            }
        } else {
            return authenticated;
        }
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.authenticated = b;
    }

    @Override
    public String getName() {
        return userName;
    }

    public String getSession() {
        return session;
    }
}
