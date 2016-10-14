package cn.lncsa.data.dao.permissions;

import cn.lncsa.data.dao.base.domain.IRelationshipRepository;
import cn.lncsa.data.model.permissions.Permission;
import cn.lncsa.data.model.permissions.PermissionRole;
import cn.lncsa.data.model.permissions.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by cattenlinger on 2016/9/26.
 */
public interface IRolePermissionDAO extends JpaRepository<PermissionRole,Integer>, IRelationshipRepository<Role,Permission,PermissionRole>{

}
