package cn.lncsa.data.dao.base.impl;

import cn.lncsa.data.dao.base.IFieldBaseQueryDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by catten on 10/12/16.
 */
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
    public Page<M> queryByField(Map queryFields, Map orderingField, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<?> criteriaQuery = criteriaBuilder.createQuery();



        return null;
    }


    /*
    *
    * Private method
    *
    * */

    private Predicate toPredicate(){
        return null;
    }
}
