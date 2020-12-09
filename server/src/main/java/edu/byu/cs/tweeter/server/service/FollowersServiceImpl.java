package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.server.dao.FollowersDAO;

public class FollowersServiceImpl {

    public FollowersResponse getFollowers(FollowersRequest request) {
        return getFollowersDAO().getFollowers(request);
    }

    private FollowersDAO getFollowersDAO() {
        return new FollowersDAO();
    }
}
