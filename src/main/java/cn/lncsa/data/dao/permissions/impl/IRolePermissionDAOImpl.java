package cn.lncsa.data.dao.permissions.impl;

import cn.lncsa.data.dao.base.domain.impl.IRelationshipRepositoryImpl;
import cn.lncsa.data.dao.permissions.IRolePermissionDAO;
import cn.lncsa.data.model.permissions.Permission;
import cn.lncsa.data.model.permissions.PermissionRole;
import cn.lncsa.data.model.permissions.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by cattenlinger on 2016/10/13.
 */
public class IRolePermissionDAOImpl extends IRelationshipRepositoryImpl<Role,Permission,PermissionRole> implements IRolePermissionDAO {

    public IRolePermissionDAOImpl(Class<PermissionRole> domainClass, EntityManager em) {
        super(domainClass, em);
    }

}
