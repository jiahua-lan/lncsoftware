package cn.lncsa.services;

import cn.lncsa.common.exceptions.PermissionException;
import cn.lncsa.common.exceptions.UserOperateException;
import cn.lncsa.data.model.permissions.Permission;
import cn.lncsa.data.model.permissions.PermissionRole;
import cn.lncsa.data.model.permissions.Role;
import cn.lncsa.data.model.permissions.UserRole;

import java.util.List;

/**
 * Created by cattenlinger on 2016/9/26.
 */
public interface IPermissionServices {

    /*
    *
    * Permission modifying
    *
    * */

    Permission addPermission(Permission permission);

    Permission removePermission(Integer permissionId) throws PermissionException;

    /*
    *
    * Role modifying
    *
    * */

    Role addRole(Role role);

    Role removeRole(Integer roleId) throws PermissionException;

    /*
    *
    * Role-Permission Relationship modifying
    *
    * */

    PermissionRole setPermissionRoleRule(Integer permissionId, Integer roleId);

    void removePermissionRoleRule(Integer permissionId, Integer ruleId);

    /*
    *
    * User-Role Relationship modifying
    *
    * */

    UserRole setUserRoleRule(Integer userId, Integer roleId);

    void removeUserRoleRule(Integer userId, Integer roleId);

    /*
    *
    * Query methods
    *
    * */

    List<Permission> queryUserPermissions(Integer userId) throws UserOperateException;

    List<Permission> queryRolePermissions(Integer roleId) throws PermissionException;

    List<Role> listAllRoles();

    List<Permission> listAllPermissions();
}
