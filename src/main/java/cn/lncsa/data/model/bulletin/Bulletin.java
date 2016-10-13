package cn.lncsa.data.model.bulletin;

import cn.lncsa.data.model.base.IBaseModel;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by catten on 16/1/15.
 */
@Entity
@Table(name = "bulletins")
public class Bulletin {

    private Integer id;

    private String type;
    private String context;
    private Date date;
    private String imageLink;
    private Integer authorid;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    private String link;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "author_id")
    public Integer getAuthor_id() {
        return authorid;
    }

    public void setAuthor_id(Integer author_id) {
        this.authorid = author_id;
    }
}
