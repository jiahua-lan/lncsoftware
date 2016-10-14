package cn.lncsa.data.dao.permissions;

import cn.lncsa.data.dao.base.domain.IRelationshipRepository;
import cn.lncsa.data.model.permissions.Permission;
import cn.lncsa.data.model.permissions.PermissionRole;
import cn.lncsa.data.model.permissions.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by cattenlinger on 2016/9/26.
 */
public interface IRolePermissionDAO extends CrudRepository<PermissionRole,Integer>, IRelationshipRepository<Role,Permission,PermissionRole>{
    /**
     *
     * Get all permissions the role have
     *
     * @param roleId
     * @return
     */
    @Query("select pr.permission from PermissionRole pr where pr.role.id = ?1")
    List<Permission> getPermissionsByRole(Integer roleId);
}
