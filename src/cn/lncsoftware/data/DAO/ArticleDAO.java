package cn.lncsoftware.data.DAO;

import cn.lncsoftware.common.DBConnector;
import cn.lncsoftware.data.Article;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by catten on 16/1/15.
 */
public class ArticleDAO {
    //Static connect object to collection
    private static MongoCollection<Document> collection = DBConnector.getDatabase("lncsoftware").getCollection("article");

    /**
     * Get one article from database by id
     * @param objectID article id in database.
     * @return article object that found.
     */
    public static Article getByID(String objectID){
        Document document = collection.find(new Document("_id",new ObjectId(objectID))).first();
        return getFromDocument(document);
    }



    /**
     * Insert an article into database
     * @param article the article object.
     */
    public static void insert(Article article){
        collection.insertOne(convertToDocument(article));
    }

    /**
     * Delete an article form database, locate by id.
     * @param objectID article id in database.
     */
    public static void delete(String objectID){
        collection.deleteOne(new Document("_id",new ObjectId(objectID)));
    }

    /**
     * Update an article base on article object.
     * @param article article object you want to update. if NO ObjectID , the method will not update ANYTHING.
     */
    public static void update(Article article){
        if("".equals(article.getObjectID())) return;
        collection.updateOne(new Document("_id",new ObjectId(article.getObjectID())),new Document("$set",convertToDocument(article)));
    }

    /**
     * Get a lot of article, stored in arrayList.
     * @param field which field you want to search(title,context,author,date)
     * @param regex regex expression to search
     * @return a list of article.
     */
    public static List<Article> find(String field, String regex){
        List<Article> result = new ArrayList<>();
        List<Document> foundDocument = collection.
                find(new Document(field, new Document("$regex",regex).append("$options","$i"))).
                into(new ArrayList<Document>());

        for(Document document : foundDocument){
            result.add(getFromDocument(document));
        }
        return result;
    }

    /**
     * Convert an article object to document object
     * note that it will not fill objectID field into document object
     * because they are generate automatically by database.
     * @param article the article
     * @return document object without objectID and date
     */
    public static Document convertToDocument(Article article){
        if(article != null){
            return new Document().
                    append("author",article.getAuthor()).
                    append("title",article.getTitle()).
                    append("context",article.getContext()).
                    append("previewSentences",article.getPreviewSentences()).
                    append("date",article.getDate());
        }else return null;
    }

    /**
     * Convert a document object to article object
     * Factory method of article object
     * @param document document included article infomation
     * @return article object
     */
    public static Article getFromDocument(Document document){
        if(document != null){
            Article result = new Article();
            result.setAuthor(document.getString("author"));
            result.setTitle(document.getString("title"));
            result.setContext(document.getString("context"));
            result.setPreviewSentences(document.getString("previewSentences"));
            result.setObjectID(document.getObjectId("_id").toHexString());
            Date date = document.getDate("date");
            if(date == null) date = document.getObjectId("_id").getDate();
            result.setDate(date);
            return result;
        }else return null;
    }
}
