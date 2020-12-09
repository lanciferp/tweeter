package edu.byu.cs.tweeter.model.service;

import java.io.IOException;
import java.net.URL;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;

public class FollowersServiceProxy implements FollowersService{

    static final String URL_PATH = "/getfollowers";

    @Override
    public FollowersResponse getFollowers(FollowersRequest request) throws IOException, TweeterRemoteException {
        ServerFacade serverFacade = getServerFacade();
        FollowersResponse followersResponse = serverFacade.getFollowers(request, URL_PATH);
        return followersResponse;
    }

    ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}
