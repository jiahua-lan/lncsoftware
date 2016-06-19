package cn.lncsa.data.dao.user;

import cn.lncsa.data.model.user.Right;
import cn.lncsa.data.model.user.User;
import cn.lncsa.data.model.user.UserRight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Handling relationship between user and right.
 *
 * Created by catte on 2016/6/12.
 */
public interface IUserRightDAO extends PagingAndSortingRepository<UserRight,Integer> {
    /**
     * Get a list of user have specified right
     *
     * @param right a existed right
     * @return a list of user
     */
    @Query("select ur.user from UserRight ur where ur.right = ?1")
    Page<User> getUserByRight(Right right, Pageable pageable);

    /**
     * Get a list of user have specified right
     *
     * @param rightId a existed right's id
     * @return a list of user
     */
    @Query("select ur.user from UserRight ur where ur.right.id = ?1")
    Page<User> getUserByRightId(Integer rightId, Pageable pageable);

    /**
     * Get a list of rights that the user have.
     *
     * @param user a user
     * @return list of rights
     */
    @Query("select ur.right from UserRight ur where ur.user = ?1")
    List<Right> getRightByUser(User user);

    /**
     * Get a list of rights that the user have
     *
     * @param userId a user id
     * @return list of rights
     */
    @Query("select ur.right from UserRight ur where ur.user.id = ?1")
    List<Right> getRightByUserId(Integer userId);

    /**
     * Get all permission relations for the user
     *
     * @param user
     * @return
     */
    @Query("select ur from UserRight ur where ur.user = ?1")
    List<UserRight> queryRightRelationByUser(User user);

    /**
     * Get all permission relations for the user by id
     *
     * @param userId
     * @return
     */
    @Query("select ur from UserRight ur where ur.user.id = ?1")
    List<UserRight> queryRightRelationByUserId(Integer userId);
}
