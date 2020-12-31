package edu.byu.cs.tweeter.model.service.response;

public class TweetResponse extends Response{

    public TweetResponse() {
        super (false);
    }

    public TweetResponse(boolean success) {
        super(success);
    }

    public TweetResponse(boolean success, String message) {
        super(success, message);
    }

    public TweetResponse(String message) {
        super(false, message);
    }
}
