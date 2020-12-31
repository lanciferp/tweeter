package edu.byu.cs.tweeter.model.service.response;

public class LogoutResponse extends Response{

    public LogoutResponse(){
        super(false);
    }

    public LogoutResponse(boolean success) {
        super(success);
    }
}
