package edu.byu.cs.tweeter.server.helper;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;

public class Main {
    public static void main(String[] args) throws TweeterRemoteException {
        Filler.fillDatabase();
    }
}
