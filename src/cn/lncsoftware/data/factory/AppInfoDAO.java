package cn.lncsoftware.data.factory;

import cn.lncsoftware.data.common.Connector;
import cn.lncsoftware.data.AppInfo;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by catten on 16/1/15.
 */
public class AppInfoDAO extends DOFactory<AppInfo>{

    public AppInfoDAO() {
        super("appInfo");
    }

    public List<AppInfo> find(String field,String regex){
        List<Document> documents = collection.find(
                new Document(field, new Document("$regex",regex)
                        .append("$options","$i")))
                .into(new ArrayList<Document>());
        return convertDocList(documents);
    }

    @Override
    public AppInfo create(Document doDoc) {
        return new AppInfo(doDoc);
    }
}
