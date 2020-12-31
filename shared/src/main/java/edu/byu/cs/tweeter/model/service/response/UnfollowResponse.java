package edu.byu.cs.tweeter.model.service.response;

public class UnfollowResponse extends Response {
    private int newFollowerCount;

    public UnfollowResponse() {
        super(false);
    }

    public UnfollowResponse(int count){
        super(true);
        newFollowerCount = count;
    }

    public UnfollowResponse(boolean success) {
        super(success);
    }

    public UnfollowResponse(String message) {
        super(false, message);
    }

    public int getNewFollowerCount() {
        return newFollowerCount;
    }
}
