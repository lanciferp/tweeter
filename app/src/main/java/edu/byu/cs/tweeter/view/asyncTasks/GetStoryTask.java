package edu.byu.cs.tweeter.view.asyncTasks;

import android.os.AsyncTask;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;
import edu.byu.cs.tweeter.presenter.StoryPresenter;

public class GetStoryTask extends AsyncTask<StoryRequest, Void, StoryResponse> {

    private final StoryPresenter presenter;
    private final edu.byu.cs.tweeter.view.asyncTasks.GetStoryTask.Observer observer;
    private Exception exception;

    /**
     * An observer interface any observer who wants to be notified about this task
     * can implement
     */
    public interface Observer {
        void statusesRetrieved(StoryResponse storyResponse);
        void handleException(Exception exception);
    }

    public GetStoryTask(StoryPresenter presenter,
                        edu.byu.cs.tweeter.view.asyncTasks.GetStoryTask.Observer observer){
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected StoryResponse doInBackground(StoryRequest... storyRequests) {
        StoryResponse response = null;

        try{
            response = presenter.getStory(storyRequests[0]);
        } catch (IOException ex) {
            exception = ex;
        }

        return response;
    }
    
    @Override
    protected void onPostExecute(StoryResponse storyResponse) {
        if(exception != null) {
            observer.handleException(exception);
        } else {
            observer.statusesRetrieved(storyResponse);
        }
    }
}
