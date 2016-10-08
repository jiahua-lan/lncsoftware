package cn.lncsa.data.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sun.plugin.util.UserProfile;

/**
 * Created by cattenlinger on 2016/10/8.
 */
public interface IUserProfileDAO extends JpaRepository<UserProfile,Integer>{

    @Query("select up from UserProfile up where up.id = ?1")
    UserProfile getById(Integer id);
}
