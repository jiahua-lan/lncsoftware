package cn.lncsoftware.data.common;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by catten on 16/1/15.
 */
public class Connector {
    private static MongoClient mongoClient = new MongoClient();
    private static MongoDatabase mongoDatabase;

    public Connector(String database){
        mongoDatabase = mongoClient.getDatabase(database);
    }

    public MongoCollection<Document> getCollection(String collection){
        return mongoDatabase.getCollection(collection);
    }
}
