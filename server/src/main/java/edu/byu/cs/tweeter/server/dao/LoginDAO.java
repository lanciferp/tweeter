package edu.byu.cs.tweeter.server.dao;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;

public class LoginDAO {
    private final User user2 = new User("Signed", "In", FEMALE_IMAGE_URL);
    private static final String FEMALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/twerter/images/daisy_duck.png";

    public LoginResponse login(LoginRequest request){
        assert request.getUsername() != null;
        assert request.getPassword() != null;

        return new LoginResponse(user2, new AuthToken());
    }
}
