package edu.byu.cs.tweeter.server.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;

import java.util.ArrayList;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.TweetService;
import edu.byu.cs.tweeter.model.service.request.TweetRequest;
import edu.byu.cs.tweeter.model.service.response.TweetResponse;
import edu.byu.cs.tweeter.server.dao.DTO.FeedUpdateDTO;
import edu.byu.cs.tweeter.server.dao.DTO.FollowDTO;
import edu.byu.cs.tweeter.server.dao.DTO.StatusDTO;
import edu.byu.cs.tweeter.server.dao.FeedDAO;
import edu.byu.cs.tweeter.server.dao.FollowDAO;
import edu.byu.cs.tweeter.server.dao.StoryDAO;

public class TweetServiceImpl implements TweetService {
    @Override
    public TweetResponse tweet(TweetRequest request) {
        StoryDAO storyDAO = getStoryDAO();
        FeedDAO feedDAO = getFeedDAO();

        ArrayList<String> mentions = new ArrayList<>();
        for (User user : request.getStatus().getMentions()){
            mentions.add(user.getAlias());
        }
        StatusDTO dto = new StatusDTO((int) System.currentTimeMillis(), request.getStatus().getMessage(), mentions, request.getStatus().getAlias());
        // for feed first get the list of people that follow the user
        boolean hasPages = false;
        ArrayList<FollowDTO> followers = getFollowDAO().getFollowers(request.getStatus().getAlias(), hasPages);
        ArrayList<FeedUpdateDTO> updates = new ArrayList<>();
        for (FollowDTO follower: followers) {
            FeedUpdateDTO update = new FeedUpdateDTO(follower.getFollower(), dto);
            updates.add(update);
        }
        AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
        SendMessageRequest send_msg_request = new SendMessageRequest("https://sqs.us-west-2.amazonaws.com/614553303574/PostsQ",
                updates.toString());
        sqs.sendMessage(send_msg_request);

        // pretend like you're done and tell the user that we finished.

        storyDAO.addTweet(dto);
        return new TweetResponse(true);
    }

    StoryDAO getStoryDAO() {
        return new StoryDAO();
    }

    FeedDAO getFeedDAO() {
        return new FeedDAO();
    }

    FollowDAO getFollowDAO() {
    return new FollowDAO();}
}

