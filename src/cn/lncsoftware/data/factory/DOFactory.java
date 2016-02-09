package cn.lncsoftware.data.factory;

import cn.lncsoftware.data.DataObject;
import cn.lncsoftware.data.common.Connector;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Created by catten on 16/2/3.
 */
public abstract class DOFactory<T extends DataObject> {
    protected static Connector connector;

    protected MongoCollection<Document> collection;
    public MongoCollection<Document> getCollection() {
        return collection;
    }

    public DOFactory(String collectionName){
        connector = new Connector("lncsoftware");
        collection = connector.getCollection(collectionName);
    }

    public void insert(T dataObj){
        collection.insertOne(dataObj.toDocument());
    }

    public void remove(T dataObj){
        collection.deleteOne(new Document("_id",dataObj.getObjectId()));
    }

    public void update(T dataObj){
        collection.updateOne(new Document("_id",dataObj.getObjectId()),new Document("$set",dataObj.toDocument()));
    }

    public T get(ObjectId targetId){
        return getDataInstance(collection.find(new Document("_id",targetId)).first());
    }

    public abstract T getDataInstance(Document doDoc);
}
