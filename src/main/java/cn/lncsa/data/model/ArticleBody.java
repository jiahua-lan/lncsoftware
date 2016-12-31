package cn.lncsa.data.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by catten on 10/11/16.
 */
@Entity
@Table(name = "article_body")
public class ArticleBody implements IBaseModel<Integer> {
    private Integer id;
    private String content;
    private Date latestModifiedDate;

    public ArticleBody() {
        this.latestModifiedDate = new Date();
    }

    public ArticleBody(String content) {
        this.content = content;
    }

    @Id
    @Column(length = 32)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Lob
    @NotEmpty(message = "validate_article_body_empty")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getLatestModifiedDate() {
        return latestModifiedDate;
    }

    public void setLatestModifiedDate(Date latestModifiedDate) {
        this.latestModifiedDate = latestModifiedDate;
    }
}
