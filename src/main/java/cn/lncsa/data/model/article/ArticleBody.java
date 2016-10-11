package cn.lncsa.data.model.article;

import javax.persistence.*;

/**
 * Created by catten on 10/11/16.
 */
@Entity
@Table(name = "article_content")
public class ArticleBody {
    private Integer id;
    private String content;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
