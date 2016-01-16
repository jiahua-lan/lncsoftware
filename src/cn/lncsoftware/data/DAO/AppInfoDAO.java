package cn.lncsoftware.data.DAO;

import cn.lncsoftware.common.DBConnector;
import cn.lncsoftware.data.AppDescription;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by catten on 16/1/15.
 */
public class AppInfoDAO {
    private static MongoCollection<Document> collection = DBConnector.getDatabase("lncsoftware").getCollection("appInfo");

    /**
     * Get an AppDescription object by id
     * @param objectID obejctID in database
     * @return AppDescription object
     */
    public static AppDescription getByID(String objectID){
        Document document = collection.find(new Document("_id",new ObjectId(objectID))).first();
        return getFormDocument(document);
    }

    /**
     * Insert an AppDescription object into database
     * @param appDescription AppDescription object
     */
    public static void insert(AppDescription appDescription){
        collection.insertOne(convertToDocument(appDescription));
    }

    /**
     * Update an AppDescription object in database
     * @param appDescription AppDescription
     */
    public static void update(AppDescription appDescription){
        if("".equals(appDescription.getObjectID())) return;
        collection.updateOne(new Document("_id",new ObjectId(appDescription.getObjectID())),convertToDocument(appDescription));
    }

    /**
     * Delete an AppDescription object by ID
     * @param objectID objectId
     */
    public static void delete(String objectID){
        collection.deleteOne(new Document("_id",new ObjectId(objectID)));
    }

    /**
     * Find an object
     * @param field which field
     * @param regex regex expression
     * @return a list of appDescription object
     */
    public static List<AppDescription> find(String field, String regex){
        List<AppDescription> result = new ArrayList<>();
        List<Document> foundDocument = collection.find(new Document(field, new Document("$regex",regex).append("$options","$i"))).into(new ArrayList<Document>());
        for(Document document : foundDocument){
            result.add(getFormDocument(document));
        }
        return result;
    }

    /**
     * Convert Document to AppDescription object
     * @param document document
     * @return AppDescription object
     */
    public static AppDescription getFormDocument(Document document){
        if(document != null){
            AppDescription result = new AppDescription();
            result.setObjectID(document.getObjectId("_id").toHexString());
            result.setTitle(document.getString("title"));
            result.setDescription(document.getString("description"));
            result.setImageCode(document.getString("image"));
            result.setLink(document.getString("link"));
            result.setStatus(document.getString("status"));
            result.setOperator(document.getObjectId("operator").toHexString());
            result.setDate(document.getObjectId("_id").getDate());
            return result;
        }else return null;
    }

    /**
     * Convert an AppDescription object to Document
     * @param appDescription AppDescription object
     * @return Document object
     */
    public static Document convertToDocument(AppDescription appDescription){
        if(appDescription != null){
            return new Document().
                    append("title",appDescription.getTitle()).
                    append("description",appDescription.getDescription()).
                    append("image",appDescription.getImageCode()).
                    append("link",appDescription.getLink()).
                    append("status",appDescription.getStatus());
        }else return null;
    }
}
