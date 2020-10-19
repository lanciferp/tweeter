package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.request.TweetRequest;
import edu.byu.cs.tweeter.model.service.response.TweetResponse;

public class TweetService {

    public TweetResponse tweet(TweetRequest request) throws IOException {
        ServerFacade serverFacade = getServerFacade();
        TweetResponse tweetResponse = serverFacade.tweet(request);

        return tweetResponse;
    }

    ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}
