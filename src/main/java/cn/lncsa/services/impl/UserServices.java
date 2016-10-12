package cn.lncsa.services.impl;

import cn.lncsa.data.dao.user.IPasswordDAO;
import cn.lncsa.data.dao.user.IUserProfileDAO;
import cn.lncsa.data.model.user.UserProfile;
import cn.lncsa.data.dao.user.IUserDAO;
import cn.lncsa.data.model.user.User;
import cn.lncsa.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by catten on 16/6/19.
 */
@Service
public class UserServices implements IUserServices {
    private IUserDAO userDAO;
    private IUserProfileDAO userProfileDAO;
    private IPasswordDAO passwordDAO;

    @Autowired
    public void setUserProfileDAO(@Qualifier("IUserProfileDAO") IUserProfileDAO userProfileDAO) {
        this.userProfileDAO = userProfileDAO;
    }

    @Autowired
    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setPasswordDAO(IPasswordDAO passwordDAO) {
        this.passwordDAO = passwordDAO;
    }

    @Override
    public User save(User user) {
        if(user.getId() == null){
            UserProfile userProfile = new UserProfile();
            userProfileDAO.save(userProfile);
            user.setProfileId(userProfile.getId());
        }
        return userDAO.save(user);
    }

    @Override
    public void delete(Integer userId){
        userDAO.delete(userId);
    }

    @Override
    public User get(Integer userId){
        return userDAO.findOne(userId);
    }

    @Override
    public User getByName(String username){
        return userDAO.getByName(username);
    }

    @Override
    public Page<User> findByUsername(String keyword, Pageable pageable) {
        return userDAO.getByNameLike(keyword,pageable);
    }

    @Override
    public UserProfile getProfile(Integer userId) {
        return userProfileDAO.getByUserId(userId);
    }

    @Override
    public Page<UserProfile> getProfile(List<Integer> userId, Pageable pageable) {
        return userProfileDAO.getByUserId(userId, pageable);
    }

    @Override
    public void saveProfile(Integer userId, UserProfile userProfile) {
        User user = userDAO.getOne(userId);
        if(user.getProfileId() == null){
            userProfileDAO.save(userProfile);
            user.setProfileId(userProfile.getId());
            userDAO.save(user);
        }else{
            userProfile.setId(userProfile.getId());
            userProfileDAO.save(userProfile);
        }
    }

    @Override
    public boolean checkPassword(String username, String password) {
        User user = userDAO.getByName(username);
        if(user == null) return false;
        return checkPassword(user.getId(),password);
    }

    @Override
    public boolean checkPassword(User user, String password) {
        if(user.getId() == null) return false;
        return checkPassword(user.getId(),password);
    }

    @Override
    public boolean checkPassword(Integer userId, String password) {
        String userPassword = passwordDAO.get(userId);
        return userPassword.equals(password);
    }
}
