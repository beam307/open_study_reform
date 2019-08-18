package org.project.study.security;

public enum Scopes {
    REFRESH_TOKEN;

    public String authority() {
        return "ROLE_" + this.name();
    }
}
