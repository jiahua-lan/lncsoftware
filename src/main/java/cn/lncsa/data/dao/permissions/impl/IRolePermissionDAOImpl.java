package cn.lncsa.data.dao.permissions.impl;

import cn.lncsa.data.dao.base.domain.impl.IRelationshipRepositoryImpl;
import cn.lncsa.data.dao.permissions.IRolePermissionDAO;
import cn.lncsa.data.model.permissions.Permission;
import cn.lncsa.data.model.permissions.PermissionRole;
import cn.lncsa.data.model.permissions.Role;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by cattenlinger on 2016/10/13.
 */
public class IRolePermissionDAOImpl extends IRelationshipRepositoryImpl<Role,Permission,PermissionRole> implements IRolePermissionDAO {

    public IRolePermissionDAOImpl(Class<PermissionRole> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    @Override
    public List<Permission> getPermissionsByRole(Integer roleId) {
        return null;
    }

    @Override
    public List<PermissionRole> getRelationships(Integer roleId, List<Permission> permissions) {
        return null;
    }

    @Override
    public List<PermissionRole> getRelationships(Permission slave) {
        return null;
    }

    @Override
    public List<PermissionRole> getRelationships(Role master) {
        return null;
    }

    @Override
    public List<PermissionRole> getRelationships(Role master, List<Permission> slave) {
        return null;
    }
}
