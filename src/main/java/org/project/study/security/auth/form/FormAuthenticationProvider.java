package org.project.study.security.auth.form;

import org.project.study.model.User;
import org.project.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class FormAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "아이디나 비밀번호를 입력해주세요.");

        String email = (String) authentication.getPrincipal();
        String pwd = (String) authentication.getCredentials();

        User user = userService.getUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("등록되지 않는 아이디입니다."));

        if (!encoder.matches(pwd, user.getPwd()) || !user.getActive()) {
            throw new BadCredentialsException("아이디가 없거나 비밀번호가 틀렸습니다.");
        }

        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
