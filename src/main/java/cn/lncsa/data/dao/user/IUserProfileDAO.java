package cn.lncsa.data.dao.user;

import cn.lncsa.data.dao.base.IFieldBaseQueryDAO;
import cn.lncsa.data.model.user.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by cattenlinger on 2016/10/8.
 */
@NoRepositoryBean
public interface IUserProfileDAO extends JpaRepository<UserProfile, Integer>, IFieldBaseQueryDAO<UserProfile>{

    /**
     * Get user profile by single user id
     *
     * @param userId
     * @return
     */
    UserProfile getByUserId(Integer userId, boolean ignoreSecret);

    /**
     * Get a list of user profile
     *
     * @param userId
     * @return
     */
    Page<UserProfile> getByUserId(List<Integer> userId,boolean ignoreSecret, Pageable pageable);
}
