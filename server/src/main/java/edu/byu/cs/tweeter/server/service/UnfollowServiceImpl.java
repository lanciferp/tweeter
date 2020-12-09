package edu.byu.cs.tweeter.server.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.UnfollowService;
import edu.byu.cs.tweeter.model.service.request.UnfollowRequest;
import edu.byu.cs.tweeter.model.service.response.UnfollowResponse;
import edu.byu.cs.tweeter.server.dao.UnfollowDAO;

public class UnfollowServiceImpl implements UnfollowService {
    @Override
    public UnfollowResponse unfollow(UnfollowRequest request) {
        return getUnfollowDAO().unfollow(request);
    }

    UnfollowDAO getUnfollowDAO(){
        return new UnfollowDAO();
    }
}