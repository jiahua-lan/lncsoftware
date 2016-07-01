package cn.lncsa.data.model.article;

import cn.lncsa.data.model.user.User;
import org.hibernate.annotations.TypeDef;

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
    private Date createDate;
    private Date lastModifiedDate;
    private User author;
    private String previewSentences;

    public Article() {
    }

    public Article(String title, String context, Date createDate, User author, String previewSentences, String status) {
        this.title = title;
        this.context = context;
        this.createDate = createDate;
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

    @Column(length = 4096, columnDefinition = "TEXT")
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
