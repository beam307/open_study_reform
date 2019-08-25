package org.project.study.security.auth.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.project.study.config.JwtSettings;
import org.project.study.model.LoginInfo;
import org.project.study.model.User;
import org.project.study.security.auth.crypto.Crypter;
import org.project.study.security.auth.crypto.JcaCrypter;
import org.project.study.security.auth.token.AccessJwtToken;
import org.project.study.security.auth.token.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Date;

@Component
public class JwtTokenFactory {

    @Autowired
    private JwtSettings settings;

    @Autowired
    private SecureRandomIDGenerator idGenerator;

    @Value("security.crypter.key")
    private String key;

    public AccessJwtToken createAccessJwtToken(User user) throws JsonProcessingException, GeneralSecurityException, UnsupportedEncodingException {
        if (StringUtils.isBlank(user.getEmail()))
            throw new IllegalArgumentException("Cannot create JWT Token without username");

        if (user.getAuthorities() == null || user.getAuthorities().isEmpty())
            throw new IllegalArgumentException("User doesn't have any privileges");

        Crypter crypter = new JcaCrypter(key);
        Claims claims = Jwts.claims().setSubject(crypter.encrypt(new LoginInfo(user.getId(), user.getEmail()).toJson()));

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(settings.getTokenIssuer())
                .setId(idGenerator.generate())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + settings.getTokenExpirationTime()))
                .signWith(SignatureAlgorithm.HS256, settings.getTokenSigningKey())
                .compact();

        return new AccessJwtToken(token, claims);
    }

    public JwtToken createRefreshToken(User user) throws JsonProcessingException, GeneralSecurityException, UnsupportedEncodingException {
        if (StringUtils.isBlank(user.getEmail())) {
            throw new IllegalArgumentException("Cannot create JWT Token without username");
        }

        Crypter crypter = new JcaCrypter(key);
        Claims claims = Jwts.claims().setSubject(crypter.encrypt(new LoginInfo(user.getId(), user.getEmail()).toJson()));

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(settings.getTokenIssuer())
                .setId(idGenerator.generate())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + settings.getRefreshTokenExpTime()))
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact();

        return new AccessJwtToken(token, claims);
    }
}
