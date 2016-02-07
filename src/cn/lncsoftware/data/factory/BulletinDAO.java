package cn.lncsoftware.data.factory;

import cn.lncsoftware.data.common.Connector;
import cn.lncsoftware.data.Bulletin;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Created by catten on 16/1/15.
 */
public class BulletinDAO extends DOFactory<Bulletin>{
    private MongoCollection<Document> collection;

    public BulletinDAO() {
        super("bulletin");
        this.collection = getCollection();
    }

    @Override
    public Bulletin getDataInstance(Document doDoc) {
        return new Bulletin(doDoc);
    }
}
