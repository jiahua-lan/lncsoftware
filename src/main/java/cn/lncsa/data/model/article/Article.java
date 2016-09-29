package cn.lncsa.data.model.article;

import cn.lncsa.data.model.abstracts.IBaseModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by catten on 16/1/15.
 */
@Entity
@Table(name = "articles")
public class Article implements Serializable, IBaseModel{

    //private static ArticleDAO dao = new ArticleDAO();

    private Integer id;

    private String title;
    private String context;
    private Date createDate;
    private Date lastModifiedDate;
    private Integer authorId;
    private String previewSentences;
    private String status;

    public Article() {
    }

    public Article(String title, String context, Date createDate, Integer authorId, String previewSentences, String status) {
        this.title = title;
        this.context = context;
        this.createDate = createDate;
        this.authorId = authorId;
        this.previewSentences = previewSentences;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    @Column(name = "author_id")
    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
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
