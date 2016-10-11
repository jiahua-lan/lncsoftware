package cn.lncsa.data.dao.permissions;

import cn.lncsa.data.domain.IRelationshipRepository;
import cn.lncsa.data.model.permissions.Permission;
import cn.lncsa.data.model.permissions.PermissionRole;
import cn.lncsa.data.model.permissions.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cattenlinger on 2016/9/26.
 */
public interface IRolePermissionDAO extends JpaRepository<PermissionRole,Integer>, IRelationshipRepository<Role,Permission,PermissionRole>{

    /**
     *
     * Get all permissions the role have
     *
     * @param roleId
     * @return
     */
    @Query("select pr.permission from PermissionRole pr where pr.role.id = ?1")
    List<Permission> getPermissionsByRole(Integer roleId);

    /**
     * Get relationships by roleId
     *
     * @param roleId
     * @param permissions
     * @return
     */
    @Query("select pr from PermissionRole pr where pr.role.id = ?1 and pr.permission in ?2")
    List<PermissionRole> getRelationships(Integer roleId, List<Permission> permissions);

    @Override
    @Query("select pr from PermissionRole pr where pr.permission = ?1")
    List<PermissionRole> getRelationships(Permission slave);

    @Override
    @Query("select pr from PermissionRole pr where pr.role = ?1")
    List<PermissionRole> getRelationships(Role master);

    @Override
    @Query("select pr from PermissionRole pr where pr.role = ?1 and pr.permission in ?2")
    List<PermissionRole> getRelationships(Role master, List<Permission> slave);
}
