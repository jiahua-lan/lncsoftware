package cn.lncsa.data.dao.domain.impl;

import cn.lncsa.data.dao.domain.IRelationshipRepository;
import cn.lncsa.data.model.domain.IRelationMaster;
import cn.lncsa.data.model.domain.IRelationSlave;
import cn.lncsa.data.model.domain.IRelationship;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by catten on 10/13/16.
 */
public class IRelationshipRepositoryImpl<M extends IRelationMaster,S extends IRelationSlave,R extends IRelationship>
        extends SimpleJpaRepository<R, Integer> implements IRelationshipRepository<M, S, R> {

    private final Class<R> domainClass;
    private final EntityManager entityManager;

    public IRelationshipRepositoryImpl(Class<R> domainClass, EntityManager em) {
        super(domainClass, em);
        this.domainClass = domainClass;
        this.entityManager = em;
    }

    @Override
    public List<R> getRelationships(S slave) {
        return null;
    }

    @Override
    public List<R> getRelationships(M master) {
        return null;
    }

    @Override
    public List<R> getRelationships(M master, List<S> slave) {
        return null;
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
