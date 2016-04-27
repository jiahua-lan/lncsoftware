package cn.lncsa.data.factory;

import cn.lncsa.data.model.Bulletin;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by catten on 16/1/15.
 */
public class BulletinDAO extends DOFactory<Bulletin>{
    private MongoCollection<Document> collection;

    public BulletinDAO() {
        super("bulletin");
        this.collection = getCollection();
    }

    //type in: mainPage, article, app_guide, app_info
    public Bulletin getBulletinBoard(String type){
        Document document = collection.find(new Document("type",new Document("$regex",type+"$"))).first();
        if(document != null) return create(document);
        return null;
    }

    //type in: contactInfo, contactFriendLink
    public List<Bulletin> getBulletinItems(String type){
        List<Document> documents = collection.find(new Document("type",new Document("$regex",type+"$"))).into(new ArrayList<Document>());
        if(documents != null) return convertDocList(documents);
        return null;
    }


    public Bulletin getMainPageBulletin(){
        return getBulletinBoard("mainPage");
    }

    public Bulletin getArticleBulletin(){
        return getBulletinBoard("article");
    }

    public Bulletin getAppGuideBulletin(){
        return getBulletinBoard("app_guide");
    }

    public Bulletin getAppInfoBulletin(){
        return getBulletinBoard("app_info");
    }

    public List<Bulletin> getContactInfoItems(){
        return getBulletinItems("contactInfo");
    }

    public List<Bulletin> getContactFriendLinks(){
        return getBulletinItems("contactFriendLink");
    }

    @Override
    public Bulletin create(Document doDoc) {
        return new Bulletin(doDoc);
    }
}
