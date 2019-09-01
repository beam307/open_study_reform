package org.project.study.security.auth.form;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.project.study.model.User;
import org.project.study.security.ErrorCode;
import org.project.study.security.ErrorResponse;
import org.project.study.security.auth.token.JwtToken;
import org.project.study.security.auth.util.JwtTokenFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class FormAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private JwtTokenFactory tokenFactory;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        JwtToken accessToken = null;
        JwtToken refreshToken = null;
        try {
            accessToken = tokenFactory.createAccessJwtToken(user);
            refreshToken = tokenFactory.createRefreshToken(user);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            mapper.writeValue(response.getWriter(), ErrorResponse.of(e.getMessage(), ErrorCode.ENCRYPT_ERROR, HttpStatus.INTERNAL_SERVER_ERROR));
        }

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("accessToken", accessToken.getToken());
        tokenMap.put("refreshToken", refreshToken.getToken());

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getWriter(), tokenMap);

    }

}
