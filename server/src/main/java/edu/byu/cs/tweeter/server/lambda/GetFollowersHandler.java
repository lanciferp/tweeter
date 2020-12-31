package edu.byu.cs.tweeter.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.server.service.FollowersServiceImpl;

public class GetFollowersHandler implements RequestHandler<FollowersRequest, FollowersResponse> {
    @Override
    public FollowersResponse handleRequest(FollowersRequest followersRequest, Context context) {
        FollowersServiceImpl service = new FollowersServiceImpl();
        try {
            return service.getFollowers(followersRequest);
        } catch (TweeterRemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}
