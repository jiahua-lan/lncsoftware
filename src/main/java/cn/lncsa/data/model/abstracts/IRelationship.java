package cn.lncsa.data.model.abstracts;

/**
 * Created by cattenlinger on 2016/9/29.
 */
public interface IRelationship<M extends IRelationMaster, S extends IRelationSlave> {
    M getMaster();
    S getSlave();

    void setRelationship(M master,S slave);
}
