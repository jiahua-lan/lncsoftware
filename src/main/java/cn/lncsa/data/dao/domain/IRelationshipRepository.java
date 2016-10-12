package cn.lncsa.data.dao.domain;

import cn.lncsa.data.model.domain.IRelationMaster;
import cn.lncsa.data.model.domain.IRelationSlave;
import cn.lncsa.data.model.domain.IRelationship;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by cattenlinger on 2016/9/29.
 *
 * The IRelationshipRepository is an interface for managing the data access objects (DAO) between data models.
 * It manages the relationships between data models based on master-slave relationships.
 *
 * The generic parameter M represents the master in the relationship, S represents the Slave in the relation,
 * and R represents the relational model class that links them.
 *
 * I created this interface is intended to streamline out a lot of duplicate relational processing code.
 *
 * @see IRelationship
 */
@NoRepositoryBean
public interface IRelationshipRepository<M extends IRelationMaster,S extends IRelationSlave,R extends IRelationship>{
    /**
     * Obtaining relationships throw a slave object
     *
     * @param slave
     * @return
     */
    List<R> getRelationships(S slave);

    /**
     * Obtaining relationships throw a master object
     *
     * @param master
     * @return
     */
    List<R> getRelationships(M master);

    /**
     * Querying master-slave relationships accurately
     *
     * @param master
     * @param slave
     * @return
     */
    List<R> getRelationships(M master, List<S> slave);

    /**
     * Delete relationships
     *
     * @param delList
     */
    void delete(List<R> delList);

    /**
     * Insert relationships
     *
     * @param addList
     */
    void save(List<R> addList);
}
