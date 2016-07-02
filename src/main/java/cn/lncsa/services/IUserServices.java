package cn.lncsa.services;

import cn.lncsa.common.exceptions.UserOperateException;
import cn.lncsa.data.model.user.Right;
import cn.lncsa.data.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by catten on 16/6/19.
 */
public interface IUserServices {
    /**
     * Create user normally, without permissions.
     *
     * @param name        username
     * @param password    password for this user
     * @param contactInfo contactInfo for this user
     * @return a saved user
     * @throws UserOperateException user existed.
     */
    User createUser(String name, String password, String contactInfo) throws UserOperateException;

    /**
     * Create user with specified permissions
     *
     * @param name        user name
     * @param password    password for this user
     * @param contactInfo contactInfo for this user
     * @param rights      specified permissions for this user
     * @return a saved user
     * @throws UserOperateException user existed.
     */
    User createUser(String name, String password, String contactInfo, List<Right> rights) throws UserOperateException;

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
     * Find user by keyword
     *
     * @param keyword     keyword
     * @param pageRequest paging request
     * @return
     */
    Page<User> findUser(String keyword, PageRequest pageRequest);

    /**
     * Change user's password
     *
     * @param userId      user id
     * @param newPassword new password for the user
     * @return user with it's new password
     * @throws UserOperateException user not exist.
     */
    User changePassword(Integer userId, String newPassword) throws UserOperateException;

    /**
     * Change user's contact info
     *
     * @param userId         user id
     * @param newContactInfo new contact information for the user
     * @return a saved user
     * @throws UserOperateException user not exist
     */
    User changeContactInfo(Integer userId, String newContactInfo) throws UserOperateException;

    /**
     * Change user's nick name
     *
     * @param userId      user id
     * @param newNickName new nick name for the user
     * @return a saved user
     * @throws UserOperateException user not exist
     */
    User changeNickName(Integer userId, String newNickName) throws UserOperateException;

    /**
     * Ban a user
     * <p>
     * It means remove all rights for this user
     *
     * @param userId user id
     * @return user
     * @throws UserOperateException user not exist
     */
    User banUser(Integer userId) throws UserOperateException;

    /**
     * Delete a user and other data, such as article, commit and other.
     * <p>
     * Usage: A user decided to leave the site forever.
     * <p>
     * <b>It will make a heavy database transaction, don't use this to delete a user</b>
     *
     * @param userId a user id
     * @return user
     * @throws UserOperateException user not exist
     */
    User deleteUser(Integer userId) throws UserOperateException;

    /**
     * Get a list of permissions of the user
     *
     * @param userId user id
     * @return a list of permissions
     * @throws UserOperateException if user not existed
     */
    List<Right> listUserRights(Integer userId) throws UserOperateException;

    /**
     * Update user permission
     *
     * @param userId target id
     * @param rights new permission list
     * @return
     */
    List<Right> updateUserRights(Integer userId, List<Right> rights) throws UserOperateException;


    /**
     * Get user and assert user existed or not;
     *
     * @param userId user id
     * @return an existed user
     * @throws UserOperateException user not existed
     */
    User getUser(Integer userId) throws UserOperateException;

}
