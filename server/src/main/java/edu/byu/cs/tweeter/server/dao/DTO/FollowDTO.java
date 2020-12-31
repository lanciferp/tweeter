package edu.byu.cs.tweeter.server.dao.DTO;

public class FollowDTO {
    String folowee;
    String follower;
    int timestamp;

    public FollowDTO(String folowee, String follower, int timestamp) {
        this.folowee = folowee;
        this.follower = follower;
        this.timestamp = timestamp;
    }

    public String getFolowee() {
        return folowee;
    }

    public void setFolowee(String folowee) {
        this.folowee = folowee;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
