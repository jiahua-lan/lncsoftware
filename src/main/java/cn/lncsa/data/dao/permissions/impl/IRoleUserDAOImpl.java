package cn.lncsa.data.dao.permissions.impl;

import cn.lncsa.data.dao.base.domain.impl.IRelationshipRepositoryImpl;
import cn.lncsa.data.dao.permissions.IRoleUserDAO;
import cn.lncsa.data.model.permissions.Role;
import cn.lncsa.data.model.permissions.UserRole;
import cn.lncsa.data.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by cattenlinger on 2016/10/13.
 */
public class IRoleUserDAOImpl extends IRelationshipRepositoryImpl<User,Role,UserRole> implements IRoleUserDAO {

    public IRoleUserDAOImpl(Class<UserRole> domainClass, EntityManager em) {
        super(domainClass, em);
    }

}
