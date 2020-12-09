package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.TweetService;
import edu.byu.cs.tweeter.model.service.TweetServiceProxy;
import edu.byu.cs.tweeter.model.service.request.TweetRequest;
import edu.byu.cs.tweeter.model.service.response.TweetResponse;

public class TweetPresenter {

    private final View view;

    public interface View {

    }

    public TweetPresenter(View view) {
        this.view = view;
    }

    public TweetResponse tweet(TweetRequest tweetRequest) throws IOException, TweeterRemoteException {
        TweetService tweetService = new TweetServiceProxy();
        return tweetService.tweet(tweetRequest);
    }
 }
