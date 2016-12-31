package cn.lncsa.data.repository;

import cn.lncsa.data.model.Permission;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cattenlinger on 2016/9/26.
 */
public interface IPermissionDAO extends IBaseDAO<Permission> {

    /**
     *
     * Get permission by title
     *
     * @param title
     * @return
     */
    @Query("select p from Permission p where p.title = ?1")
    List<Permission> getByTitle(String title);
}
