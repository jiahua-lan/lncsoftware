package cn.lncsoftware.data;

import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Created by catten on 16/2/2.
 */
public abstract class DataObject {
    protected ObjectId objectId;

    public ObjectId getObjectId(){
        return objectId;
    }

    public void setObjectId(ObjectId objectId){
        this.objectId = objectId;
    }

    public DataObject(ObjectId objectId){
        this.objectId = objectId;
    }

    public DataObject(){
        objectId = null;
    }

    public abstract Document toDocument();
    public abstract void apply(Document doDoc);
}
