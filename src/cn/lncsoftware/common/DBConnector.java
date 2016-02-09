package cn.lncsoftware.common;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Created by catten on 16/1/15.
 */
public class DBConnector {
    private static MongoClient mongoClient = getMongoClient();

    public static MongoClient getMongoClient(){
        if(mongoClient == null){
            mongoClient = new MongoClient();
        }
        return mongoClient;
    }

    public static MongoDatabase getDatabase(String database){
        return mongoClient.getDatabase(database);
    }
}
