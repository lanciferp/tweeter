package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.RegisterService;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;
import edu.byu.cs.tweeter.server.dao.UserDAO;

public class RegisterServiceImpl implements RegisterService {
    @Override
    public RegisterResponse register(RegisterRequest request) throws TweeterRemoteException {
        User user = getUserDAO().addUser(request.getUsername(), request.getFirstName(),
                request.getLastName(), request.getImageURL());
        return new RegisterResponse(user, new AuthToken());
    }

    UserDAO getUserDAO(){
        return new UserDAO();
    }
}
