package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.FeedService;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;

public class FeedPresenter {

    private final View view;

    public interface View {
        //I'm not entirely sure what this does.
    }

    /**
     * Creates an instance.
     *
     * @param view the view for which this class is the presenter
     */
    public FeedPresenter(View view) {
        this.view = view;
    }

    public FeedResponse getFeed(FeedRequest request) throws IOException {
        FeedService feedService = getFeedService();
        return feedService.getFeed(request);
    }

    FeedService getFeedService(){
        return new FeedService();
    }

}
