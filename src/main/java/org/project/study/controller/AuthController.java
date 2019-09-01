package org.project.study.controller;

import org.project.study.model.User;
import org.project.study.security.auth.jwt.JwtAuthenticationProvider;
import org.project.study.security.auth.token.AccessJwtToken;
import org.project.study.security.auth.util.JwtTokenFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    JwtTokenFactory tokenFactory;

    @Autowired
    JwtAuthenticationProvider jwtAuthenticationProvider;

    @PostMapping("/refreshToken")
    public ResponseEntity<String> create(HttpServletRequest request) {
        try {
            String refreshToken = request.getHeader("X-REFRESH-TOKEN");
            User user = jwtAuthenticationProvider.parseRefreshToken(refreshToken);
            if (user != null) {
                AccessJwtToken accessJwtToken = tokenFactory.createAccessJwtToken(user);
                return ResponseEntity.ok(accessJwtToken.getToken());
            }
        } catch (Exception e) {
        }
        return ResponseEntity.status(401).build();
    }
}
