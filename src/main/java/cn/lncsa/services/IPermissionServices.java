package cn.lncsa.services;

import cn.lncsa.common.exceptions.PermissionException;
import cn.lncsa.common.exceptions.UserOperateException;
import cn.lncsa.data.model.permissions.Permission;
import cn.lncsa.data.model.permissions.PermissionRole;
import cn.lncsa.data.model.permissions.Role;

import java.util.List;

/**
 * Created by cattenlinger on 2016/9/26.
 */
public interface IPermissionServices {

    List<Permission> queryUserPermissions(Integer userId) throws UserOperateException;

    void assertUserHasPermission(Integer userId, Integer permissionId) throws PermissionException, UserOperateException;

    PermissionRole setPermissionRoleRule(Integer permissionId, Integer roleId) throws PermissionException;

    PermissionRole removePermissionRoleRule(Integer ruleId) throws PermissionException;

    List<Permission> queryRolePermissions(Integer roleId) throws PermissionException;

    Role addRole(Role role);

    Role removeRole(Integer roleId) throws PermissionException;

    Permission addPermission(Permission permission);

    Permission removePermission(Integer permissionId) throws PermissionException;

    List<Role> listAllRoles();

    List<Permission> listAllPermissions();
}
