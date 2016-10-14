package cn.lncsa.data.dao.article.impl;

import cn.lncsa.data.dao.article.ITagArticleDAO;
import cn.lncsa.data.dao.base.domain.impl.IRelationshipRepositoryImpl;
import cn.lncsa.data.model.article.Article;
import cn.lncsa.data.model.article.ArticleTag;
import cn.lncsa.data.model.article.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cattenlinger on 2016/10/13.
 */
public class ITagArticleDAOImpl extends IRelationshipRepositoryImpl<Article,Tag,ArticleTag> implements ITagArticleDAO {

    public ITagArticleDAOImpl(Class<ArticleTag> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    @Override
    public void removeAllByArticleId(final Integer articleId) {

        for (ArticleTag articleTag : findAll(new Specification<ArticleTag>() {
            @Override
            public Predicate toPredicate(Root<ArticleTag> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("article"), articleId);
            }
        })) {
            delete(articleTag);
        }
    }
}
