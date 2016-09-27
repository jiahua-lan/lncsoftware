package cn.lncsa.data.dao.user;

import cn.lncsa.data.model.permissions.Role;
import cn.lncsa.data.model.user.User;
import cn.lncsa.data.model.permissions.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Handling relationship between user and role.
 *
 * Created by catte on 2016/6/12.
 */
public interface IUserRoleDAO extends PagingAndSortingRepository<UserRole,Integer> {
    /**
     * Get a list of user have specified role
     *
     * @param role a existed role
     * @return a list of user
     */
    @Query("select ur.user from UserRole ur where ur.role = ?1")
    Page<User> getUserByRight(Role role, Pageable pageable);

    /**
     * Get a list of user have specified role
     *
     * @param rightId a existed role's id
     * @return a list of user
     */
    @Query("select ur.user from UserRole ur where ur.role.id = ?1")
    Page<User> getUserByRightId(Integer rightId, Pageable pageable);

    /**
     * Get a list of rights that the user have.
     *
     * @param user a user
     * @return list of rights
     */
    @Query("select ur.role from UserRole ur where ur.user = ?1")
    List<Role> getUserRoles(User user);

    /**
     * Get a list of rights that the user have
     *
     * @param userId a user id
     * @return list of rights
     */
    @Query("select ur.role from UserRole ur where ur.user.id = ?1")
    List<Role> getUserRolesByUserId(Integer userId);

    /**
     * Get all permission relations for the user
     *
     * @param user
     * @return
     */
    @Query("select ur from UserRole ur where ur.user = ?1")
    List<UserRole> queryRightRelationByUser(User user);

    /**
     * Get all permission relations for the user by id
     *
     * @param userId
     * @return
     */
    @Query("select ur from UserRole ur where ur.user.id = ?1")
    List<UserRole> queryRightRelationByUserId(Integer userId);
}
