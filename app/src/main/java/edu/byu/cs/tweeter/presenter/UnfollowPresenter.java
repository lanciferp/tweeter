package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.request.UnfollowRequest;
import edu.byu.cs.tweeter.model.service.response.UnfollowResponse;
import edu.byu.cs.tweeter.model.service.UnfollowService;


public class UnfollowPresenter {
    private final UnfollowPresenter.View view;

    public interface View {
    }

    public UnfollowPresenter(UnfollowPresenter.View view) {
        this.view = view;
    }

    public UnfollowResponse unfollow(UnfollowRequest unfollowRequest) throws IOException {
        UnfollowService unfollowService = new UnfollowService();
        return unfollowService.unfollow(unfollowRequest);
    }
}
