package edu.byu.cs.tweeter.model.service.request;

public class LogoutRequest {
    private final String username;

    public LogoutRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
