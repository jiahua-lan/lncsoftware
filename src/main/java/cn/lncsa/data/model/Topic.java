package cn.lncsa.data.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by catte on 2016/6/12.
 */
@Entity
@Table(name = "topics")
public class Topic implements IBaseModel<Integer> {

    private Integer id;

    @Column(unique = true)
    private String title;

    private User creator;

    private Date createDate;

    @ManyToMany(mappedBy = "topics")
    private Set<Article> articles;

    public Topic() {
    }

    public Topic(String title) {
        this.title = title;
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

    @NotEmpty(message = "validate_topics_title_empty")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ManyToOne
    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
