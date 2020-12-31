package edu.byu.cs.tweeter.model.net;

import java.util.List;

public class TweeterImageException  extends TweeterRemoteException {

    public TweeterImageException(String message, String remoteExceptionType, List<String> remoteStakeTrace) {
        super(message, remoteExceptionType, remoteStakeTrace);
    }
}
