package cn.lncsa.services.impl;

import cn.lncsa.common.ListTools;
import cn.lncsa.data.dao.permissions.IRoleUserDAO;
import cn.lncsa.data.model.permissions.UserRole;
import cn.lncsa.data.model.user.User;
import cn.lncsa.services.exceptions.PermissionException;
import cn.lncsa.services.exceptions.UserOperateException;
import cn.lncsa.data.dao.permissions.IPermissionDAO;
import cn.lncsa.data.dao.permissions.IRolePermissionDAO;
import cn.lncsa.data.dao.permissions.IRoleDAO;
import cn.lncsa.data.dao.user.*;
import cn.lncsa.data.model.permissions.Permission;
import cn.lncsa.data.model.permissions.PermissionRole;
import cn.lncsa.data.model.permissions.Role;
import cn.lncsa.services.IPermissionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cattenlinger on 2016/9/26.
 */
@Service
public class PermissionServices implements IPermissionServices {

    private IPermissionDAO permissionDAO;
    private IUserDAO userDAO;
    private IRolePermissionDAO permissionRoleDAO;
    private IRoleDAO roleDAO;
    private IRoleUserDAO roleUserDAO;

    @Autowired
    public void setPermissionDAO(IPermissionDAO permissionDAO) {
        this.permissionDAO = permissionDAO;
    }

    @Autowired
    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setPermissionRoleDAO(IRolePermissionDAO permissionRoleDAO) {
        this.permissionRoleDAO = permissionRoleDAO;
    }

    @Autowired
    public void setRoleDAO(IRoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Autowired
    public void setRoleUserDAO(IRoleUserDAO roleUserDAO) {
        this.roleUserDAO = roleUserDAO;
    }

    @Override
    public Permission addPermission(Permission permission) {
        return permissionDAO.save(permission);
    }

    @Override
    public void removePermission(Integer permissionId) throws PermissionException {
        permissionDAO.delete(permissionId);
    }

    @Override
    public Role addRole(Role role) {
        return roleDAO.save(role);
    }

    @Override
    public void removeRole(Integer roleId) throws PermissionException {
        roleDAO.delete(roleId);
    }

    @Override
    public void grantPermissionToRole(Integer roleId, List<Permission> permissions) throws PermissionException {
        try {
            updatePermissionRelationship(
                    getRole(roleId),
                    ListTools.listDiff(permissionRoleDAO.getPermissionsByRole(roleId), permissions));
        } catch (RoleNotFoundException e) {
            throw new PermissionException("target role not found", e);
        }
    }

    @Override
    public void retakePermissionFromRole(Integer roleId, List<Permission> permissions) {
        permissionRoleDAO.delete(permissionRoleDAO.getRelationships(roleId, permissions));
    }

    @Override
    public void giveRoleToUser(Integer userId, List<Role> roles) throws PermissionException {
        try {
            updateRoleRelationship(getUser(userId), ListTools.listDiff(roleUserDAO.getRolesByUserId(userId), roles));
        } catch (UserOperateException e) {
            throw new PermissionException("target user not found", e);
        }
    }

    @Override
    public void retakeRoleFromUser(Integer userId, List<Role> roles) {
        roleUserDAO.delete(roleUserDAO.getRelationships(userId, roles));
    }

    @Override
    public List<Permission> queryUserPermissions(Integer userId) throws UserOperateException {
        List<Role> roles = roleUserDAO.getRolesByUserId(userId);
        if (roles.size() == 0) return new LinkedList<>();
        List<Permission> permissions = new LinkedList<>();
        for (Role role : roles) {
            permissions.addAll(permissionRoleDAO.getPermissionsByRole(role.getId()));
        }
        return permissions;
    }

    @Override
    public List<Permission> queryRolePermissions(Integer roleId) throws PermissionException {
        return permissionRoleDAO.getPermissionsByRole(roleId);
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
    *   Private procedure
    * */

    private Role getRole(Integer roleId) throws RoleNotFoundException {
        Role role = roleDAO.getOne(roleId);
        if (role == null) throw new RoleNotFoundException("role not found");
        return role;
    }

    private User getUser(Integer userId) throws UserOperateException {
        User user = userDAO.getOne(userId);
        if (user == null) throw new UserOperateException("user not found");
        return user;
    }

    private void updatePermissionRelationship(Role role, List<Permission>[] listDiff) {
        List<PermissionRole> delList = permissionRoleDAO.getRelationships(role.getId(), listDiff[ListTools.LIST_DELETE]);
        List<PermissionRole> addList = new LinkedList<>();
        for (Permission permission : listDiff[ListTools.LIST_ADD]) addList.add(new PermissionRole(role, permission));
        permissionRoleDAO.delete(delList);
        permissionRoleDAO.save(addList);
    }

    private void updateRoleRelationship(User user, List<Role>[] listDiff) {
        List<UserRole> delList = roleUserDAO.getRelationships(user.getId(), listDiff[ListTools.LIST_DELETE]);
        List<UserRole> addList = new LinkedList<>();
        for (Role role : listDiff[ListTools.LIST_ADD]) addList.add(new UserRole(user, role));
        roleUserDAO.delete(delList);
        roleUserDAO.save(addList);
    }

}
