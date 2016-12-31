package cn.lncsa.services;

import cn.lncsa.data.model.User;
import cn.lncsa.data.model.UserProfile;
import cn.lncsa.data.repository.IUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by catten on 12/31/16.
 */
@Service
public class UserServices{

    private IUserDAO userDAO;

    @Autowired
    private void setUserDAO(IUserDAO userDAO){
        this.userDAO = userDAO;
    }

    public User save(User user) {
        return userDAO.save(user);
    }
    
    public void delete(Integer userId) {

    }

    public User get(Integer userId) {
        return userDAO.findOne(userId);
    }

    
    public User getByName(String username) {
        return userDAO.getByName(username);
    }

    
    public Page<User> findByUsername(String keyword, Pageable pageable) {
        return null;
    }

    
    public UserProfile getProfile(Integer userId, boolean ignoreSecret) {
        return null;
    }

    public Page<UserProfile> getProfile(List<Integer> userId, boolean ignoreSecret, Pageable pageable) {
        return null;
    }

    public void saveProfile(Integer userId, UserProfile userProfile) {

    }

    public boolean checkPassword(String username, String password) {
        return false;
    }
}
