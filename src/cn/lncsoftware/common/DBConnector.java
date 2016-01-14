package cn.lncsoftware.common;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Created by catten on 16/1/15.
 */
public class DBConnector {
    private static MongoClient mongoClient = null;

    private DBConnector(){
        if(mongoClient == null){
            mongoClient = new MongoClient();
        }
    }

    private static final DBConnector connector = new DBConnector();

    /*public static DBConnector getInstance(){
        return connector;
    }*/

    public static MongoDatabase getDatabase(){
        return mongoClient.getDatabase("lncsoftware");
    }
}
