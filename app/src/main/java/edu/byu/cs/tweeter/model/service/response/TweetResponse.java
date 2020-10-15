package edu.byu.cs.tweeter.model.service.response;

public class TweetResponse extends Response{
    TweetResponse(boolean success) {
        super(success);
    }

    TweetResponse(boolean success, String message) {
        super(success, message);
    }
}
