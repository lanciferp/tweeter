package edu.byu.cs.tweeter.server.dao.DTO;

import java.util.ArrayList;

public class StatusDTO {
    private final int timePosted;
    private final String message;
    private final ArrayList<String> mentions;
    private final String user;

    public StatusDTO(int timePosted, String message, ArrayList<String> mentions, String user) {
        this.timePosted = timePosted;
        this.message = message;
        this.mentions = mentions;
        this.user = user;
    }

    public int getTimePosted() {
        return timePosted;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<String> getMentions() {
        return mentions;
    }

    public String getUser() {
        return user;
    }
}
