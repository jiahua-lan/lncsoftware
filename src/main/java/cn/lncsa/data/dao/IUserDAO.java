package cn.lncsa.data.dao;

import cn.lncsa.data.model.Right;
import cn.lncsa.data.model.User;
import cn.lncsa.data.model.UserRight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by catte on 2016/6/12.
 */
public interface IUserDAO extends JpaRepository<User,Integer> {

    /**
     * Get user by name
     *
     * @param name username
     * @return a user
     */
    User getByName(String name);
}
