package edu.byu.cs.tweeter.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.TweetService;
import edu.byu.cs.tweeter.model.service.request.TweetRequest;
import edu.byu.cs.tweeter.model.service.response.TweetResponse;
import edu.byu.cs.tweeter.server.service.TweetServiceImpl;

public class TweetHandler implements RequestHandler<TweetRequest, TweetResponse> {
    @Override
    public TweetResponse handleRequest(TweetRequest request, Context context) {
        TweetService service = new TweetServiceImpl();
        try {
            return service.tweet(request);
        } catch (IOException | TweeterRemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}
