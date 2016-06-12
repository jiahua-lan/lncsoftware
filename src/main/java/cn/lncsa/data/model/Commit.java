package cn.lncsa.data.model;

import org.bson.Document;
import org.bson.types.ObjectId;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by catten on 16/3/4.
 */
@Entity
@Table(name = "commit")
public class Commit{

    private Integer id;

    private User user;
    private Article article;
    private String contents;
    private Date date;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
