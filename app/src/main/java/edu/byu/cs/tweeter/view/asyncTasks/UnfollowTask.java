package edu.byu.cs.tweeter.view.asyncTasks;

import android.os.AsyncTask;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.UnfollowRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import edu.byu.cs.tweeter.model.service.response.UnfollowResponse;
import edu.byu.cs.tweeter.presenter.UnfollowPresenter;

public class UnfollowTask extends AsyncTask<UnfollowRequest, Void, UnfollowResponse> {
    private final UnfollowPresenter presenter;
    private final Observer observer;
    private Exception exception;

    public interface Observer {
        void newUnfollowCount(UnfollowResponse followResponse);
        void handleExpection(Exception exception);
    }

    public UnfollowTask(UnfollowPresenter presenter, Observer observer){
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }


    @Override
    protected UnfollowResponse doInBackground(UnfollowRequest... unfollowRequests) {
        UnfollowResponse unfollowResponse = null;

        try {
            unfollowResponse = presenter.unfollow(unfollowRequests[0]);
        } catch (IOException | TweeterRemoteException ex) {
            exception = ex;
            unfollowResponse = new UnfollowResponse(exception.getMessage());
            return unfollowResponse;
        }

        return unfollowResponse;
    }

    @Override
    protected void onPostExecute(UnfollowResponse unfollowResponse){
        if(exception != null) {
            observer.handleExpection(exception);
        } else if(unfollowResponse.isSuccess()) {
            observer.newUnfollowCount(unfollowResponse);
        }
    }
}
