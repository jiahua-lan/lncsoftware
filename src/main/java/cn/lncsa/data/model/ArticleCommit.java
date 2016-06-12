package cn.lncsa.data.model;

import javax.persistence.*;

/**
 * Created by catte on 2016/6/12.
 */
@Entity
@Table(name = "article_commit")
public class ArticleCommit {
    private Integer id;
    private Article article;
    private Commit commit;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @ManyToOne
    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }
}
