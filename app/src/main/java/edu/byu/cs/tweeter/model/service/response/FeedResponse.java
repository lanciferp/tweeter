package edu.byu.cs.tweeter.model.service.response;

import java.util.List;
import java.util.Objects;

import edu.byu.cs.tweeter.model.domain.Status;

public class FeedResponse extends PagedResponse{
    private List<Status> statuses;

    /**
     * Creates a response indicating the request failed and passing along the message
     *
     * @param message a message describing the failure
     */
    public FeedResponse(String message) {
        super(false, message, false);
    }

    /**
     * Creates a successful response
     *
     * @param statuses The statuses for the story corresponding to the user
     * @param hasMorePages an indicator of there being more data or not
     */
    public FeedResponse(List<Status> statuses, boolean hasMorePages) {
        super(true, hasMorePages);
        this.statuses = statuses;
    }

    /**
     * Returns statuses for the request
     *
     * @return statuses for the story
     */
    public List<Status> getStatuses() {
        return statuses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedResponse that = (FeedResponse) o;
        return Objects.equals(statuses, that.statuses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statuses);
    }
}
