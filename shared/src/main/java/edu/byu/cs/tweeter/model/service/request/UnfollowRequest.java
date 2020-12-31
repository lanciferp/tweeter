package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class UnfollowRequest {
    private User user;
    private AuthToken authToken;
    private String exFollower;

    public UnfollowRequest() {
        user = null;
        authToken = null;
        exFollower = null;
    }

    public UnfollowRequest(User user, AuthToken authToken, String exFollower) {
        this.user = user;
        this.authToken = authToken;
        this.exFollower = exFollower;
    }

    public String getExFollower() {
        return exFollower;
    }

    public User getUser(){
        return this.user;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    public void setExFollower(String exFollower) {
        this.exFollower = exFollower;
    }
}
