package cn.lncsa.services;

import cn.lncsa.common.exceptions.UserOperateException;
import cn.lncsa.data.dao.article.IArticleDAO;
import cn.lncsa.data.dao.article.ICommitDAO;
import cn.lncsa.data.dao.user.IUserDAO;
import cn.lncsa.data.dao.user.IUserRightDAO;
import cn.lncsa.data.model.article.Article;
import cn.lncsa.data.model.article.Commit;
import cn.lncsa.data.model.user.Right;
import cn.lncsa.data.model.user.User;
import cn.lncsa.data.model.user.UserRight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by catten on 16/6/19.
 */
@Service
public class UserServices {
    private IUserDAO userDAO;
    private IUserRightDAO userRightDAO;
    private IArticleDAO articleDAO;
    private ICommitDAO commitDAO;

    @Autowired
    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setUserRightDAO(IUserRightDAO userRightDAO) {
        this.userRightDAO = userRightDAO;
    }

    @Autowired
    public void setCommitDAO(ICommitDAO commitDAO) {
        this.commitDAO = commitDAO;
    }

    @Autowired
    public void setArticleDAO(IArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    /**
     * Create user normally, without permissions.
     *
     * @param name username
     * @param password password for this user
     * @param contactInfo contactInfo for this user
     * @return a saved user
     * @throws UserOperateException user existed.
     */
    public User createUser(String name, String password, String contactInfo) throws UserOperateException {
        User user = userDAO.getByName(name);
        if(user != null) throw new UserOperateException("User existed");
        user = new User(name,password,contactInfo);
        user.setNickName(name);
        userDAO.save(user);
        return user;
    }

    /**
     * Create user with specified permissions
     *
     * @param name user name
     * @param password password for this user
     * @param contactInfo contactInfo for this user
     * @param rights specified permissions for this user
     * @return a saved user
     * @throws UserOperateException user existed.
     */
    public User createUser(String name, String password, String contactInfo, List<Right> rights) throws UserOperateException {
        User user = createUser(name,password,contactInfo);
        List<UserRight> userRightList = new LinkedList<>();
        for (Right right : rights){
            userRightList.add(new UserRight(user,right));
        }
        userRightDAO.save(userRightList);
        return user;
    }

    /**
     * Get user by username
     *
     * This method is for upper level API
     *
     * @param username username
     * @return
     */
    public User getUserByName(String username) throws UserOperateException {
        User user = userDAO.getByName(username);
        if (user == null) throw new UserOperateException("User not exist");
        return user;
    }

    public Page<User> findUser(String keyword){
        return null;//TODO Full field query needed.
    }

    /**
     * Change user's password
     *
     * @param userId user id
     * @param newPassword new password for the user
     * @return user with it's new password
     * @throws UserOperateException user not exist.
     */
    public User changePassword(Integer userId, String newPassword) throws UserOperateException {
        User user = getUser(userId);
        user.setPassword(newPassword);
        userDAO.save(user);
        return user;
    }

    /**
     * Change user's contact info
     *
     * @param userId user id
     * @param newContactInfo new contact information for the user
     * @return a saved user
     * @throws UserOperateException user not exist
     */
    public User changeContactInfo(Integer userId, String newContactInfo) throws UserOperateException {
        User user = getUser(userId);
        if(newContactInfo.equals(user.getContactInfo())) return user;
        user.setContactInfo(newContactInfo);
        userDAO.save(user);
        return user;
    }

    /**
     * Change user's nick name
     *
     * @param userId user id
     * @param newNickName new nick name for the user
     * @return a saved user
     * @throws UserOperateException user not exist
     */
    public User changeNickName(Integer userId, String newNickName) throws UserOperateException {
        User user = getUser(userId);
        user.setNickName(newNickName);
        userDAO.save(user);
        return user;
    }

    /**
     * Ban a user
     *
     * It means remove all rights for this user
     *
     * @param userId user id
     * @return user
     * @throws UserOperateException user not exist
     */
    public User banUser(Integer userId) throws UserOperateException {
        User user = getUser(userId);
        List<UserRight> rightList = userRightDAO.queryRightRelationByUser(user);
        if(rightList != null) userRightDAO.delete(rightList);
        return user;
    }

    /**
     * Delete a user and other data, such as article, commit and other.
     *
     * Usage: A user decided to leave the site forever.
     *
     * <b>It will make a heavy database transaction, don't use this to delete a user</b>
     *
     * @param userId a user id
     * @return user
     * @throws UserOperateException user not exist
     */
    public User deleteUser(Integer userId) throws UserOperateException {
        User user = banUser(userId);
        Page<Article> articles = articleDAO.findByAuthor(user);
        while (articles.hasNext()) articleDAO.delete(articles.getContent());
        Page<Commit> commits = commitDAO.getByUser(user);
        while (commits.hasNext()) commitDAO.delete(commits.getContent());
        userDAO.delete(user);
        return user;
    }

    /**
     * Get a list of permissions of the user
     *
     * @param userId user id
     * @return a list of permissions
     * @throws UserOperateException if user not existed
     */
    public List<Right> listUserRights(Integer userId) throws UserOperateException {
        return userRightDAO.getRightByUser(getUser(userId));
    }

    /**
     * Get user and assert user existed or not;
     *
     * @param userId user id
     * @return an existed user
     * @throws UserOperateException user not existed
     */
    private User getUser(Integer userId) throws UserOperateException {
        User user = userDAO.findOne(userId);
        if(user == null) throw new UserOperateException("User not exist");
        return user;
    }
}
