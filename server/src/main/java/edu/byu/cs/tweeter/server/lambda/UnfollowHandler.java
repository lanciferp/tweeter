package edu.byu.cs.tweeter.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.UnfollowService;
import edu.byu.cs.tweeter.model.service.request.UnfollowRequest;
import edu.byu.cs.tweeter.model.service.response.UnfollowResponse;
import edu.byu.cs.tweeter.server.service.UnfollowServiceImpl;

public class UnfollowHandler implements RequestHandler<UnfollowRequest, UnfollowResponse> {
    @Override
    public UnfollowResponse handleRequest(UnfollowRequest unfollowRequest, Context context) {
        UnfollowService service = new UnfollowServiceImpl();
        UnfollowResponse unfollow = new UnfollowResponse();
        try {
            unfollow = service.unfollow(unfollowRequest);
        } catch (IOException | TweeterRemoteException e) {
            e.printStackTrace();
        }
        return unfollow;
    }
}
