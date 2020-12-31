package edu.byu.cs.tweeter.model.service.request;

public class RegisterRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String imageURL;

    public RegisterRequest() {
        username = null;
        password = null;
        firstName = null;
        lastName = null;
        imageURL = null;
    }

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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
