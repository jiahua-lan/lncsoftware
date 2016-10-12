package cn.lncsa.data.dao.base;

import cn.lncsa.data.model.user.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Map;

/**
 * Created by catten on 10/12/16.
 */
@NoRepositoryBean
public interface IFieldBaseQueryDAO<M>{

    Page<M> queryByField(Map<String,Object> queryFields, Map<String,Object> orderingField, Pageable pageable);
}
