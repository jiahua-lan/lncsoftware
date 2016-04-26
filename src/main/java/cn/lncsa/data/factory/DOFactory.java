package cn.lncsa.data.factory;

import cn.lncsa.data.model.DataObject;
import cn.lncsa.data.common.Connector;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by catten on 16/2/3.
 *
 * Data Object Factory.
 *
 * Base class of Data access objects.
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

    public void create(T dataObj){
        collection.insertOne(dataObj.toDocument());
    }

    public T read(ObjectId targetId){
        Document document = collection.find(new Document("_id",targetId)).first();
        if (document != null) return create(document); else return null;
    }

    public void update(T dataObj){
        collection.updateOne(new Document("_id",dataObj.getObjectId()),new Document("$set",dataObj.toDocument()));
    }

    public void delete(T dataObj){
        collection.deleteOne(new Document("_id",dataObj.getObjectId()));
    }

    public List<T> convertDocList(List<Document> doDocList){
        ArrayList<T> arrayList = new ArrayList<>();
        for (Document document : doDocList) arrayList.add(create(document));
        return arrayList;
    }

    public abstract T create(Document doDoc);
}
