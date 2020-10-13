package edu.byu.cs.tweeter.model.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Status {
    private final Date timePosted;
    private final String message;
    private final ArrayList<User> mentions;
    private final User user;

    public Status (Date timePosted, String message, ArrayList<User> mentions, User user) {
        this.timePosted = timePosted;
        this.message = message;
        this.mentions = mentions;
        this.user = user;
    }

    public Date getTimePosted() {
        return timePosted;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<User> getMentions() {
        return mentions;
    }

    public String getFirstName(){
        return user.getFirstName();
    }

    public String getLastName(){
        return user.getLastName();
    }

    public String getName(){
        return user.getName();
    }

    public String getAlias(){
        return getAlias();
    }

    public String getImageUrl() {
        return user.getImageUrl();
    }

    public byte[] getImageBytes() {
        return user.getImageBytes();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return timePosted.equals(status.timePosted) &&
                message.equals(status.message) &&
                mentions.equals(status.mentions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timePosted, message, mentions);
    }

    @Override
    public String toString() {
        return "Status{" +
                "timePosted=" + timePosted +
                ", message='" + message + '\'' +
                ", mentions=" + mentions +
                '}';
    }
}
