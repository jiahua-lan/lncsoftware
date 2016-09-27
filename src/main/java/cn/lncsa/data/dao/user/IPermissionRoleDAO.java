package cn.lncsa.data.dao.user;

import cn.lncsa.data.model.permissions.Permission;
import cn.lncsa.data.model.permissions.PermissionRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cattenlinger on 2016/9/26.
 */
public interface IPermissionRoleDAO extends JpaRepository<PermissionRole,Integer> {

    @Query("select pr.permission from PermissionRole pr where pr.role.id = ?1")
    List<Permission> queryPermissionsByRole(Integer roleId);

    @Query("select pr.permission from PermissionRole pr where pr.role in (select ur.user from UserRole ur where ur.user.id = ?1)")
    List<Permission> queryUserPermissionByUserId(Integer userId);
}
