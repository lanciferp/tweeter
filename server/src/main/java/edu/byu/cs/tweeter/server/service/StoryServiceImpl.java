package edu.byu.cs.tweeter.server.service;

import java.util.ArrayList;

import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.StoryService;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;
import edu.byu.cs.tweeter.server.dao.DTO.StatusDTO;
import edu.byu.cs.tweeter.server.dao.StoryDAO;
import edu.byu.cs.tweeter.server.dao.UserDAO;

public class StoryServiceImpl implements StoryService {
    @Override
    public StoryResponse getStory(StoryRequest request) throws TweeterRemoteException {

        // first get the aliases of everyone that the user Follows with the timestamp they started
        // following them
        // then get all statuses, or at least the first 10
        boolean morePages = false;
        ArrayList<StatusDTO> statusDTOS = getStoryDAO().getStory(request.getStorySource().getAlias());
        // Add the users to each status and mentions
        ArrayList<Status> statuses = new ArrayList<>();
        for (StatusDTO dto : statusDTOS){
            User poster = getUserDAO().getUser(dto.getUser());
            ArrayList<User> mentions = new ArrayList<>();
            for (String alias : dto.getMentions()){
                User mentioned = getUserDAO().getUser(alias);
                mentions.add(mentioned);
            }
            Status status = new Status(String.valueOf(dto.getTimePosted()), dto.getMessage(), mentions, poster);
            statuses.add(status);
        }
        return new StoryResponse(statuses,morePages);
    }

    UserDAO getUserDAO(){
        return new UserDAO();
    }

    StoryDAO getStoryDAO(){
        return new StoryDAO();
    }
}
