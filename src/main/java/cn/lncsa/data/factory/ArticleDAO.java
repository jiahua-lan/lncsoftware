package cn.lncsa.data.factory;

import cn.lncsa.data.model.Article;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by catten on 16/1/15.
 */
public class ArticleDAO extends DOFactory<Article>{
    private MongoCollection<Document> collection;

    public ArticleDAO() {
        super("article");
        this.collection = getCollection();
    }

    public List<Article> getLatestPage(){
        return getPage(1);
    }

    public int getPages(){
        long count = collection.count();
        if(count < 1) return 0;
        int pages = (int) (count / 10);
        if(count % 10 != 0) pages++;
        return pages;
    }

    public List<Article> getPage(int page){
        long count = collection.count();
        if(count < 1) return null;
        List<Document> documents;
        if(page > count / 10 && count % 10 != 0) {
            count %= 10;
            documents = collection
                    .find()
                    .limit((int) count)
                    .skip((int) (collection.count() - 10 * (page - 1) - count))
                    .into(new ArrayList<Document>());
        }
        else{
            documents = collection
                    .find()
                    .limit(10)
                    .skip((int) (collection.count() - 10 * page))
                    .into(new ArrayList<Document>());
        }
        return convertDocList(documents);
    }

    public List<Article> query(String regex){
        List<Document> documents = collection.find(
                new Document(
                        "$or",
                        Arrays.asList(
                                new Document("context", new Document("$regex", regex).append("$options","$i"))
                                , new Document("title", new Document("$regex", regex).append("$options","$i"))
                        )
                ))
                .into(new ArrayList<Document>());
        return convertDocList(documents);
    }

    @Override
    public Article create(Document doDoc) {
        return new Article(doDoc);
    }
}
