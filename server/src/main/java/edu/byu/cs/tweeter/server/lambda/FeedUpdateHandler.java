package edu.byu.cs.tweeter.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.ArrayList;

import edu.byu.cs.tweeter.server.dao.DTO.FeedUpdateDTO;
import edu.byu.cs.tweeter.server.dao.FeedDAO;

public class FeedUpdateHandler implements RequestHandler<ArrayList<FeedUpdateDTO>, Integer> {
    @Override
    public Integer handleRequest(ArrayList<FeedUpdateDTO> updates, Context context) {
        for (FeedUpdateDTO alias : updates){
            getFeedDAO().addTweet(alias.getFeedOwner(), alias.getStatus());
        }
        return updates.size();
    }

    FeedDAO getFeedDAO(){
        return new FeedDAO();
    }
}
