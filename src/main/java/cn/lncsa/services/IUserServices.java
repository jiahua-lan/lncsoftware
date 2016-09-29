package cn.lncsa.services;

import cn.lncsa.services.exceptions.UserOperateException;
import cn.lncsa.data.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
    User saveUser(User user);

    /**
     * Delete a user
     *
     * @param userId a user id
     * @return user
     * @throws UserOperateException user not exist
     */
    void deleteUser(Integer userId) throws UserOperateException;

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
    User getUser(Integer userId) throws UserOperateException;

    /**
     * Get user by username
     * <p>
     * This method is for upper level API
     *
     * @param username username
     * @return
     */
    User getUserByName(String username) throws UserOperateException;


    /**
     * Find user by nickname
     *
     * @param keyword     keyword
     * @param pageable paging request
     * @return
     */
    Page<User> findUserByNickname(String keyword, Pageable pageable);

    /**
     * Find user by username
     *
     * @param keyword
     * @param pageable
     * @return
     */
    Page<User> findUserByUsername(String keyword, Pageable pageable);

}
