package edu.byu.cs.tweeter.model.service.response;

public class UnfollowResponse extends Response {
    private int newFollowerCount;

    public UnfollowResponse(int newFollowerCount) {
        super(true);
        this.newFollowerCount = newFollowerCount;
    }

    public int getNewFollowerCount() {
        return newFollowerCount;
    }
}
