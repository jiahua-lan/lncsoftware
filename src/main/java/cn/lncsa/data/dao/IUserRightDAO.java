package cn.lncsa.data.dao;

import cn.lncsa.data.model.UserRights;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by catte on 2016/6/12.
 */
public interface IUserRightDAO extends JpaRepository<UserRights,Integer> {
}
