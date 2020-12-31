package edu.byu.cs.tweeter.server.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import edu.byu.cs.tweeter.server.dao.DTO.StatusDTO;

public class FeedDAO {
    public StatusDTO addTweet(String alias, StatusDTO status){
        // add the new status to their Feed.
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();

        UpdateItemRequest request = new UpdateItemRequest();
        request.setTableName("Feed");
        request.setKey(Collections.singletonMap("Alias",
                new AttributeValue().withS(alias)));
        request.setUpdateExpression("list_append(:prepend_value, UserFeed)");

        Map<String, AttributeValue> statusMap = new HashMap<>();
        statusMap.put("Poster", new AttributeValue(status.getUser()));
        statusMap.put("Message", new AttributeValue(status.getMessage()));
        statusMap.put("Mentions", new AttributeValue(status.getMentions()));
        statusMap.put("TimePosted", new AttributeValue(String.valueOf(status.getTimePosted())));

        request.setExpressionAttributeValues(
                Collections.singletonMap(":prepend_value",
                        new AttributeValue().withM(statusMap))
        );
        client.updateItem(request);
        return status;
    }

    public ArrayList<StatusDTO> getFeed(String alias){
        AmazonDynamoDB client = AmazonDynamoDBAsyncClientBuilder.standard().build();
        DynamoDB dynamoDB = new DynamoDB(client);
        Table table = dynamoDB.getTable("Feed");
        GetItemSpec spec = new GetItemSpec()
                .withPrimaryKey("Alias", alias)
                .withProjectionExpression("Alias, UserFeed");
        Item item = table.getItem(spec);
        Map<String, Object> map = item.asMap();
        String user = (String)map.get("Alias");
        ArrayList<HashMap<String, Object>> feed = (ArrayList<HashMap<String, Object>>) map.get("UserFeed");
        ArrayList<StatusDTO> statuses = new ArrayList<>();
        for (HashMap<String, Object> status : feed){
            StatusDTO dto = new StatusDTO((int)status.get("TimePosted"), (String)status.get("Message"), (ArrayList<String>)status.get("Mentions"), (String)status.get("Poster"));
            statuses.add(dto);
        }

        return statuses;
    }
}
