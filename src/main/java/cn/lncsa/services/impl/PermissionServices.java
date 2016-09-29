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
import cn.lncsa.services.helper.RelationshipHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.lang.reflect.InvocationTargetException;
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

    private RelationshipHelper<Role,Permission,PermissionRole> rolePermissionRelationshipHelper;
    private RelationshipHelper<User,Role,UserRole> userRoleRelationshipHelper;

    public PermissionServices() {
        rolePermissionRelationshipHelper = new RelationshipHelper<>();
        userRoleRelationshipHelper = new RelationshipHelper<>();
    }

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
    public void removePermission(Integer permissionId) {
        permissionDAO.delete(permissionId);
    }

    @Override
    public Role addRole(Role role) {
        return roleDAO.save(role);
    }

    @Override
    public void removeRole(Integer roleId) {
        roleDAO.delete(roleId);
    }

    @Override
    public void grantPermissionToRole(Integer roleId, List<Permission> permissions) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        rolePermissionRelationshipHelper.updateRelationship(
                getRole(roleId),
                ListTools.listDiff(permissionRoleDAO.getPermissionsByRole(roleId), permissions),
                permissionRoleDAO
        );
    }

    @Override
    public void retakePermissionFromRole(Integer roleId, List<Permission> permissions) {
        permissionRoleDAO.delete(permissionRoleDAO.getRelationships(roleId, permissions));
    }

    @Override
    public void giveRoleToUser(Integer userId, List<Role> roles) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        userRoleRelationshipHelper.updateRelationship(
                userDAO.findOne(userId),
                ListTools.listDiff(roleUserDAO.getRolesByUserId(userId), roles),
                roleUserDAO);
    }

    @Override
    public void retakeRoleFromUser(Integer userId, List<Role> roles) {
        roleUserDAO.delete(roleUserDAO.getRelationships(userId, roles));
    }

    @Override
    public List<Permission> queryUserPermissions(Integer userId){
        List<Role> roles = roleUserDAO.getRolesByUserId(userId);
        if (roles.size() == 0) return new LinkedList<>();
        List<Permission> permissions = new LinkedList<>();
        for (Role role : roles) {
            permissions.addAll(permissionRoleDAO.getPermissionsByRole(role.getId()));
        }
        return permissions;
    }

    @Override
    public Role getRole(Integer roleId){
        return roleDAO.findOne(roleId);
    }

    @Override
    public List<Role> queryUserRoles(Integer userId) {
        return roleUserDAO.getRolesByUserId(userId);
    }

    @Override
    public List<Permission> queryRolePermissions(Integer roleId){
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

    @Override
    public Permission getPermission(Integer permissionId) {
        return permissionDAO.getOne(permissionId);
    }

}
