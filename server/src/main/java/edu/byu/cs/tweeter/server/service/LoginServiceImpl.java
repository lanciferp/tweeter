package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.LoginService;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.server.dao.UserDAO;

public class LoginServiceImpl implements LoginService {

    @Override
    public LoginResponse login(LoginRequest request) throws TweeterRemoteException {
        //check the password
        User user = getUserDAO().getUser(request.getUsername());
        LoginResponse response;
        if(user != null) {
            response = new LoginResponse(user, new AuthToken());
        }
        else {
            response = new LoginResponse();
        }
        return response;
    }

    UserDAO getUserDAO(){
        return new UserDAO();
    }
}
