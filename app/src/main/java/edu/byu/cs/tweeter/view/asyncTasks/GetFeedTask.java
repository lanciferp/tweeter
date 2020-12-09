package edu.byu.cs.tweeter.view.asyncTasks;

import android.os.AsyncTask;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.util.ByteArrayUtils;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import edu.byu.cs.tweeter.presenter.FeedPresenter;

public class GetFeedTask extends AsyncTask<FeedRequest, Void, FeedResponse> {

    private final FeedPresenter presenter;
    private final edu.byu.cs.tweeter.view.asyncTasks.GetFeedTask.Observer observer;
    private Exception exception;

    /**
     * An observer interface any observer who wants to be notified about this task
     * can implement
     */
    public interface Observer {
        void statusesRetrieved(FeedResponse feedResponse);
        void handleException(Exception exception);
    }

    public GetFeedTask(FeedPresenter presenter,
                        edu.byu.cs.tweeter.view.asyncTasks.GetFeedTask.Observer observer){
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected FeedResponse doInBackground(FeedRequest... feedRequests) {
        FeedResponse response = null;

        try{
            response = presenter.getFeed(feedRequests[0]);
        } catch (IOException | TweeterRemoteException ex) {
            exception = ex;
        }

        try {
            assert response != null;
            loadImages(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
    private void loadImages(FeedResponse response) throws IOException {
        for(edu.byu.cs.tweeter.model.domain.Status status : response.getStatuses()) {
            byte[] bytes = ByteArrayUtils.bytesFromUrl(status.getImageUrl());
            status.setImageBytes(bytes);
        }
    }

    @Override
    protected void onPostExecute(FeedResponse feedResponse) {
        if(exception != null) {
            observer.handleException(exception);
        } else {
            observer.statusesRetrieved(feedResponse);
        }
    }
}