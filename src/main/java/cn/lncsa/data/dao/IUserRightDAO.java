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
public interface IUserRightDAO extends JpaRepository<UserRight,Integer> {
    List<UserRight> getByUser(User user);

    @Query("select ur from UserRight ur where ur.user.id = :userId")
    List<UserRight> getByUserId(@Param("userId") Integer userId);

    List<UserRight> getByRight(Right right);

    @Query("select ur from UserRight ur where ur.right.id = :rightId")
    List<UserRight> getByRightId(@Param("rightId") Integer rightId);
}
