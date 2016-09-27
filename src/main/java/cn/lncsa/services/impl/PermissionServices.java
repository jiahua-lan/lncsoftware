package cn.lncsa.services.impl;

import cn.lncsa.common.exceptions.PermissionException;
import cn.lncsa.common.exceptions.UserOperateException;
import cn.lncsa.data.dao.user.*;
import cn.lncsa.data.model.permissions.Permission;
import cn.lncsa.data.model.permissions.PermissionRole;
import cn.lncsa.data.model.permissions.Role;
import cn.lncsa.data.model.user.User;
import cn.lncsa.services.IPermissionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by cattenlinger on 2016/9/26.
 */
@Service
public class PermissionServices implements IPermissionServices {

    private IPermissionDAO permissionDAO;
    private IUserDAO userDAO;
    private IPermissionRoleDAO permissionRoleDAO;
    private IRoleDAO roleDAO;

    @Autowired
    public void setPermissionDAO(IPermissionDAO permissionDAO) {
        this.permissionDAO = permissionDAO;
    }

    @Autowired
    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setPermissionRoleDAO(IPermissionRoleDAO permissionRoleDAO) {
        this.permissionRoleDAO = permissionRoleDAO;
    }

    @Autowired
    public void setRoleDAO(IRoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<Permission> queryUserPermissions(Integer userId) throws UserOperateException {
        return permissionRoleDAO.queryUserPermissionByUserId(getUser(userId).getId());
    }

    @Override
    public void assertUserHasPermission(Integer userId, Integer permissionId) throws PermissionException, UserOperateException {
        List<Permission> permissionList = permissionRoleDAO.queryUserPermissionByUserId(getUser(userId).getId());
        for (Permission permission : permissionList){
            if(Objects.equals(permission.getId(), permissionId)) return;
        }
        throw new PermissionException("Permission Denied");
    }

    @Override
    public PermissionRole setPermissionRoleRule(Integer permissionId, Integer roleId) throws PermissionException {
        PermissionRole permissionRole = new PermissionRole();
        Role role = getRole(roleId);
        Permission permission = getPermission(permissionId);
        permissionRole.setRole(role);
        permissionRole.setPermission(permission);
        if(permissionRoleDAO.save(permissionRole).getId() != null) return permissionRole;
        return null;
    }

    @Override
    public PermissionRole removePermissionRoleRule(Integer ruleId) throws PermissionException {
        PermissionRole permissionRole = permissionRoleDAO.getOne(ruleId);
        if (permissionRole == null) throw new PermissionException("Rule not found");
        permissionRoleDAO.delete(ruleId);
        return permissionRole;
    }

    @Override
    public List<Permission> queryRolePermissions(Integer roleId) throws PermissionException {
        return permissionRoleDAO.queryPermissionsByRole(getRole(roleId).getId());
    }

    @Override
    public Role addRole(Role role) {
        Role aRole = role;
        if(roleDAO.save(aRole).getId() != null) return aRole;
        return null;
    }

    @Override
    public Role removeRole(Integer roleId) throws PermissionException {
        Role role = getRole(roleId);
        roleDAO.delete(role);
        return role;
    }

    @Override
    public Permission addPermission(Permission permission) {
        Permission permission1 = permission;
        if(permissionDAO.save(permission1).getId() != null) return permission;
        return null;
    }

    @Override
    public Permission removePermission(Integer permissionId) throws PermissionException {
        Permission permission = getPermission(permissionId);
        permissionDAO.delete(permission);
        return permission;
    }

    @Override
    public List<Role> listAllRoles() {
        return roleDAO.findAll();
    }

    @Override
    public List<Permission> listAllPermissions() {
        return permissionDAO.findAll();
    }

    /*
    *   private procedure
    * */

    private User getUser(Integer userId) throws UserOperateException{
        User user = userDAO.getOne(userId);
        if (user == null) throw new UserOperateException("User not exist");
        return user;
    }

    private Role getRole(Integer roleId) throws PermissionException{
        Role role = roleDAO.getById(roleId);
        if(role == null) throw new PermissionException("No such role.");
        return role;
    }

    private Permission getPermission(Integer permissionId) throws PermissionException{
        Permission permission = permissionDAO.getOne(permissionId);
        if(permission == null) throw new PermissionException("No such permission.");
        return permission;
    }
}
