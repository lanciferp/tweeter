package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

public class StoryRequest {

    private final User storySource;
    private final int limit;
    private final Status lastStatus;

    /**
     *
     * @param storySource the {@link User} whose story should be returned.
     * @param limit the maximum numbers of statuses to return.
     * @param lastStatus the last status returned in the previous request, null is there was no
     *                   previous request or if no statuses were returned.
     */
    public StoryRequest(User storySource, int limit, Status lastStatus) {
        this.storySource = storySource;
        this.limit = limit;
        this.lastStatus = lastStatus;
    }

    public User getStorySource() {
        return storySource;
    }

    public int getLimit() {
        return limit;
    }

    public Status getLastStatus() {
        return lastStatus;
    }
}
