package edu.byu.cs.tweeter.server.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.BatchGetItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableKeysAndAttributes;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;

public class UserDAO {
    public User getUser(String alias) throws TweeterRemoteException {
        AmazonDynamoDB client = AmazonDynamoDBAsyncClientBuilder.standard().
                withRegion("us-west-2").build();
        DynamoDB dynamoDB = new DynamoDB(client);
        Table table = dynamoDB.getTable("User");

        GetItemSpec spec = new GetItemSpec()
                .withPrimaryKey("Alias", alias);
        Item item;
        try{
            item = table.getItem(spec);
        } catch (Exception e){
            throw new TweeterRemoteException("GetUser - " + e.getMessage() + " " + alias, "User", null);
        }
        System.out.println(item.getString("alias"));

        return new User(item.getString("FirstName"), item.getString("LastName"),
                item.getString("Alias"), item.getString("ImageURL"));
    }
    public ArrayList<User> getUsers(ArrayList<String> aliases) throws TweeterRemoteException {
        //TODO: Add Pagination
        AmazonDynamoDB client = AmazonDynamoDBAsyncClientBuilder.standard().
                withRegion("us-west-2").build();
        DynamoDB dynamoDB = new DynamoDB(client);
        TableKeysAndAttributes userTableKeysAndAttributes = new TableKeysAndAttributes("User");
        for (String alias : aliases){
            userTableKeysAndAttributes.addHashOnlyPrimaryKey("Alias", alias);
        }
        BatchGetItemOutcome outcome;
        try {
            outcome = dynamoDB.batchGetItem(userTableKeysAndAttributes);
        } catch(Exception e){
            throw new TweeterRemoteException(e.getMessage(), "BatchUsers", null);
        }

        ArrayList<User> users = new ArrayList<>();
        for (String tableName : outcome.getTableItems().keySet()) {
            System.out.println("Items in table " + tableName);
            List<Item> items = outcome.getTableItems().get(tableName);
            for (Item item : items) {
                users.add(new User(item.getString("FirstName"), item.getString("LastName"),
                        item.getString("Alias"), item.getString("ImageURL")));
            }
        }
        return users;
    }

    public User addUser(String alias, String firstName, String lastName, String imageURL) throws TweeterRemoteException{
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion("us-west-2").build();
        DynamoDB dynamoDB = new DynamoDB(client);
        Table table = dynamoDB.getTable("User");

        Item item = new Item()
                .withPrimaryKey("Alias", alias)
                .withString("FirstName", firstName)
                .withString("LastName", lastName)
                .withString("ImageURL", imageURL);
        try {
            table.putItem(item);
        }
        catch(Exception e){
            System.err.println("Unable to add item: " + alias);
            System.err.println(e.getMessage());
            throw new TweeterRemoteException(e.getMessage(), "User", null);
        }
        return new User(firstName,lastName,alias, imageURL);
    }

}
