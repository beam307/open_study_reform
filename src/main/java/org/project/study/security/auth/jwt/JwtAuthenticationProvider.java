package org.project.study.security.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.project.study.config.JwtSettings;
import org.project.study.model.LoginInfo;
import org.project.study.model.User;
import org.project.study.security.auth.crypto.Crypter;
import org.project.study.security.auth.crypto.JcaCrypter;
import org.project.study.security.auth.token.JwtAuthenticationToken;
import org.project.study.security.auth.token.RawAccessJwtToken;
import org.project.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtSettings jwtSettings;

    @Autowired
    private UserService userService;

    @Value("security.crypter.key")
    private String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();

        Crypter crypter =  new JcaCrypter(key);
        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey());

        try {
            String subject = crypter.decrypt(jwsClaims.getBody().getSubject());
            LoginInfo loginInfo = LoginInfo.fromJson(subject);

            if (loginInfo == null)
                throw new BadCredentialsException("invalid loginInfo" + subject);

            User user = userService.getUserByIdAndEmail(loginInfo.getId(), loginInfo.getEmail()).get();

            return new JwtAuthenticationToken(user, user.getAuthorities());
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
