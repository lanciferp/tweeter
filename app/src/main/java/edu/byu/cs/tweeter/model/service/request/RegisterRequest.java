package edu.byu.cs.tweeter.model.service.request;

public class RegisterRequest {
    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String imageURL;

    public RegisterRequest(String username, String password, String firstName, String lastName, String imageURL) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageURL = imageURL;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getImageURL() {
        return imageURL;
    }
}
