package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.Status;

public class TweetRequest {
    private final Status status;

    public TweetRequest(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
