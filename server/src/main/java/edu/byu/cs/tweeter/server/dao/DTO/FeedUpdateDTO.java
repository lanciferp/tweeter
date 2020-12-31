package edu.byu.cs.tweeter.server.dao.DTO;

public class FeedUpdateDTO {
    String feedOwner;
    StatusDTO status;

    public FeedUpdateDTO() {
    }

    public FeedUpdateDTO(String personsFeed, StatusDTO status) {
        this.feedOwner = personsFeed;
        this.status = status;
    }

    public String getFeedOwner() {
        return feedOwner;
    }

    public void setFeedOwner(String feedOwner) {
        this.feedOwner = feedOwner;
    }

    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FeedUpdateDTO{" +
                "feedOwner='" + feedOwner + '\'' +
                ", status=" + status +
                '}';
    }
}
