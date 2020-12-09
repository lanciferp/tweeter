package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.StoryService;
import edu.byu.cs.tweeter.model.service.StoryServiceProxy;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;

public class StoryPresenter {

    private final View view;

    public interface View {
        //I'm not entirely sure what this does.
    }

    /**
     * Creates an instance.
     *
     * @param view the view for which this class is the presenter
     */
    public StoryPresenter(View view) {
        this.view = view;
    }

    public StoryResponse getStory(StoryRequest request) throws IOException, TweeterRemoteException {
        StoryService storyService = getStoryService();
        return storyService.getStory(request);
    }

    StoryService getStoryService(){
        return new StoryServiceProxy();
    }

}
