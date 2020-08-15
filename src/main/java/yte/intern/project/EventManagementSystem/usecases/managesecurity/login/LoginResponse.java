package yte.intern.project.EventManagementSystem.usecases.managesecurity.login;

import lombok.Getter;

import java.util.List;

@Getter
public class LoginResponse {

    private final String token;
    private final List<String> role;

    public LoginResponse(final String token, final List<String> role) {
        this.token = token;
        this.role = role;
    }
}
