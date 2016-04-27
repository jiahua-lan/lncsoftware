package cn.lncsa.data.factory;

import cn.lncsa.data.model.AppInfo;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by catten on 16/1/15.
 */
public class AppInfoDAO extends DOFactory<AppInfo>{

    public AppInfoDAO() {
        super("appInfo");
    }

    public List<AppInfo> query(String field,String regex){
        List<Document> documents = collection.find(
                new Document(field, new Document("$regex",regex)
                        .append("$options","$i")))
                .into(new ArrayList<Document>());
        return convertDocList(documents);
    }

    public List<AppInfo> listAll(){
        return query("title",".+");
    }

    @Override
    public AppInfo create(Document doDoc) {
        return new AppInfo(doDoc);
    }
}
