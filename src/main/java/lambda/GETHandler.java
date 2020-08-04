package lambda;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;


import java.util.HashMap;

// Handler value: lambda.GETHandler
public class GETHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        LambdaLogger logger = context.getLogger();

        //----------writing dynamo code -----

        Region region = Region.AP_SOUTH_1;
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion(region.toString()).build();


        DynamoDB dynamoDB = new DynamoDB(client);

        //---enfing cred -----

        Table table = dynamoDB.getTable("menu");

        try {
            table.putItem(new Item().withPrimaryKey("menuId", "3-1234-dejoe").withBoolean("veg", true));

        } catch (Exception e) {
            logger.log(e.getMessage());
        }


        //---------ending dynamo code ----

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setIsBase64Encoded(false);
        response.setStatusCode(200);
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "text/html");
        response.setHeaders(headers);
        response.setBody("Hello There! Dejoe here.");
        // log execution details
//        Util.logEnvironment(event, context, gson);
        return response;
    }
}