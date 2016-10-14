package cn.lncsa.data.dao.permissions;

import cn.lncsa.data.model.permissions.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cattenlinger on 2016/9/26.
 */
public interface IPermissionDAO extends JpaRepository<Permission,Integer> {

    /**
     *
     * Get permission by title
     *
     * @param title
     * @return
     */
    @Query("select p from Permission p where p.title = ?1")
    List<Permission> getByTitle(String title);


    /**
     *
     * Get all permissions the role have
     *
     * @param roleId
     * @return
     */
    @Query("select pr.permission from PermissionRole pr where pr.role.id = ?1")
    List<Permission> getPermissionsByRole(Integer roleId);
}
