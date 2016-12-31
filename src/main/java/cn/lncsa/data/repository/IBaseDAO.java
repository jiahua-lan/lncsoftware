package cn.lncsa.data.repository;

import cn.lncsa.data.model.IBaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by catten on 12/26/16.
 */
@NoRepositoryBean
public interface IBaseDAO<M extends IBaseModel<Integer>> extends JpaRepository<M ,Integer>, JpaSpecificationExecutor<M> {

}
