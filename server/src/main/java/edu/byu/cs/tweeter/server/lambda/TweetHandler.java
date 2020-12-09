package edu.byu.cs.tweeter.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.service.request.TweetRequest;
import edu.byu.cs.tweeter.model.service.response.TweetResponse;

public class TweetHandler implements RequestHandler<TweetRequest, TweetResponse> {
    @Override
    public TweetResponse handleRequest(TweetRequest request, Context context) {
        return null;
    }
}
