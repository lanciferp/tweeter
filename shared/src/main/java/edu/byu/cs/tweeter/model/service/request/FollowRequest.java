package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class FollowRequest {

    private User user;
    private AuthToken authToken;
    private String followee;

    public FollowRequest(User user, AuthToken authToken, String followee) {
        this.user = user;
        this.authToken = authToken;
        this.followee = followee;
    }

    public User getUser(){
        return this.user;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public String getFollowee() {
        return followee;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    public void setFollowee(String followee) {
        this.followee = followee;
    }
}
