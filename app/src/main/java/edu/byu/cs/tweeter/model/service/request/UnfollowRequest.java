package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.User;

public class UnfollowRequest {
    private final User user;

    public UnfollowRequest(User user) {
        this.user = user;
    }

    public User getUser(){
        return this.user;
    }
}
