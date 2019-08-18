package org.project.study.security.auth.ajax;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.project.study.model.UserContext;
import org.project.study.security.auth.token.JwtToken;
import org.project.study.security.auth.jwt.JwtTokenFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AjaxAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private JwtTokenFactory tokenFactory;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserContext userContext = (UserContext) authentication.getPrincipal();

        JwtToken accessToken = tokenFactory.createAccessJwtToken(userContext);
        JwtToken refreshToken = tokenFactory.createRefreshToken(userContext);

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", accessToken.getToken());
        tokenMap.put("refreshToken", refreshToken.getToken());

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getWriter(), tokenMap);

        clearAuthenticationAttributes(request);
    }

    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
