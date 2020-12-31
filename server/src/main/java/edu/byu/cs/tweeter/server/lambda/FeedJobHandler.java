package edu.byu.cs.tweeter.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;

import java.util.ArrayList;

import edu.byu.cs.tweeter.server.dao.DTO.FeedUpdateDTO;

public class FeedJobHandler implements RequestHandler<ArrayList<FeedUpdateDTO>, Boolean> {
    @Override
    public Boolean handleRequest(ArrayList<FeedUpdateDTO> feedDTOS, Context context) {
        // chop the array list into smaller jobs and pass those jobs along to another queue
        AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
        int i = 0;
        int j = 0;
        for (i = 0; i + 25 < feedDTOS.size(); i += 25){
            j += 25;
            ArrayList<FeedUpdateDTO> smallerBite  = new ArrayList<>(feedDTOS.subList(i,j));
            // insert into queue
            SendMessageRequest send_msg_request = new SendMessageRequest("https://sqs.us-west-2.amazonaws.com/614553303574/JobsQ",
                   smallerBite.toString());
            sqs.sendMessage(send_msg_request);
        }

        // do the rest
        ArrayList<FeedUpdateDTO> smallerBite  = new ArrayList<>(feedDTOS.subList(i,feedDTOS.size()));

        SendMessageRequest send_msg_request = new SendMessageRequest("https://sqs.us-west-2.amazonaws.com/614553303574/JobsQ",
                smallerBite.toString());
        sqs.sendMessage(send_msg_request);
        return true;
    }
}
