package cn.lncsa.data.model.article;

import cn.lncsa.data.model.domain.IRelationMaster;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by catten on 16/1/15.
 */
@Entity
@Table(name = "article_head")
public class Article implements Serializable, IRelationMaster<Integer>{

    //private static ArticleDAO dao = new ArticleDAO();

    private Integer id;

    private String title;
    private Date createDate;
    private Date modifiedDate;
    private Integer authorId;
    private String previewSentences;
    private ArticleStatus status;
    private Integer contentId;

    public Article() {
    }

    public Article(String title,Integer contentId,Date createDate, Integer authorId, String previewSentences, ArticleStatus status) {
        this.title = title;
        this.createDate = createDate;
        this.authorId = authorId;
        this.contentId = contentId;
        this.previewSentences = previewSentences;
        this.status = status;
    }

    @Enumerated(value = EnumType.STRING)
    public ArticleStatus getStatus() {
        return status;
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date lastModifiedDate) {
        this.modifiedDate = lastModifiedDate;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }
}
