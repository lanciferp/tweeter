package edu.byu.cs.tweeter.server.dao;

import edu.byu.cs.tweeter.model.service.request.TweetRequest;
import edu.byu.cs.tweeter.model.service.response.TweetResponse;

public class TweetDAO {
    public TweetResponse tweet(TweetRequest request){
        assert request.getStatus() != null;

        return new TweetResponse(true);
    }
}
