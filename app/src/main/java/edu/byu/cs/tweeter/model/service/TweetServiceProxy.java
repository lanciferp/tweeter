package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.TweetRequest;
import edu.byu.cs.tweeter.model.service.response.TweetResponse;

public class TweetServiceProxy implements TweetService{

    static final String URL_PATH = "/tweet";

    public TweetResponse tweet(TweetRequest request) throws IOException, TweeterRemoteException {
        ServerFacade serverFacade = getServerFacade();
        TweetResponse tweetResponse = serverFacade.tweet(request, URL_PATH);

        return tweetResponse;
    }

    ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}
