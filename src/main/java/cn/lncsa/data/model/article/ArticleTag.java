package cn.lncsa.data.model.article;

import cn.lncsa.data.model.abstracts.IBaseModel;
import cn.lncsa.data.model.abstracts.IRelationship;

import javax.persistence.*;

/**
 * Created by catte on 2016/6/12.
 */
@Entity
@Table(name = "article_tags")
public class ArticleTag implements IBaseModel<Integer>, IRelationship<Article,Tag> {
    private Integer id;
    private Tag tag;
    private Article article;

    public ArticleTag() {
    }

    public ArticleTag(Tag tag, Article article) {
        this.tag = tag;
        this.article = article;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @ManyToOne
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleTag that = (ArticleTag) o;

        if (!tag.equals(that.tag)) return false;
        return article.equals(that.article);

    }

    @Override
    public int hashCode() {
        int result = tag.hashCode();
        result = 31 * result + article.hashCode();
        return result;
    }

    @Override
    @Transient
    public Article getMaster() {
        return article;
    }

    @Override
    @Transient
    public Tag getSlave() {
        return tag;
    }

    @Override
    @Transient
    public void setRelationship(Article master, Tag slave) {
        article = master;
        tag = slave;
    }
}
