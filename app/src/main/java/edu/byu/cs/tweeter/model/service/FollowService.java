package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.request.FollowRequest;
import edu.byu.cs.tweeter.model.service.response.FollowResponse;

public class FollowService {

    public FollowResponse follow(FollowRequest request) throws IOException {
        ServerFacade serverFacade = getServerFacade();
        FollowResponse followResponse = serverFacade.follow(request);

        return followResponse;
    }

    ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}
