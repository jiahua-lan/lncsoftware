package cn.lncsa.services.impl;

import cn.lncsa.services.exceptions.UserOperateException;
import cn.lncsa.data.dao.article.IArticleDAO;
import cn.lncsa.data.dao.article.ICommitDAO;
import cn.lncsa.data.dao.user.IUserDAO;
import cn.lncsa.data.dao.permissions.IRoleUserDAO;
import cn.lncsa.data.model.user.User;
import cn.lncsa.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by catten on 16/6/19.
 */
@Service
public class UserServices implements IUserServices {
    private IUserDAO userDAO;

    @Autowired
    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User saveUser(User user) {
        return userDAO.save(user);
    }

    @Override
    public void deleteUser(Integer userId){
        userDAO.delete(userId);
    }

    @Override
    public User get(Integer userId){
        return userDAO.findOne(userId);
    }

    @Override
    public User getUserByName(String username){
        return userDAO.getByName(username);
    }

    @Override
    public Page<User> findUserByNickname(String keyword, Pageable pageable) {
        return userDAO.getByNickNameLike(keyword,pageable);
    }

    @Override
    public Page<User> findUserByUsername(String keyword, Pageable pageable) {
        return userDAO.getByNameLike(keyword,pageable);
    }
}
