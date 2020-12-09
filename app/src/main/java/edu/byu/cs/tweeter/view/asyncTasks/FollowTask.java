package edu.byu.cs.tweeter.view.asyncTasks;

import android.os.AsyncTask;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.FollowRequest;
import edu.byu.cs.tweeter.model.service.response.FollowResponse;
import edu.byu.cs.tweeter.presenter.FollowPresenter;

public class FollowTask extends AsyncTask<FollowRequest, Void, FollowResponse> {
    private final FollowPresenter presenter;
    private final Observer observer;
    private Exception exception;

    public interface Observer {
        void newFollowCount(FollowResponse followResponse);
        void handleExpection(Exception exception);
    }

    public FollowTask(FollowPresenter presenter, Observer observer) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected FollowResponse doInBackground(FollowRequest... followRequests) {
        FollowResponse followResponse = null;

        try {
            followResponse = presenter.follow(followRequests[0]);
        } catch (IOException | TweeterRemoteException ex) {
            exception = ex;
        }

        return followResponse;
    }

    @Override
    protected void onPostExecute(FollowResponse followResponse){
        if(exception != null) {
            observer.handleExpection(exception);
        } else if(followResponse.isSuccess()) {
            observer.newFollowCount(followResponse);
        }
    }
}
