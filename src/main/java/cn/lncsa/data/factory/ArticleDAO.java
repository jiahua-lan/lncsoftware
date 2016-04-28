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
    private static int itemPerPage = 5;

    public ArticleDAO() {
        super("article");
        this.collection = getCollection();
    }

    public List<Article> getLatestPage(){
        return getPage(1);
    }

    public int getPageCount(){
        long count = collection.count();
        if(count < 1) return 0;
        int pages = (int) (count / itemPerPage);
        if(count % itemPerPage != 0) pages++;
        return pages;
    }

    public List<Article> getPage(int page){
        long count = collection.count();
        if(count < 1) return null;
        List<Document> documents;
        if(page > count / itemPerPage && count % itemPerPage != 0) {
            count %= itemPerPage;
            documents = collection
                    .find()
                    .limit((int) count)
                    .skip((int) (collection.count() - itemPerPage * (page - 1) - count))
                    .into(new ArrayList<Document>());
        }
        else{
            documents = collection
                    .find()
                    .limit(itemPerPage)
                    .skip((int) (collection.count() - itemPerPage * page))
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
