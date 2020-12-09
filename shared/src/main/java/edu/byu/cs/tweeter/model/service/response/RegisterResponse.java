package edu.byu.cs.tweeter.model.service.response;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class RegisterResponse extends Response{
    private User user;
    private AuthToken authToken;

    public RegisterResponse(String message){
        super(false, message);
    }

    public RegisterResponse(User user,AuthToken authtoken) {
        super(true, null);
        this.user = user;
        this.authToken = authtoken;
    }

    public User getUser() {
        return user;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }
}
