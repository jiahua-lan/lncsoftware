package cn.lncsa.services.impl;

import cn.lncsa.common.exceptions.UserOperateException;
import cn.lncsa.data.dao.article.IArticleDAO;
import cn.lncsa.data.dao.article.ICommitDAO;
import cn.lncsa.data.dao.user.IUserDAO;
import cn.lncsa.data.dao.permissions.IRoleUserDAO;
import cn.lncsa.data.model.permissions.Role;
import cn.lncsa.data.model.user.User;
import cn.lncsa.data.model.permissions.UserRole;
import cn.lncsa.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by catten on 16/6/19.
 */
@Service
public class UserServices implements IUserServices {
    private IUserDAO userDAO;
    private IRoleUserDAO userRightDAO;
    private IArticleDAO articleDAO;
    private ICommitDAO commitDAO;

    @Autowired
    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setUserRightDAO(IRoleUserDAO userRightDAO) {
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
    public User createUser(String name, String password, String contactInfo, List<Role> roles) throws UserOperateException {
        User user = createUser(name,password,contactInfo);
        List<UserRole> userRoleList = new LinkedList<>();
        for (Role role : roles){
            userRoleList.add(new UserRole(user, role));
        }
        userRightDAO.save(userRoleList);
        return user;
    }

    @Override
    public User getUserByName(String username) throws UserOperateException {
        User user = userDAO.getByName(username);
        if (user == null) throw new UserOperateException("User not exist");
        return user;
    }

    @Override
    public Page<User> findUser(String keyword, PageRequest pageRequest){
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
        List<UserRole> rightList = userRightDAO.queryRightRelationByUser(user);
        if(rightList != null) userRightDAO.delete(rightList);
        return user;
    }

    @Transactional
    @Override
    public User deleteUser(Integer userId) throws UserOperateException {
        User user = banUser(userId);
        //Delete user's article
        articleDAO.deleteArticleByAuthorId(userId);
        //Delete user's commit
        commitDAO.deleteByUserId(userId);
        //Delete the user.
        userDAO.delete(user);
        return user;
    }

    @Override
    public List<Role> listUserRoles(Integer userId) throws UserOperateException {
        return userRightDAO.getRolesByUser(getUser(userId));
    }

    @Override
    public List<Role> updateUserRoles(Integer userId, List<Role> roles) throws UserOperateException {
        User user = getUser(userId);
        List<UserRole> userRoleList = userRightDAO.queryRightRelationByUser(user);
        if(userRoleList != null && userRoleList.size() > 0) userRightDAO.delete(userRoleList);
        return saveUserRightList(user, roles);
    }

    /**
     * Save user permission list.
     *
     * @param user
     * @param roleList
     * @return
     */
    private List<Role> saveUserRightList(User user, List<Role> roleList){
        for (Role role : roleList){
            userRightDAO.save(new UserRole(user, role));
        }
        return roleList;
    }

    public User getUser(Integer userId) throws UserOperateException {
        User user = userDAO.findOne(userId);
        if(user == null) throw new UserOperateException("User not exist");
        return user;
    }
}
