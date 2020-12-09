package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.FollowService;
import edu.byu.cs.tweeter.model.service.FollowServiceProxy;
import edu.byu.cs.tweeter.model.service.request.FollowRequest;
import edu.byu.cs.tweeter.model.service.response.FollowResponse;

public class FollowPresenter {

    private final View view;

    public interface View {
    }

    public FollowPresenter(View view) {
        this.view = view;
    }

    public FollowResponse follow(FollowRequest followRequest) throws IOException, TweeterRemoteException {
        FollowService followService = new FollowServiceProxy();
        return followService.follow(followRequest);
    }

}
