package edu.byu.cs.tweeter.model.service.response;

public class FollowResponse extends Response{
    private int newFollowerCount;

    public FollowResponse(boolean success, int newFollowerCount) {
        super(success);
        this.newFollowerCount = newFollowerCount;
    }

    public int getNewFollowerCount() {
        return newFollowerCount;
    }
}
