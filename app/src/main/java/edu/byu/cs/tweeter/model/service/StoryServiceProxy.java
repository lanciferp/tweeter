package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;

public class StoryServiceProxy implements StoryService{

    static final String URL_PATH = "/getstory";

    public StoryResponse getStory(StoryRequest request) throws IOException, TweeterRemoteException {
        ServerFacade serverFacade = getServerFacade();
        StoryResponse storyResponse = serverFacade.getStory(request, URL_PATH);
        return storyResponse;
    }

    ServerFacade getServerFacade() {
        return new ServerFacade();
    }

}
