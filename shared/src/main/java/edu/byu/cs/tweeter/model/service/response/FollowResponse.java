package edu.byu.cs.tweeter.model.service.response;

public class FollowResponse extends Response{
    private int newFollowerCount;

    public FollowResponse() {
        super(false);
    }

    public FollowResponse(boolean success) {
        super(success);
    }

    public FollowResponse(boolean success, int newFollowerCount) {
        super(success);
        this.newFollowerCount = newFollowerCount;
    }

    public FollowResponse(String message) {
        super(false, message);
    }

    public int getNewFollowerCount() {
        return newFollowerCount;
    }
}
