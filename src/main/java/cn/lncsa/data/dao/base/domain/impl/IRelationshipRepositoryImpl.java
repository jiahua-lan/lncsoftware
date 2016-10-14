package cn.lncsa.data.dao.base.domain.impl;

import cn.lncsa.data.dao.base.domain.IRelationshipRepository;
import cn.lncsa.data.model.base.IBaseModel;
import cn.lncsa.data.model.domain.IRelationMaster;
import cn.lncsa.data.model.domain.IRelationSlave;
import cn.lncsa.data.model.domain.IRelationship;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * Created by catten on 10/13/16.
 */
@NoRepositoryBean
public class IRelationshipRepositoryImpl<M extends IRelationMaster,S extends IRelationSlave,R extends IRelationship>
        extends SimpleJpaRepository<R, Integer> implements IRelationshipRepository<M, S, R> {

    public IRelationshipRepositoryImpl(Class<R> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    @Override
    public List<R> getRelationships(final S slave) {
        return findAll(new Specification<R>() {
            @Override
            public Predicate toPredicate(Root<R> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("id"),slave.getId());
            }
        });
    }

    @Override
    public List<R> getRelationships(final M master) {
        return findAll(new Specification<R>() {
            @Override
            public Predicate toPredicate(Root<R> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("id"),master.getId());
            }
        });
    }

    @Override
    public List<R> getRelationships(final M master, final List<S> slave) {
        return findAll(new Specification<R>() {
            @Override
            public Predicate toPredicate(Root<R> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                CriteriaBuilder.In<Serializable> in = cb.in(root.<Serializable>get("id"));
                for(S s : slave) in.value(s.getId());
                return cb.and(cb.equal(root.get("master"),master.getId()),in);
            }
        });
    }

    @Override
    public void delete(List<R> delList) {
        super.delete(delList);
    }

    @Override
    public void save(List<R> addList) {
        super.save(addList);
    }
}
