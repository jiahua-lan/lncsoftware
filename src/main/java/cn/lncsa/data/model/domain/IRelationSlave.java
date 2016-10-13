package cn.lncsa.data.model.domain;

import cn.lncsa.data.model.base.IBaseModel;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by cattenlinger on 2016/9/29.
 *
 * IRelationSlave identifies the slave object in the master-detail relationship.
 *
 * @see IRelationship
 */
@NoRepositoryBean
public interface IRelationSlave<I extends Serializable> extends IBaseModel<I> {
}
