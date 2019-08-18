package org.project.study.security.auth.jwt.extractor;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

@Component
public class JwtHeaderTokenExtractor implements TokenExtractor {
    @Override
    public String extract(String header) {
        if (StringUtils.isBlank(header)) {
            throw new AuthenticationServiceException("Authorization header cannot be blank!");
        }

        return header;
    }
}
