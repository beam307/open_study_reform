package org.project.study.security.auth.jwt.extractor;

public interface TokenExtractor {
    String extract(String payload);
}