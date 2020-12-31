package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.UnfollowService;
import edu.byu.cs.tweeter.model.service.request.UnfollowRequest;
import edu.byu.cs.tweeter.model.service.response.UnfollowResponse;
import edu.byu.cs.tweeter.server.dao.FollowDAO;

public class UnfollowServiceImpl implements UnfollowService {
    @Override
    public UnfollowResponse unfollow(UnfollowRequest request) {
        int newCount =  getFollowDAO().unfollow(request.getExFollower(), request.getUser().getAlias());
        return new UnfollowResponse(newCount);
    }

    FollowDAO getFollowDAO(){
        return new FollowDAO();
    }
}
