package cn.lncsa.data.model;

import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by cattenlinger on 2016/9/29.
 */
@NoRepositoryBean
public interface IBaseModel<I extends Serializable> {
    I getId();
}
