package cn.lncsa.services;

import cn.lncsa.services.exceptions.PermissionException;
import cn.lncsa.services.exceptions.UserOperateException;
import cn.lncsa.data.model.permissions.Permission;
import cn.lncsa.data.model.permissions.Role;

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

    /**
     * Add a permission
     *
     * @param permission
     * @return
     */
    Permission addPermission(Permission permission);

    /**
     *
     * Remove a permission
     *
     * @param permissionId
     * @throws PermissionException
     */
    void removePermission(Integer permissionId) throws PermissionException;

    /*
    *
    * Role modifying
    *
    * */

    /**
     *
     * Add a role
     *
     * @param role
     * @return
     */
    Role addRole(Role role);

    /**
     * Remove a role
     *
     * @param roleId
     * @throws PermissionException
     */
    void removeRole(Integer roleId) throws PermissionException;

    /*
    *
    * Role-Permission Relationship modifying
    *
    * */

    /**
     * set permission-role relationships
     *
     * @param roleId
     * @param permissions
     * @return
     * @throws PermissionException
     */
    void grantPermissionToRole(Integer roleId, List<Permission> permissions) throws PermissionException;

    /**
     * remove permission-role relationships
     *
     * @param ruleId
     * @param permissions
     */
    void retakePermissionFromRole(Integer ruleId, List<Permission> permissions);

    /*
    *
    * User-Role Relationship modifying
    *
    * */

    /**
     * Set user-role relationships
     *
     * @param userId
     * @param roles
     * @return
     */
    void giveRoleToUser(Integer userId, List<Role> roles) throws PermissionException;

    /**
     * Remove user-role relationships
     *
     * @param userId
     * @param roles
     */
    void retakeRoleFromUser(Integer userId, List<Role> roles);

    /*
    *
    * Query methods
    *
    * */

    /**
     * What permissions the user have
     *
     * @param userId
     * @return
     * @throws UserOperateException
     */
    List<Permission> queryUserPermissions(Integer userId) throws UserOperateException;

    /**
     * What role the user are
     *
     * @param roleId
     * @return
     * @throws PermissionException
     */
    List<Permission> queryRolePermissions(Integer roleId) throws PermissionException;

    /**
     * Show all roles in the system
     *
     * @return
     */
    List<Role> listAllRoles();

    /**
     * show all permissions in the system
     *
     * @return
     */
    List<Permission> listAllPermissions();
}
