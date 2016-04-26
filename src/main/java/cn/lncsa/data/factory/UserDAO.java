package cn.lncsa.data.factory;

import cn.lncsa.data.model.User;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by catten on 16/1/15.
 */
public class UserDAO extends DOFactory<User>{
    private MongoCollection<Document> collection;

    public UserDAO() {
        super("user");
        this.collection = getCollection();
    }

    public User getUserByName(String name){
        Document document = collection.find(new Document("name",name)).first();
        if(document != null){
            return new User(document);
        }
        return null;
    }

    public List<User> search(String field, String regex){
        List<Document> documents = collection.find(new Document(field,new Document("$regex",regex).append("$options","$i")))
                .into(new ArrayList<Document>());
        List<User> users = new ArrayList<>();
        for (Document document : documents) users.add(new User(document));
        return users;
    }

    @Override
    public User create(Document doDoc) {
        return new User(doDoc);
    }
}
