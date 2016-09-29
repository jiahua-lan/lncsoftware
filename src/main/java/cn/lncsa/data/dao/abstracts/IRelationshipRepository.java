package cn.lncsa.data.dao.abstracts;

import cn.lncsa.data.model.abstracts.IBaseModel;
import cn.lncsa.data.model.abstracts.IRelationMaster;
import cn.lncsa.data.model.abstracts.IRelationSlave;
import cn.lncsa.data.model.abstracts.IRelationship;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by cattenlinger on 2016/9/29.
 */
@NoRepositoryBean
public interface IRelationshipRepository<M extends IRelationMaster,S extends IRelationSlave,R extends IRelationship>{
    List<R> getRelationships(S slave);
    List<R> getRelationships(M master);
    List<R> getRelationships(M master, List<S> slave);

    void delete(List<R> delList);

    void save(List<R> addList);
}
