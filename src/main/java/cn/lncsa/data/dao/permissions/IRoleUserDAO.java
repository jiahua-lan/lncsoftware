package cn.lncsa.data.dao.permissions;

import cn.lncsa.data.dao.domain.IRelationshipRepository;
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
public interface IRoleUserDAO extends PagingAndSortingRepository<UserRole,Integer>, IRelationshipRepository<User,Role,UserRole> {

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

    @Query("select ur from UserRole ur where ur.user.id = ?1 and ur.role in ?2")
    List<UserRole> getRelationships(Integer userId, List<Role> elements);

    @Override
    @Query("select ur from UserRole ur where ur.role = ?1")
    List<UserRole> getRelationships(Role slave);

    @Override
    @Query("select ur from UserRole ur where ur.user = ?1")
    List<UserRole> getRelationships(User master);

    @Override
    @Query("select ur from UserRole ur where ur.user = ?1 and ur.role in ?2")
    List<UserRole> getRelationships(User master, List<Role> slave);
}
