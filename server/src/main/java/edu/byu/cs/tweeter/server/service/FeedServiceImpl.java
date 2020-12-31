package edu.byu.cs.tweeter.server.service;

import java.util.ArrayList;

import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.FeedService;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import edu.byu.cs.tweeter.server.dao.DTO.StatusDTO;
import edu.byu.cs.tweeter.server.dao.FeedDAO;
import edu.byu.cs.tweeter.server.dao.FollowDAO;
import edu.byu.cs.tweeter.server.dao.UserDAO;

public class FeedServiceImpl implements FeedService {
    @Override
    public FeedResponse getFeed(FeedRequest request) throws TweeterRemoteException {

        // first get the aliases of everyone that the user Follows with the timestamp they started
        // following them
        boolean morePages = false;
        // then get all statuses, or at least the first 10
        ArrayList<StatusDTO> statusDTOS = getFeedDAO().getFeed(request.getFeedSource().getAlias());
        // Add the users to each status and mentions
        ArrayList<Status> statuses = new ArrayList<>();
        for (StatusDTO dto : statusDTOS){
            User poster = getUserDAO().getUser(dto.getUser());
            ArrayList<User> mentions = new ArrayList<>();
            for (String alias : dto.getMentions()){
                User mentioned = getUserDAO().getUser(alias);
                mentions.add(mentioned);
            }
            Status status = new Status(String.valueOf( dto.getTimePosted()), dto.getMessage(), mentions, poster);
            statuses.add(status);
        }
        return new FeedResponse(statuses,morePages);
    }

    UserDAO getUserDAO(){
        return new UserDAO();
    }

    FollowDAO getFollowDAO(){
        return new FollowDAO();
    }

    FeedDAO getFeedDAO(){
        return new FeedDAO();
    }

}
