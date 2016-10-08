package cn.lncsa.services;

import cn.lncsa.services.exceptions.UserOperateException;
import cn.lncsa.data.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import sun.plugin.util.UserProfile;

import java.util.List;

/**
 * Created by catten on 16/6/19.
 */
public interface IUserServices {
    /*
    * User modifying methods
    *
    * */

    /**
     * Save a user
     *
     * @param user
     * @return
     */
    User save(User user);

    /**
     * Delete a user
     *
     * @param userId a user id
     * @return user
     * @throws UserOperateException user not exist
     */
    void delete(Integer userId);

    /*
    * User query methods
    *
    * */

    /**
     * Get user and assert user existed or not;
     *
     * @param userId user id
     * @return an existed user
     * @throws UserOperateException user not existed
     */
    User get(Integer userId);

    /**
     * Get user by username
     * <p>
     * This method is for upper level API
     *
     * @param username username
     * @return
     */
    User getUserByName(String username);

    /**
     * Find user by username
     *
     * @param keyword
     * @param pageable
     * @return
     */
    Page<User> findUserByUsername(String keyword, Pageable pageable);

    UserProfile getUserProfile(Integer userId);

    List<UserProfile> getUserProfile(List<Integer> userId);

    void saveUserProfile(Integer userId, UserProfile userProfile);


}
