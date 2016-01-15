package cn.lncsoftware.data.DAO;

import cn.lncsoftware.common.DBConnector;
import cn.lncsoftware.data.Bulletin;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import sun.jvm.hotspot.ci.ciObjArrayKlass;

/**
 * Created by catten on 16/1/15.
 */
public class BulletinDAO {
    private static MongoCollection<Document> collection = DBConnector.getDatabase("lncsoftware").getCollection("bulletin");

    /**
     * Get a bulletin by object id
     * @param objectID the object id of the bulletin
     * @return a bulletin object
     */
    public static Bulletin getByID(String objectID){
        Document document = collection.find(new Document("_id",new ObjectId(objectID))).first();
        return getFormDocument(document);
    }

    /**
     * Get a bulletin by type, only return the first result
     * @param type type of the bulletin
     * @return
     */
    public static Bulletin getByType(String type){
        Document document = collection.find(new Document("type",type)).first();
        return getFormDocument(document);
    }

    /**
     * Insert a bulletin into database
     * @param bulletin bulletin object
     */
    public static void insert(Bulletin bulletin){
        collection.insertOne(convertToDocument(bulletin));
    }

    /**
     * Delete a bulletin form database
     * @param objectID object id of the bulletin
     */
    public static void delete(String objectID){
        collection.deleteOne(new Document("_id",new ObjectId(objectID)));
    }

    /**
     * Update a bulletin
     * @param bulletin
     */
    public static void update(Bulletin bulletin){
        if("".equals(bulletin.getObjectID())) return;
        collection.updateOne(new Document("_id", new ObjectId(bulletin.getObjectID())),new Document("$set",convertToDocument(bulletin)));
    }

    /**
     * Convert document to bulletin
     * @param document document contains bulletin info
     * @return bulletin object
     */
    public static Bulletin getFormDocument(Document document){
        if(document != null){
            Bulletin result = new Bulletin();
            result.setObjectID(document.getObjectId("_id").toHexString());
            result.setContext(document.getString("context"));
            result.setDate(document.getObjectId("_id").getDate());
            result.setType(document.getString("type"));
            return result;
        }else return null;
    }

    /**
     * Convert bulletin to Document object
     * @param bulletin bulletin
     * @return document contains infomation
     */
    public static Document convertToDocument(Bulletin bulletin){
        if(bulletin != null){
            return new Document().
                    append("context",bulletin.getContext()).
                    append("type",bulletin.getType());
        }else return null;
    }
}
