package cn.lncsa.data.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by catte on 2016/6/12.
 */
@Entity
@Table(name = "tags")
public class Topic implements IBaseModel<Integer> {

    @Id
    @Column(length = 32)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String title;

    @Column(name = "articles_id")
    @ManyToMany(mappedBy = "topics")
    private Set<Article> articles;

    public Topic() {
    }

    public Topic(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
