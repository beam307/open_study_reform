package org.project.study.security.auth.ajax;

import org.project.study.model.User;
import org.project.study.model.UserContext;
import org.project.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");

        String email = (String) authentication.getPrincipal();
        String pwd = (String) authentication.getCredentials();

        User user = userService.getUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        if (!encoder.matches(pwd, user.getPwd())) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }

        if (user.getAdmin() == null) throw new InsufficientAuthenticationException("User has no roles assigned");

        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getAdmin() > 0) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        UserContext userContext = UserContext.create(user.getEmail(), authorities);

        return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
