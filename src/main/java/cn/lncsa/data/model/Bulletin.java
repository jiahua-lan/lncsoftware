package cn.lncsa.data.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by catten on 16/1/15.
 */
@Entity
@Table(name = "bulletins")
public class Bulletin implements IBaseModel<Integer> {

    @Transient
    public boolean isTimeToShow(){
        return this.date.after(new Date());
    }

    private Integer id;

    private String type;
    private String content;
    private String imageLink;
    private String link;

    private Date date;

    private User author;

    /*
    * Getter and setter
    *
    * */

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Column(length = 15)
    @NotEmpty(message = "validate_bulletin_type_empty")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @NotEmpty(message = "validate_bulletin_content_empty")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Temporal(TemporalType.TIMESTAMP)
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
    @Column(length = 32)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
