package org.project.study.model;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
public class UserContext {

    private final String email;
    private final List<GrantedAuthority> authorities;

    private UserContext(String email, List<GrantedAuthority> authorities) {
        this.email = email;
        this.authorities = authorities;
    }

    public static UserContext create(String username, List<GrantedAuthority> authorities) {
        if (StringUtils.isBlank(username)) throw new IllegalArgumentException("Username is blank: " + username);
        return new UserContext(username, authorities);
    }

}
