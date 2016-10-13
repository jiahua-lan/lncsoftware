package cn.lncsa.data.dao.article.impl;

import cn.lncsa.data.dao.article.IArticleDAO;
import cn.lncsa.data.dao.base.impl.IFieldBaseQueryDAOImpl;
import cn.lncsa.data.model.article.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by cattenlinger on 2016/10/13.
 */
public class IArticleDAOImpl extends IFieldBaseQueryDAOImpl<Article,Integer> implements IArticleDAO {

    public IArticleDAOImpl(Class<Article> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    @Override
    public Page<Article> findByTitleLike(String keyword, String[] status, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Article> findByAuthorId(final Integer authorId, String[] status, Pageable pageable) {
        return findAll(new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("articleId"),authorId);
            }
        },pageable);
    }

    @Override
    public Page<Article> getArticleBetweenCreateDate(final Date start, final Date end, final String[] status, Pageable pageable) {
        return findAll(new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                CriteriaBuilder.In<Serializable> in = cb.in(root.<Serializable>get("status"));
                for (String s : status) in.value(s);
                return cb.and(cb.between(root.<Date>get("createDate"),start,end));
            }
        },pageable);
    }

    @Override
    public Page<Article> getArticleBetweenModifiedDate(Date start, Date end, String[] status, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Article> findAll(String[] status, Pageable pageable) {
        return null;
    }

    @Override
    public void deleteArticleByAuthorId(Integer authorId) {

    }
}
