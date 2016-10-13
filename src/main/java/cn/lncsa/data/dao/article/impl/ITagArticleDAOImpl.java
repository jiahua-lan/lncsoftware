package cn.lncsa.data.dao.article.impl;

import cn.lncsa.data.dao.article.ITagArticleDAO;
import cn.lncsa.data.dao.base.domain.impl.IRelationshipRepositoryImpl;
import cn.lncsa.data.model.article.Article;
import cn.lncsa.data.model.article.ArticleTag;
import cn.lncsa.data.model.article.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by cattenlinger on 2016/10/13.
 */
public class ITagArticleDAOImpl extends IRelationshipRepositoryImpl<Article,Tag,ArticleTag> implements ITagArticleDAO {

    public ITagArticleDAOImpl(Class<ArticleTag> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    @Override
    public List<Tag> getByArticle(Article article) {
        return null;
    }

    @Override
    public List<Tag> getByArticleId(Integer articleId) {
        return null;
    }

    @Override
    public List<ArticleTag> getRelationships(Integer articleId, List<Tag> tags) {
        return null;
    }

    @Override
    public Page<Article> findArticleByTag(Tag tag, List<String> status, Pageable pageable) {
        return null;
    }

    @Override
    public void removeAllByArticleId(Article article) {

    }
}
