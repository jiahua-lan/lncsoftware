package cn.lncsa.data.dao.article.impl;

import cn.lncsa.data.dao.article.IArticleDAO;
import cn.lncsa.data.dao.base.impl.IFieldBaseQueryDAOImpl;
import cn.lncsa.data.model.article.Article;
import cn.lncsa.data.model.article.ArticleTag;
import cn.lncsa.data.model.article.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by cattenlinger on 2016/10/13.
 */
public class IArticleDAOImpl extends IFieldBaseQueryDAOImpl<Article> implements IArticleDAO {


    public IArticleDAOImpl(Class<Article> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    @Override
    public Page<Article> findByAuthorId(final Integer authorId, final String[] status, Pageable pageable) {
        return findAll(new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //Fill status conditions
                CriteriaBuilder.In<Serializable> in = cb.in(root.<Serializable>get("status"));
                for (String s : status) in.value(s);

                return cb.equal(root.get("articleId"),authorId);
            }
        },pageable);
    }

    @Override
    public Page<Article> getArticleBetweenDate(final Date start, final Date end,final DateType dateType, final String[] status, Pageable pageable) {
        return findAll(new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //Fill status conditions
                CriteriaBuilder.In<Serializable> in = cb.in(root.<Serializable>get("status"));
                for (String s : status) in.value(s);

                return cb.and(cb.between(root.<Date>get(dateType.name()),start,end));
            }
        },pageable);
    }

    @Override
    public Page<Article> findAll(final String[] status, Pageable pageable) {
        return findAll(new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                CriteriaBuilder.In<Serializable> in = cb.in(root.<Serializable>get("status"));
                for (String s : status) in.value(s);

                return in;
            }
        }, pageable);
    }

    @Override
    public Page<Article> getByTags(List<Tag> tags, final List<String> status, Pageable pageable) {
        return findAll(new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                CriteriaBuilder.In<Serializable> in = cb.in(root.<Serializable>get("status"));
                for (String s : status) in.value(s);

                Join<Article,ArticleTag> articleTagJoin = root.join("tag");

                return cb.and(cb.equal(articleTagJoin,root.get("id")),in);
            }
        },pageable);
    }

    @Override
    public void deleteByAuthorId(final Integer authorId) {

        for (Article article : findAll(new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.<Serializable>get("authorId"), authorId);
            }
        })) {
            delete(article);
        }
    }
}
