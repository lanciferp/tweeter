package edu.byu.cs.tweeter.model.service.request;

public class LogoutRequest {
    private String username;

    public LogoutRequest() {
        username = null;
    }

    public LogoutRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
