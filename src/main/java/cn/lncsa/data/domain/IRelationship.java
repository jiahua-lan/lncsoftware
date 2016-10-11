package cn.lncsa.data.domain;

/**
 * Created by cattenlinger on 2016/9/29.
 *
 * IRelationship is an interface for storing data classes for master-slave relationships.
 * The generic parameter M represents the master object, and S represents the slave object.
 *
 * @see IRelationshipRepository
 * @see IRelationMaster
 * @see IRelationSlave
 */
public interface IRelationship<M extends IRelationMaster, S extends IRelationSlave> {
    /**
     * Get the master object
     *
     * @return
     */
    M getMaster();

    /**
     * Get the slave object
     *
     * @return
     */
    S getSlave();

    /**
     * Method for setting this relationship
     *
     * @param master
     * @param slave
     */
    void setRelationship(M master,S slave);
}
