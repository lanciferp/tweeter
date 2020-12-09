package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;

public class FeedServiceProxy implements FeedService{

    static final String URL_PATH = "/getfeed";


    @Override
    public FeedResponse getFeed(FeedRequest request) throws IOException, TweeterRemoteException {
        return getServerFacade().getFeed(request, URL_PATH);
    }

    ServerFacade getServerFacade() {
        return new ServerFacade();
    }

}
