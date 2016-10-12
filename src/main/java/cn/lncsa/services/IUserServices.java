package cn.lncsa.services;

import cn.lncsa.data.model.user.UserProfile;
import cn.lncsa.services.exceptions.UserOperateException;
import cn.lncsa.data.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    User getByName(String username);

    /**
     * Find user by username
     *
     * @param keyword
     * @param pageable
     * @return
     */
    Page<User> findByUsername(String keyword, Pageable pageable);

    /**
     * Get a user profile
     *
     * @param userId
     * @return
     */
    UserProfile getProfile(Integer userId);

    /**
     * Get a list of user profiles
     *
     * @param userId
     * @return
     */
    Page<UserProfile> getProfile(List<Integer> userId, Pageable pageable);

    /**
     * save user's profile
     *
     * @param userId
     * @param userProfile
     */
    void saveProfile(Integer userId, UserProfile userProfile);

    /**
     * Password checking
     *
     * @param username
     * @param password
     * @return
     */
    boolean checkPassword(String username, String password);

    boolean checkPassword(User user, String password);

    boolean checkPassword(Integer userId, String password);
}
