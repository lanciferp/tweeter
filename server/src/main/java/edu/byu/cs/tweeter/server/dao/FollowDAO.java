package edu.byu.cs.tweeter.server.dao;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowRequest;
import edu.byu.cs.tweeter.model.service.response.FollowResponse;
import edu.byu.cs.tweeter.server.dao.DTO.FollowDTO;

public class FollowDAO {
    public FollowResponse follow(FollowRequest request){
        AmazonDynamoDB client = AmazonDynamoDBAsyncClientBuilder.standard().build();
        DynamoDB dynamoDB = new DynamoDB(client);
        Table table = dynamoDB.getTable("Follow");

        Item item = new Item()
                .withPrimaryKey("Follower", request.getUser().getAlias())
                .withString("FirstName", request.getFollowee())
                .withNumber("Timestamp", System.currentTimeMillis());

        PutItemOutcome outcome = table.putItem(item);

        //get the new number of followers
        QueryRequest queryRequest = new QueryRequest()
                .withTableName("Follow")
                .withKeyConditionExpression("# =" + request.getFollowee());
        return null;
    }

    public ArrayList<FollowDTO> getFollowing(String follower, boolean morePages) {
        //query table and get a list of all of the aliases that the User follows
    return null;
    }

    public ArrayList<FollowDTO> getFollowers(String followee, boolean morePages){
        //query table and get list of all the aliases of users that follow follows
        return null;
    }

    public int getFoloweeCount(String follower){
        //find how many users this user is following
        return getFollowing(follower, false).size();
    }

    public int getFollowersCount(String following){
        return getFollowers(following, false).size();
    }

    public int unfollow(String followerAlias, String foloweeAlias) {
        return 0;
    }
}
