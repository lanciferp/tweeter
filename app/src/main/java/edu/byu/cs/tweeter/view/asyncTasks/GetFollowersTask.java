package edu.byu.cs.tweeter.view.asyncTasks;

import android.os.AsyncTask;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterImageException;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.util.ByteArrayUtils;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.presenter.FollowersPresenter;
import edu.byu.cs.tweeter.util.ImageUtils;

/**
 * An {@link AsyncTask} for retrieving followees for a user.
 */
public class GetFollowersTask extends AsyncTask<FollowersRequest, Void, FollowersResponse> {

    private final FollowersPresenter presenter;
    private final edu.byu.cs.tweeter.view.asyncTasks.GetFollowersTask.Observer observer;
    private Exception exception;

    /**
     * An observer interface to be implemented by observers who want to be notified when this task
     * completes.
     */
    public interface Observer {
        void followersRetrieved(FollowersResponse followersResponse);
        void handleException(Exception exception);
    }

    /**
     * Creates an instance.
     *
     * @param presenter the presenter from whom this task should retrieve followees.
     * @param observer the observer who wants to be notified when this task completes.
     */
    public GetFollowersTask(FollowersPresenter presenter, edu.byu.cs.tweeter.view.asyncTasks.GetFollowersTask.Observer observer) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }

    /**
     * The method that is invoked on the background thread to retrieve followees. This method is
     * invoked indirectly by calling {@link #execute(FollowersRequest...)}.
     *
     * @param followersRequests the request object (there will only be one).
     * @return the response.
     */
    @Override
    protected FollowersResponse doInBackground(FollowersRequest... followersRequests) {
        FollowersResponse response = null;

        try {
            response = presenter.getFollowers(followersRequests[0]);
        } catch (IOException | TweeterRemoteException ex) {
            exception = ex;
            response = new FollowersResponse(exception.getMessage());
            return response;
        }

        try {
            assert response != null;
            loadImages(response);
        } catch (TweeterImageException e) {
            observer.handleException(e);
        }
        return response;
    }

    /**
     * Loads the profile image data for each followee included in the response.
     *
     * @param response the response from the followee request.
     */
    private void loadImages(FollowersResponse response) throws TweeterImageException {
        for(User user : response.getFollowers()) {
            ImageUtils.loadImage(user);
        }
    }

    /**
     * Notifies the observer (on the UI thread) when the task completes.
     *
     * @param followersResponse the response that was received by the task.
     */
    @Override
    protected void onPostExecute(FollowersResponse followersResponse) {
        if(exception != null) {
            observer.handleException(exception);
        } else {
            observer.followersRetrieved(followersResponse);
        }
    }
}
