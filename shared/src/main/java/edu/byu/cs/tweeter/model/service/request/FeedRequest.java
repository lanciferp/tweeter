package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

public class FeedRequest {
    private final User feedSource;
    private final int limit;
    private final Status lastStatus;

    public FeedRequest() {
        feedSource = null;
        limit = 0;
        lastStatus = null;
    }

    /**
     *
     * @param feedSource the {@link User} whose feed should be returned.
     * @param limit the maximum numbers of statuses to return.
     * @param lastStatus the last status returned in the previous request, null is there was no
     *                   previous request or if no statuses were returned.
     */
    public FeedRequest(User feedSource, int limit, Status lastStatus) {
        this.feedSource = feedSource;
        this.limit = limit;
        this.lastStatus = lastStatus;
    }

    public User getFeedSource() {
        return feedSource;
    }

    public int getLimit() {
        return limit;
    }

    public Status getLastStatus() {
        return lastStatus;
    }
}
