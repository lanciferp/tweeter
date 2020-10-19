package edu.byu.cs.tweeter.view.asyncTasks;

import android.os.AsyncTask;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.request.TweetRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.model.service.response.TweetResponse;
import edu.byu.cs.tweeter.presenter.TweetPresenter;

public class TweetTask extends AsyncTask<TweetRequest, Void, TweetResponse> {

    private final TweetPresenter presenter;
    private final Observer observer;
    private Exception exception;

    public interface Observer {
        void tweetSuccessful(TweetResponse response);
        void tweetFailed(TweetResponse response);
        void handleException(Exception ex);
    }

    public TweetTask(TweetPresenter presenter, Observer observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected TweetResponse doInBackground(TweetRequest... tweetRequests) {
        TweetResponse response = null;

        try {
            response = presenter.tweet(tweetRequests[0]);
        } catch (IOException e) {
            exception = e;
        }

        return response;
    }

    @Override
    protected void onPostExecute(TweetResponse tweetResponse) {
        if(exception != null) {
            observer.handleException(exception);
        } else if(tweetResponse.isSuccess()) {
            observer.tweetSuccessful(tweetResponse);
        } else {
            observer.tweetFailed(tweetResponse);
        }
    }
}
