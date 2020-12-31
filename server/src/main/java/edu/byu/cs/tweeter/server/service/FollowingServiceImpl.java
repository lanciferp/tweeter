package edu.byu.cs.tweeter.server.service;

import java.util.ArrayList;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.FollowingService;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.server.dao.DTO.FollowDTO;
import edu.byu.cs.tweeter.server.dao.FollowDAO;
import edu.byu.cs.tweeter.server.dao.UserDAO;

/**
 * Contains the business logic for getting the users a user is following.
 */
public class FollowingServiceImpl implements FollowingService {

    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned in a previous request. Uses the {@link FollowingDAO} to
     * get the followees.
     *
     * @param request contains the data required to fulfill the request.
     * @return the followees.
     */
    @Override
    public FollowingResponse getFollowees(FollowingRequest request) throws TweeterRemoteException {
        boolean morePages = false;
        ArrayList<FollowDTO> following = getFollowDAO().getFollowing(request.getFollower().getAlias(), morePages);
        ArrayList<String> aliases = new ArrayList<>();
        for (FollowDTO follow : following){
            String alias = follow.getFolowee();
            aliases.add(alias);
        }
        ArrayList<User> users = getUserDAO().getUsers(aliases);

        return new FollowingResponse(users, morePages);
    }

    UserDAO getUserDAO(){
        return new UserDAO();
    }
    FollowDAO getFollowDAO(){
        return new FollowDAO();
    }

}
