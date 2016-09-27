package cn.lncsa.data.dao.permissions;

import cn.lncsa.data.model.permissions.Role;
import cn.lncsa.data.model.user.User;
import cn.lncsa.data.model.permissions.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Handling relationship between user and role.
 *
 * Created by catte on 2016/6/12.
 */
public interface IRoleUserDAO extends PagingAndSortingRepository<UserRole,Integer> {

    /**
     * Get a list of user have specified role
     *
     * @param rightId a existed role's id
     * @return a list of user
     */
    @Query("select ur.user from UserRole ur where ur.role.id = ?1")
    Page<User> getUsersByRoleId(Integer rightId, Pageable pageable);

    /**
     * Get a list of rights that the user have
     *
     * @param userId a user id
     * @return list of rights
     */
    @Query("select ur.role from UserRole ur where ur.user.id = ?1")
    List<Role> getRolesByUserId(Integer userId);

    @Modifying
    @Query("delete from UserRole ur where ur.user.id = ?1 and ur.role.id = ?2")
    void deleteRelationship(Integer userId, Integer roleId);
}