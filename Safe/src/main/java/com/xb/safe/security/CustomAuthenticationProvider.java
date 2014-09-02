package com.xb.safe.security;

import com.xb.danilov.sessionmanager.client.SessionManagerClient;
import com.xb.danilov.sessionmanager.dto.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import java.util.ArrayList;
import java.util.List;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private SessionManagerClient smc;
    private final String mainSystem = "Safe";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName().toLowerCase().trim();
        String password = authentication.getCredentials().toString();
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        try {
            System.out.println(login + " START AUTH");
            String session = smc.auth(login, password, details.getRemoteAddress());
            List<Role> rolesBySystem = smc.getUserRoles(session, login);
            List<CustomRole> customRoles = new ArrayList<>();
            if (rolesBySystem != null) {
                for (Role each : rolesBySystem) {
                    if (each.getSystem().id.equalsIgnoreCase(mainSystem)) {
                        customRoles.add(new CustomRole(each));
                    }
                }
            } else {
                customRoles = new ArrayList<>();
            }
            CustomAuthentication customAuthentication = new CustomAuthentication(session, login, customRoles, details);
            System.out.println(login + " END AUTH");
            return customAuthentication;
        } catch (Exception ex) {
            System.out.println(login + " " + ex.getMessage());
            throw new AuthenticationException(ex.getMessage()) {
            };
        }
    }

    @Override
    public boolean supports(Class<? extends Object> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
