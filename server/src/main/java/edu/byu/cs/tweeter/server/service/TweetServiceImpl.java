package edu.byu.cs.tweeter.server.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.TweetService;
import edu.byu.cs.tweeter.model.service.request.TweetRequest;
import edu.byu.cs.tweeter.model.service.response.TweetResponse;
import edu.byu.cs.tweeter.server.dao.TweetDAO;

public class TweetServiceImpl implements TweetService {
    @Override
    public TweetResponse tweet(TweetRequest request) throws IOException, TweeterRemoteException {
        return getTweetDAO().tweet(request);
    }

    TweetDAO getTweetDAO(){
        return new TweetDAO();
    }
}
