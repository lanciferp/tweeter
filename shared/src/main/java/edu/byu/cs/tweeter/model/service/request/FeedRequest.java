package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

public class FeedRequest {
    private User feedSource;
    private int limit;
    private Status lastStatus;

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

    public void setFeedSource(User feedSource) {
        this.feedSource = feedSource;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setLastStatus(Status lastStatus) {
        this.lastStatus = lastStatus;
    }
}
