package edu.byu.cs.tweeter.server.dao;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;

public class RegisterDAO {
    private final User user2 = new User("Signed", "In", FEMALE_IMAGE_URL);
    private static final String FEMALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/twerter/images/daisy_duck.png";

    public RegisterResponse register(RegisterRequest request){
        assert request.getUsername() != null;
        assert request.getPassword() != null;

        return new RegisterResponse(user2, new AuthToken());
    }
}
