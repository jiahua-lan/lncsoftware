package cn.lncsa.data.dao.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Map;

/**
 * Created by catten on 10/12/16.
 */
@NoRepositoryBean
public interface IFieldBaseQueryDAO<M> extends CrudRepository<M,Integer>{

    /**
     * Query entity by entity's field
     *
     * @param queryFields
     * @param pageable
     * @return
     */
    Page<M> queryByField(Map<String,Object> queryFields, Pageable pageable);
}
