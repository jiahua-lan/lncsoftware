package cn.lncsoftware.data.DAO;

import cn.lncsoftware.common.DBConnector;
import cn.lncsoftware.data.User;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by catten on 16/1/15.
 */
public class UserDAO {
    //Static database connection object to collection.
    private static MongoCollection<Document> collection = DBConnector.getDatabase("lncsoftware").getCollection("user");

    /**
     * Get a user by ObjectID
     * @param objectID user object's id
     * @return user object
     */
    public static User getByID(String objectID){
        Document document = collection.find(new Document("_id",new ObjectId(objectID))).first();
        return getFromDocument(document);
    }

    /**
     * Add a user to database
     * @param user user object
     */
    public static void insert(User user){
        collection.insertOne(convertToDocument(user));
    }

    /**
     * Delete a user by objectID
     * @param objectID
     */
    public static void delete(String objectID){
        collection.deleteOne(new Document("_id",new ObjectId(objectID)));
    }

    /**
     * Update user info
     * if no object id , the method will update nothing
     * @param user new user infos
     */
    public static void update(User user){
        if("".equals(user.getObjectID())) return;
        collection.updateOne(new Document("_id",new ObjectId(user.getObjectID())),new Document("$set",new Document(convertToDocument(user))));
    }

    /**
     * find some users
     * @param field field which you want to search
     * @param regex a regex expression
     * @return a list of user object
     */
    public static List<User> find(String field, String regex){
        List<User> result = new ArrayList<>();
        List<Document> foundDocument = collection.
                find(new Document(field, new Document("$regex",regex).append("$options","$i"))).
                into(new ArrayList<Document>());

        for(Document document : foundDocument){
            result.add(getFromDocument(document));
        }
        return result;
    }

    /**
     * Method for convert document object to user object
     * @param document document object contains user infomation
     * @return a user object
     */
    public static User getFromDocument(Document document){
        if(document != null){
            User result = new User();
            result.setObjectID(document.getObjectId("_id").toHexString());
            result.setName(document.getString("name"));
            result.setPassword(document.getString("password"));
            result.setRights(document.get("rights",ArrayList.class));
            result.setRegDate(document.getObjectId("_id").getDate());
            result.setContactInfo(document.getString("contactInfo"));
            return result;
        }else return null;
    }

    /**
     * Method for convert user object to document object
     * @param user a user object
     * @return document object contains user info
     */
    public static Document convertToDocument(User user){
        if(user != null){
            return new Document().
                    append("name",user.getName()).
                    append("password",user.getPassword()).
                    append("rights",user.getRights()).
                    append("contactInfo",user.getContactInfo());
        }else return null;
    }
}
