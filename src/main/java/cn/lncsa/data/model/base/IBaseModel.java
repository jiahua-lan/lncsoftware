package cn.lncsa.data.model.base;

import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by cattenlinger on 2016/9/29.
 */
@NoRepositoryBean
public interface IBaseModel<I> {
    I getId();
}
