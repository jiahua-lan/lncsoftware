package cn.lncsa.data.dao.user;

import cn.lncsa.data.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by catten on 10/11/16.
 */
public interface IPasswordDAO extends JpaRepository<User,Integer> {

    @Query("select User.password from User where User.id = ?1")
    String get(Integer userId);
}
