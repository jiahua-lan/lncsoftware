package cn.lncsa.data.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by catten on 16/1/15.
 */
@Entity
@Table(name = "article")
public class Article {

    //private static ArticleDAO dao = new ArticleDAO();

    private Integer id;

    private String title;
    private String context;
    private Date date;
    private User author;
    private String previewSentences;

    public Article() {
    }

    public Article(String title, String context, Date date, User author, String previewSentences, String status) {
        this.title = title;
        this.context = context;
        this.date = date;
        this.author = author;
        this.previewSentences = previewSentences;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getPreviewSentences() {
        return previewSentences;
    }

    public void setPreviewSentences(String previewSentences) {
        this.previewSentences = previewSentences;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
