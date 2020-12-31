package edu.byu.cs.tweeter.server.service;

import java.util.ArrayList;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.server.dao.DTO.FollowDTO;
import edu.byu.cs.tweeter.server.dao.FollowDAO;
import edu.byu.cs.tweeter.server.dao.UserDAO;

public class FollowersServiceImpl {

    public FollowersResponse getFollowers(FollowersRequest request) throws TweeterRemoteException {
        // first get list of follow dtos for everyone that follows the user
        boolean morePages = false;
        ArrayList<FollowDTO> followers = getFollowDAO().getFollowers(request.getFollowee().getAlias(), morePages);
        ArrayList<String> aliases = new ArrayList<>();
        for (FollowDTO follow : followers){
            String alias = follow.getFollower();
            aliases.add(alias);
        }
        ArrayList<User> users = getUserDAO().getUsers(aliases);
        return new FollowersResponse(users, morePages);
    }

    UserDAO getUserDAO(){
        return new UserDAO();
    }
    FollowDAO getFollowDAO(){
        return new FollowDAO();
    }
}
