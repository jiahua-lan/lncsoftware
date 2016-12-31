package cn.lncsa.data.model;

import javax.persistence.*;

/**
 * Created by catten on 10/11/16.
 */
@Entity
@Table(name = "article_body")
public class ArticleBody implements IBaseModel<Integer> {
    @Id
    @Column(length = 32)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;

    public ArticleBody() {

    }

    public ArticleBody(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
