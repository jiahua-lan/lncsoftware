package cn.lncsa.data.repository;

import cn.lncsa.data.model.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by cattenlinger on 2016/10/8.
 */
@NoRepositoryBean
public interface IUserProfileDAO extends IBaseDAO<UserProfile>{

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
