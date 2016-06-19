package cn.lncsa.services.impl;

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
import cn.lncsa.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by catten on 16/6/19.
 */
@Service
public class UserServices implements IUserServices {
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

    @Override
    public User createUser(String name, String password, String contactInfo) throws UserOperateException {
        User user = userDAO.getByName(name);
        if(user != null) throw new UserOperateException("User existed");
        user = new User(name,password,contactInfo);
        user.setNickName(name);
        userDAO.save(user);
        return user;
    }

    @Override
    public User createUser(String name, String password, String contactInfo, List<Right> rights) throws UserOperateException {
        User user = createUser(name,password,contactInfo);
        List<UserRight> userRightList = new LinkedList<>();
        for (Right right : rights){
            userRightList.add(new UserRight(user,right));
        }
        userRightDAO.save(userRightList);
        return user;
    }

    @Override
    public User getUserByName(String username) throws UserOperateException {
        User user = userDAO.getByName(username);
        if (user == null) throw new UserOperateException("User not exist");
        return user;
    }

    @Override
    public Page<User> findUser(String keyword){
        return null;//TODO Full field query needed.
    }

    @Override
    public User changePassword(Integer userId, String newPassword) throws UserOperateException {
        User user = getUser(userId);
        user.setPassword(newPassword);
        userDAO.save(user);
        return user;
    }

    @Override
    public User changeContactInfo(Integer userId, String newContactInfo) throws UserOperateException {
        User user = getUser(userId);
        if(newContactInfo.equals(user.getContactInfo())) return user;
        user.setContactInfo(newContactInfo);
        userDAO.save(user);
        return user;
    }

    @Override
    public User changeNickName(Integer userId, String newNickName) throws UserOperateException {
        User user = getUser(userId);
        user.setNickName(newNickName);
        userDAO.save(user);
        return user;
    }

    @Override
    public User banUser(Integer userId) throws UserOperateException {
        User user = getUser(userId);
        List<UserRight> rightList = userRightDAO.queryRightRelationByUser(user);
        if(rightList != null) userRightDAO.delete(rightList);
        return user;
    }

    @Override
    public User deleteUser(Integer userId) throws UserOperateException {
        User user = banUser(userId);
        Page<Article> articles = articleDAO.findByAuthor(user,new PageRequest(0,20));
        while (articles.hasNext()) articleDAO.delete(articles.getContent());
        Page<Commit> commits = commitDAO.getByUser(user,new PageRequest(0,20));
        while (commits.hasNext()) commitDAO.delete(commits.getContent());
        userDAO.delete(user);
        return user;
    }

    @Override
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
