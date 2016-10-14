package cn.lncsa.data.dao.base.impl;

import cn.lncsa.data.dao.base.IFieldBaseQueryDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.*;

/**
 * Created by catten on 10/12/16.
 */
@NoRepositoryBean
public class IFieldBaseQueryDAOImpl<M, ID extends Serializable> extends SimpleJpaRepository<M, ID> implements IFieldBaseQueryDAO<M> {
    private final EntityManager entityManager;
    private final Class<M> domainClass;

    public IFieldBaseQueryDAOImpl(Class<M> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
        this.domainClass = domainClass;
    }

    protected EntityManager getEntityManager(){
        return entityManager;
    }

    protected Class<M> getDomainClass(){
        return domainClass;
    }

    @Override
    public Page<M> queryByField(final Map queryField, Pageable pageable) {
        return findAll(new Specification<M>() {
            @Override
            public Predicate toPredicate(Root<M> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new LinkedList<>();
                for (Object s : queryField.keySet()){
                    predicates.add(cb.like(root.<String>get((String)s),(String)queryField.get(s)));
                }
                return cb.and((Predicate[]) predicates.toArray());
            }
        },pageable);
    }
}
